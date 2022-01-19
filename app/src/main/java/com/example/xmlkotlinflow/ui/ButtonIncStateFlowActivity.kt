package com.example.xmlkotlinflow.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.xmlkotlinflow.databinding.ActivityButtonIncBinding
import com.example.xmlkotlinflow.viewmodels.ViewModelBtnIncFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

class ButtonIncStateFlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityButtonIncBinding
    private val viewModel by viewModels<ViewModelBtnIncFlow>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonIncBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        btnInc.setOnClickListener {
            increment()
        }
        btnSame.setOnClickListener {
            viewModel.sameValue()
        }
        lifecycleScope.launchWhenStarted {
            viewModel.count.collectLatest { number ->
                binding.tvDisplay.text = number.toString()
                viewModel.updatedCalled()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.called.collectLatest { number ->
                binding.tvCalled.text = number.toString()
            }
        }
    }


    private fun increment() {
        viewModel.incrementCount()
    }
}