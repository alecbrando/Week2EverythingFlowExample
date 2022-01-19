package com.example.xmlkotlinflow.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
*   Differences between live data and kotlin flow (flow, stateflow, sharedflow(one time UI events))
*   * kotlin flow is a reactive stream
*   * live data is an observable state holder for UI
*   * stateflow is a state holder
*   * live data is lifecycle aware (no memory leaks)
*   * kotlin flow is made off of coroutines
*   * kotlin flow is for continuous streams of data
*   * stateflow needs default value
*   * stateflow is a hot stream - still emits with no subscribers
*   * kotlin flow is a cold stream - only emits when it has subscribers
* */

class ViewModelCountDownFlow : ViewModel() {

    private val _called = MutableStateFlow<Int>(0)
    val called: StateFlow<Int> = _called.asStateFlow()

    private val _counter = MutableStateFlow<Int>(10)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        // LOADING()
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            // SUCCESS()
            emit(currentValue)
        }
    }

    fun startCounter() = viewModelScope.launch(Dispatchers.IO) {
        countDownFlow.collect { number ->
            _counter.emit(number)
        }
    }

    fun updateCalled() {
        _called.value += 1
    }
}

// Some useful operators
// onEach -> additional step to send data somewhere else or do something else with it
// count -> returns number that fits expression
// reduce -> (accum, value) which allows you to return a sum each iteration
// fold -> same as reduce but has an initial value
// map -> map over data and manipulate it
// filter -> allows you to filter over the data and set conditions


// Coroutine
//launch { // context of the parent, main runBlocking coroutine
//    println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
//}
//launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
//    println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
//}
//launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
//    println("Default               : I'm working in thread ${Thread.currentThread().name}")
//}
//launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
//    println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//}

