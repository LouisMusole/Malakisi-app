package com.telema.malakisi.domain.repository

import com.telema.malakisi.data.repository.MalakisiRepository
import com.telema.malakisi.data.source.local.dao.MalakisiLocalDao
import com.telema.malakisi.data.source.remote.MalakisiApi
import com.telema.malakisi.domain.Result
import com.telema.malakisi.domain.model.Agenda
import com.telema.malakisi.domain.model.Instructor
import com.telema.malakisi.domain.model.Learner
import com.telema.malakisi.domain.model.Program
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MalakisiRepositoryImpl(
    private val malakisiApi: MalakisiApi,
    private val malakisiDao: MalakisiLocalDao
) : MalakisiRepository {


    override fun loadData(): Flow<Result<Boolean>> {
        return flow {
            emit(Result.Loading)
            if(malakisiDao.getProgramsCount()==0){
                val result = malakisiApi.getMalakisiData()
                malakisiDao.insertPrograms(result.programs.map { it.toProgramEntity() })
                malakisiDao.insertLearners(result.learners.map { it.toLearnerEntity() })
                malakisiDao.insertInstructors(result.instructors.map { it.toInstructorEntity() })
                malakisiDao.insertAgendas(result.agendas.map { it.toAgendaEntity() })
            }
            emit(Result.Success(true))
        }.catch {throwable->
            emit(Result.Error(throwable))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPrograms(): Flow<List<Program>> {
        return malakisiDao.getPrograms().map {list-> list.map { it.toProgram() } }
    }

    override suspend fun getLearners(idprogram: String): List<Learner> {
        return malakisiDao.getLearners(idprogram).map { it.toLearner() }
    }

    override suspend fun getInstructors(idprogram: String): List<Instructor> {
        return malakisiDao.getInstructors(idprogram).map { it.toInstructor() }
    }

    override suspend fun getAgendas(idprogram: String): List<Agenda> {
        return malakisiDao.getAgendas(idprogram).map { it.toAgenda() }
    }
}