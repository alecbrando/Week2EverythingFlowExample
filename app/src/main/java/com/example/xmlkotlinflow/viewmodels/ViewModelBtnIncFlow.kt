package com.example.xmlkotlinflow.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelBtnIncFlow : ViewModel() {

    private val _called = MutableStateFlow(0)
    val called get() = _called.asStateFlow()

    private val _count = MutableStateFlow(0)
    val count get() = _count.asStateFlow()

    fun incrementCount() {
        _count.value += 1
    }

    fun sameValue() {
        val value = _count.value
        _count.value = value
    }

    fun updatedCalled() {
        _called.value += 1
    }
}