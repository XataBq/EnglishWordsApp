package com.example.englishwordsapp

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.englishwordsapp.databinding.ActivityLearnWordBinding

class MainActivity : AppCompatActivity() {

    //    private lateinit var binding: ActivityLearnWordBinding
    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        //findViewById
        //ViewBinding
//        val tvQuestionWord: TextView = findViewById(R.id.tvQuestionWord)
//        tvQuestionWord.text = "42"
//        tvQuestionWord.setTextColor(Color.BLUE)
//        tvQuestionWord.setTextColor(Color.parseColor("#FDD600"))
//        tvQuestionWord.setTextColor(ContextCompat.getColor(this, R.color.textVariantsColor))

//        binding.run {
//            tvQuestionWord.text = "AndroidSprint.ru"
//            tvQuestionWord.setTextColor(Color.GRAY)
//            imageButton.isVisible = false
//    }
        // нейтральный
        // корректный
        // некорректный

        val trainer = LearnWordsTrainer()
        showNextQuestion(trainer)

        with(binding) {
            btnContinue.setOnClickListener {
                layoutResult.isVisible = false
                btnSkip.isVisible = true
                markAnswerNeutral(layoutAnswer1, tvVariantValue1, tvVariantNumber1)
                markAnswerNeutral(layoutAnswer2, tvVariantValue2, tvVariantNumber2)
                markAnswerNeutral(layoutAnswer3, tvVariantValue3, tvVariantNumber3)
                markAnswerNeutral(layoutAnswer4, tvVariantValue4, tvVariantNumber4)
                showNextQuestion(trainer)
            }

            btnSkip.setOnClickListener {
                showNextQuestion(trainer)
            }
        }

    }

    private fun showNextQuestion(trainer: LearnWordsTrainer) {
        val firstQuestion: Question? = trainer.getNextQuestion()
        with(binding) {
            if (firstQuestion == null || firstQuestion.variants.size < NUMBER_OF_ANSWERS) {
                tvQuestionWord.isVisible = false
                layoutVariants.isVisible = false
                btnSkip.text = "Complete"
            } else {
                btnSkip.isVisible = true
                tvQuestionWord.isVisible = true
                tvQuestionWord.text = firstQuestion.correctAnswer.original

                tvVariantValue1.text = firstQuestion.variants[0].translate
                tvVariantValue2.text = firstQuestion.variants[1].translate
                tvVariantValue3.text = firstQuestion.variants[2].translate
                tvVariantValue4.text = firstQuestion.variants[3].translate

                layoutAnswer1.setOnClickListener {
                    if (trainer.checkAnswer(0)) {
                        markAnswerCorrect(layoutAnswer1, tvVariantValue1, tvVariantNumber1)
                        showResultMessage(true)
                    } else {
                        markAnswerWrong(layoutAnswer1, tvVariantValue1, tvVariantNumber1)
                        showResultMessage(false)
                    }
                }

                layoutAnswer2.setOnClickListener {
                    if (trainer.checkAnswer(1)) {
                        markAnswerCorrect(layoutAnswer2, tvVariantValue2, tvVariantNumber2)
                        showResultMessage(true)
                    } else {
                        markAnswerWrong(layoutAnswer2, tvVariantValue2, tvVariantNumber2)
                        showResultMessage(false)
                    }
                }

                layoutAnswer3.setOnClickListener {
                    if (trainer.checkAnswer(2)) {
                        markAnswerCorrect(layoutAnswer3, tvVariantValue3, tvVariantNumber3)
                        showResultMessage(true)
                    } else {
                        markAnswerWrong(layoutAnswer3, tvVariantValue3, tvVariantNumber3)
                        showResultMessage(false)
                    }
                }

                layoutAnswer4.setOnClickListener {
                    if (trainer.checkAnswer(3)) {
                        markAnswerCorrect(layoutAnswer4, tvVariantValue4, tvVariantNumber4)
                        showResultMessage(true)
                    } else {
                        markAnswerWrong(layoutAnswer4, tvVariantValue4, tvVariantNumber4)
                        showResultMessage(false)
                    }
                }
            }
        }
    }

    private fun markAnswerNeutral(
        layoutAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumber: TextView,
    ) {

        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.textVariantsColor
            )
        )

        tvVariantNumber.apply {
            background =
                ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_variants
                )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.textVariantsColor
                )
            )
        }
    }


    private fun markAnswerWrong(
        layoutAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumber: TextView,
    ) {
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container_wrong
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.textVariantsColorWrong
            )
        )

        tvVariantNumber.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))

        tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_wrong
        )
    }

    private fun markAnswerCorrect(
        layoutAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumber: TextView,
    ) {
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container_correct
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_correct
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                this@MainActivity, R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity, R.color.textVariantsColorCorrect
            )
        )
    }

    private fun showResultMessage(isCorrect: Boolean) {
        val color: Int
        val messageText: Int
        val resultIconResource: Int

        if (isCorrect) {
            color = ContextCompat.getColor(this, R.color.textVariantsColorCorrect)
            messageText = R.string.title_correct
            resultIconResource = R.drawable.ic_correct
        } else {
            color = ContextCompat.getColor(this, R.color.textVariantsColorWrong)
            messageText = R.string.title_wrong
            resultIconResource = R.drawable.ic_wrong
        }

        with(binding) {
            btnSkip.isVisible = false
            layoutResult.isVisible = true
            btnContinue.setTextColor(color)
            layoutResult.setBackgroundColor(color)
            ivResultIcon.setImageResource(resultIconResource)
            tvResultMessage.setText(messageText)
        }

    }
}