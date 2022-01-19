package com.example.xmlkotlinflow.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlkotlinflow.databinding.ActivityCountDownBinding
import com.example.xmlkotlinflow.viewmodels.ViewModelCountDownLiveData

class CountDownLiveDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountDownBinding
    private val viewModel by viewModels<ViewModelCountDownLiveData>()
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountDownBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        btnStart.setOnClickListener {
            startCounter()
        }
        viewModel.counter.observe(this@CountDownLiveDataActivity, { number ->
            tvDisplay.text = number.toString()
            viewModel.updatedCalled()
            if(number == 0){
                running = false
            }
        })
        viewModel.called.observe(this@CountDownLiveDataActivity, {  number ->
            tvCalled.text = number.toString()
        })
    }

    private fun startCounter() {
        if (!running) {
            running = true
            viewModel.startCounter()
        }
    }
}