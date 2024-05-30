package com.telema.malakisi.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MalakisiDto(
    val learners : List<Learner>,
    val instructors : List<Instructor>,
    val programs : List<Program>,
    val agendas : List<Agenda>
)
