package com.example.testafterwhile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(): ViewModel() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun setName(name: String) {
        _name.value = name
    }
}