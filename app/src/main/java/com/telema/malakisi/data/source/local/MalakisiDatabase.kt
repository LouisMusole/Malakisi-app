package com.telema.malakisi.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.telema.malakisi.data.source.local.dao.MalakisiLocalDao
import com.telema.malakisi.data.source.local.entity.AgendaEntity
import com.telema.malakisi.data.source.local.entity.InstructorEntity
import com.telema.malakisi.data.source.local.entity.LearnerEntity
import com.telema.malakisi.data.source.local.entity.ProgramEntity

@Database(
    entities = [
        ProgramEntity::class,
        LearnerEntity::class,
        InstructorEntity::class,
        AgendaEntity::class
    ],
    version = 2
)
abstract class MalakisiDatabase : RoomDatabase() {
    abstract fun malakisiDao(): MalakisiLocalDao
}