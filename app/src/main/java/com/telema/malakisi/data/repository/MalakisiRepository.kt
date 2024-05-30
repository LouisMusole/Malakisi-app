package com.telema.malakisi.data.repository

import com.telema.malakisi.domain.Result
import com.telema.malakisi.domain.model.Agenda
import com.telema.malakisi.domain.model.Instructor
import com.telema.malakisi.domain.model.Learner
import com.telema.malakisi.domain.model.Program
import kotlinx.coroutines.flow.Flow

interface MalakisiRepository {
    fun loadData() : Flow<Result<Boolean>>

    suspend fun getPrograms():Flow<List<Program>>

    suspend fun getLearners(idprogram : String) : List<Learner>

    suspend fun getInstructors(idprogram : String) : List<Instructor>

    suspend fun getAgendas(idprogram : String) : List<Agenda>

}