package ir.interview.idmb.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<US : UiState, UE : UiEvent, UEF : UiEffect> : ViewModel() {


    private val initializeState: US by lazy { createInitialState() }
    protected abstract fun createInitialState(): US

    protected val _state = MutableStateFlow(initializeState)
    val state: StateFlow<US> get() = _state

    protected val _event = Channel<UE>()
    protected val _effect = Channel<UEF>()
    val effect get() = _effect

    init {
        handleEvents()
    }

    protected abstract fun handleEvents()

    fun setEvent(event: UE) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    fun setEffect(effect: UEF) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}