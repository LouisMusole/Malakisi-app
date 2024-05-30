package com.telema.malakisi.domain.model

import com.telema.malakisi.data.source.local.entity.LearnerEntity
import kotlinx.serialization.Serializable

@Serializable
data class Learner(
    val id: String,
    val idprogram : String,
    val names: String,
    val imageurl: String,
    val program: String
){
    fun toLearnerEntity() = LearnerEntity(
        id = id,
        idprogram = idprogram,
        names = names,
        imageurl = imageurl,
        program = program
    )
}
