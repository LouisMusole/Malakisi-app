package com.telema.malakisi.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telema.malakisi.data.repository.MalakisiRepository
import com.telema.malakisi.domain.model.Agenda
import com.telema.malakisi.domain.model.Instructor
import com.telema.malakisi.domain.model.Learner
import com.telema.malakisi.domain.model.Program
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsProgramViewModel(private val repository: MalakisiRepository) : ViewModel() {

    private var _instructors = MutableStateFlow<List<Instructor>>(emptyList())
    var instructors = _instructors.asStateFlow()
    private val _learners = MutableStateFlow<List<Learner>>(emptyList())
    var learners = _learners.asStateFlow()
    private val _agendas = MutableStateFlow<List<Agenda>>(emptyList())
    var agendas = _agendas.asStateFlow()

    fun getDetailsProgram(program: Program) {
        viewModelScope.launch {
            _instructors.value = repository.getInstructors(program.id)
            _learners.value = repository.getLearners(program.id)
            _agendas.value = repository.getAgendas(program.id)
        }
    }
}