package com.example.xmlkotlinflow.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.xmlkotlinflow.databinding.ActivityCountDownBinding
import com.example.xmlkotlinflow.viewmodels.ViewModelCountDownFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountDownStateFlowActivity : AppCompatActivity() {

    private val viewmodel by viewModels<ViewModelCountDownFlow>()
    private lateinit var binding: ActivityCountDownBinding
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        tvDisplay.text = viewmodel.counter.value.toString()
        btnStart.setOnClickListener {
            startCounter()
        }
        lifecycleScope.launchWhenStarted {
            viewmodel.counter.collect { number ->
                binding.tvDisplay.text = number.toString()
                viewmodel.updateCalled()
                if (number == 0) {
                    running = false
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewmodel.called.collect { number ->
                binding.tvCalled.text = number.toString()
            }
        }
    }

    private fun startCounter() {
        if (!running) {
            running = true
            viewmodel.startCounter()
        }
    }
}