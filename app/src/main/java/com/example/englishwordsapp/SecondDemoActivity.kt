package com.example.englishwordsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.englishwordsapp.databinding.ActivitySecondDemoBinding

class SecondDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondDemoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        with(binding) {
            btnOpenFirstActivity.setOnClickListener {
                val intent = Intent(this@SecondDemoActivity, FirstDemoActivity::class.java)
                startActivity(intent)
            }

            val text = intent.getStringExtra("EXTRA_KEY_TEXT")
            val number = intent.getIntExtra("EXTRA_KEY_NUMBER", 0)

            tvString.text = text
            tvNumber.text = number.toString()

        }
    }
}