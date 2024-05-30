package com.telema.malakisi.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.telema.malakisi.domain.model.Agenda
import kotlinx.serialization.Serializable

@Entity
data class AgendaEntity(
    @PrimaryKey
    val id: Int,
    val idprogram: String,
    val letter : String,
    val week: String,
    val lesson: String
){
    fun toAgenda() = Agenda(
        id = id,
        idprogram = idprogram,
        letter = letter,
        week = week,
        lesson = lesson
    )
}
