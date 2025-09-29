package com.example.englishwordsapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.englishwordsapp.databinding.ActivityFirstDemoBinding
import java.io.Serializable

class FirstDemoActivity : AppCompatActivity() {

    private var _binding: ActivityFirstDemoBinding? = null

    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityFirstDemoBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFirstDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. без передачи данных
        // 2. с передачей данных
        // 3. с ожиданием результата

        val word = ExtraWord(
            "Kamilla",
            "Камилла"
        )

        binding.btnOpenSecondActivity.setOnClickListener {
            val intent = Intent(this@FirstDemoActivity, SecondDemoActivity::class.java).apply {
                putExtra("EXTRA_KEY_TEXT", "don't panic")
                putExtra("EXTRA_KEY_NUMBER", 42)
                putExtra("EXTRA_KEY_WORD", word)
            }

//            val bundle = Bundle()
//            bundle.putString("EXTRA_KEY_TEXT", "don't panic")
//            bundle.putInt("EXTRA_KEY_NUMBER", 41)
//            bundle.putSerializable("EXTRA_KEY_WORD", word) // Parcelable
//            intent.putExtras(bundle)
            intent.putExtras(
                bundleOf(
                    "EXTRA_KEY_TEXT" to "don't panic",
                    "EXTRA_KEY_NUMBER" to 41,
                    "EXTRA_KEY_WORD" to word,

                    )
            )

            startActivity(intent)
        }
    }
    // Serializable
    // Parcelable
    // Bundle

//    data class ExtraWord(
//        val original: String,
//        val translate: String,
//        var learned: Boolean = false,
//    ) : Parcelable {
//        override fun describeContents(): Int {
//            return 0
//        }
//
//        override fun writeToParcel(dest: Parcel, flags: Int) {
//            dest.writeString(original)
//            dest.writeString(translate)
//            dest.writeByte(if (learned) 1 else 0)
//        }
//
//        constructor(parcel: Parcel) : this(
//            original = parcel.readString().toString(),
//            translate = parcel.readString().toString(),
//            learned = parcel.readByte() != 0.toByte()
//        )
//
//        companion object CREATOR : Parcelable.Creator<ExtraWord> {
//            override fun createFromParcel(source: Parcel): ExtraWord? {
//                return ExtraWord(source)
//            }
//
//            override fun newArray(size: Int): Array<out ExtraWord?>? {
//                return arrayOfNulls(size)
//            }
//        }
//    }

    data class ExtraWord(
        val original: String,
        val translate: String,
        var learned: Boolean = false,
    ) : Serializable

}