package com.example.xmlkotlinflow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.xmlkotlinflow.R
import com.example.xmlkotlinflow.databinding.ActivityButtonIncBinding
import com.example.xmlkotlinflow.viewmodels.ViewModelBtnIncLiveData

class ButtonIncLiveDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityButtonIncBinding
    private val viewModel by viewModels<ViewModelBtnIncLiveData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonIncBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitViews()
    }

    private fun InitViews() = with(binding) {
        btnInc.setOnClickListener {
            increment()
        }
        btnSame.setOnClickListener {
            updateWithSameValue()
        }
        viewModel.count.observe(this@ButtonIncLiveDataActivity, { number ->
            tvDisplay.text = number.toString()
            viewModel.updatedCalled()
        })

        viewModel.called.observe(this@ButtonIncLiveDataActivity, { number ->
            tvCalled.text = number.toString()
        })
    }

    private fun updateWithSameValue() = with(viewModel) {
        sameValue()
    }

    private fun increment() = with(viewModel) {
        incrementCount()
    }


}
