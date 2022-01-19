package com.example.xmlkotlinflow.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlkotlinflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        btnCountLive.setOnClickListener {
            val intent = Intent(this@MainActivity, CountDownLiveDataActivity::class.java)
            startActivity(intent)
        }
        btnCountStateFlow.setOnClickListener {
            val intent = Intent(this@MainActivity, CountDownStateFlowActivity::class.java)
            startActivity(intent)
        }
        btnIncLiveData.setOnClickListener {
            val intent = Intent(this@MainActivity, ButtonIncLiveDataActivity::class.java)
            startActivity(intent)
        }
        btnIncStateFlow.setOnClickListener {
            val intent = Intent(this@MainActivity, ButtonIncStateFlowActivity::class.java)
            startActivity(intent)
        }
    }
}