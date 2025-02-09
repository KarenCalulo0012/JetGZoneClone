package com.kaecals.viewmodel

import androidx.lifecycle.ViewModel
import com.kaecals.data.model.response.MainResponse
import com.kaecals.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state: StateFlow<MainState> get() = _state.asStateFlow()

    fun processIntent(intent: MainIntent) {
        when(intent) {
            is MainIntent.GetName -> getViewModelText(intent.name)
        }
    }

    private fun getViewModelText(name: String) {
        //Removed mainRepository for testing purposes
        _state.value = MainState.Success(
            MainResponse(
                name = "Irikjan",
                age = 21,
                count = 1
            )
        )
    }
}

sealed class MainIntent {
    data class GetName(val name: String): MainIntent()
}

sealed class MainState {
    data object Loading: MainState()
    data class Success(val data: MainResponse): MainState()
    data class Error(val message: String): MainState()
}
