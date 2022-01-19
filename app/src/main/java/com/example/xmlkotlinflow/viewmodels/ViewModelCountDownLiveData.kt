package com.example.xmlkotlinflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelCountDownLiveData : ViewModel() {

    private val _called : MutableLiveData<Int> = MutableLiveData(0)
    val called : LiveData<Int> get() = _called

    private val _counter : MutableLiveData<Int> = MutableLiveData(10)
    val counter : LiveData<Int> get() = _counter

    fun startCounter() = viewModelScope.launch {
        val startingValue = 10
        var currentValue = startingValue

        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            _counter.value = currentValue
        }
    }

    fun updatedCalled() {
        _called.value = (_called.value ?: 0) + 1
    }
}