package com.kaecals.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    fun getViewModelText(): String {
        return "From Viewmodel"
    }
}