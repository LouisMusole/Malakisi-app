package com.telema.malakisi.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.telema.malakisi.data.source.local.entity.AgendaEntity
import com.telema.malakisi.data.source.local.entity.InstructorEntity
import com.telema.malakisi.data.source.local.entity.LearnerEntity
import com.telema.malakisi.data.source.local.entity.ProgramEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MalakisiLocalDao {

    @Query("SELECT COUNT(*) FROM programentity")
    suspend fun getProgramsCount() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrograms(programs : List<ProgramEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLearners(learners : List<LearnerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstructors(instructors : List<InstructorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgendas(agendas : List<AgendaEntity>)

    @Query("SELECT * FROM programentity ORDER BY name ASC")
    fun getPrograms() : Flow<List<ProgramEntity>>

    @Query("SELECT * FROM instructorentity WHERE idprogram = :idprogram ORDER BY names ASC")
    suspend fun getInstructors(idprogram : String) : List<InstructorEntity>

    @Query("SELECT * FROM learnerentity WHERE idprogram = :idprogram ORDER BY names ASC")
    suspend fun getLearners(idprogram : String) : List<LearnerEntity>

    @Query("SELECT * FROM agendaentity WHERE idprogram = :idprogram ORDER BY letter ASC")
    suspend fun getAgendas(idprogram : String) : List<AgendaEntity>
}