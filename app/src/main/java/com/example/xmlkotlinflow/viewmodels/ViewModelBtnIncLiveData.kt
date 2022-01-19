package com.example.xmlkotlinflow.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelBtnIncLiveData : ViewModel() {

    private val _called = MutableLiveData(0)
    val called get() = _called

    private val _count = MutableLiveData<Int>(0)
    val count get() = _count

    fun incrementCount() {
        _count.value = (_count.value ?: 0) + 1
    }

    fun sameValue() {
        val value = _count.value!!
        _count.value = value
    }

    fun updatedCalled() {
        _called.value = (_called.value ?: 0) + 1
    }

}