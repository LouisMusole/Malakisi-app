package com.telema.malakisi.domain.model

import com.telema.malakisi.data.source.local.entity.InstructorEntity
import kotlinx.serialization.Serializable

@Serializable
data class Instructor(
    val id: String,
    val idprogram : String,
    val names: String,
    val imageurl: String,
    val bio: String,
){
    fun toInstructorEntity() = InstructorEntity(
        id = id,
        idprogram = idprogram,
        names = names,
        imageurl = imageurl,
        bio = bio
    )
}
