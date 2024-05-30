package com.telema.malakisi.domain.model

import com.telema.malakisi.data.source.local.entity.AgendaEntity
import kotlinx.serialization.Serializable

@Serializable
data class Agenda(
    val id: Int,
    val idprogram: String,
    val week: String,
    val letter: String,
    val lesson: String
){
    fun toAgendaEntity() = AgendaEntity(
        id = id,
        idprogram = idprogram,
        letter = letter,
        week = week,
        lesson = lesson
    )
}
