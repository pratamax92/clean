package com.cartenz.component_base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), KoinComponent {
    abstract fun getCoroProvider(): CoroutineContext

    protected fun executeUseCase(action: suspend () -> Unit) {
//        _viewState.value = Loading()
        launch { action() }
    }

    inline fun launch(
        coroutineContext: CoroutineContext = getCoroProvider(),
        crossinline block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(coroutineContext) { block() }
    }


}
