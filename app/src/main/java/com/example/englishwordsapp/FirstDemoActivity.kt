package com.example.englishwordsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.englishwordsapp.databinding.ActivityFirstDemoBinding

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

        binding.btnOpenSecondActivity.setOnClickListener {
            val intent = Intent(this@FirstDemoActivity, SecondDemoActivity::class.java)
            intent.putExtra("EXTRA_KEY_TEXT", "don't panic")
            intent.putExtra("EXTRA_KEY_NUMBER", 42)
            startActivity(intent)
        }
    }

}