package com.telema.malakisi.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telema.malakisi.data.repository.MalakisiRepository
import com.telema.malakisi.domain.model.Program
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListProgramScreenViewModel(private val repository: MalakisiRepository) : ViewModel() {

    private val _programs = MutableStateFlow(listOf<Program>())
    var programs = _programs.asStateFlow()

    init {
        getPrograms()
    }

    private fun getPrograms(){
        viewModelScope.launch {
            repository.getPrograms().collect{
                _programs.value = it
            }

        }
    }
}