package com.telema.malakisi.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.telema.malakisi.domain.model.Instructor
import kotlinx.serialization.Serializable

@Entity
data class InstructorEntity(
    @PrimaryKey
    val id: String,
    val idprogram : String,
    val names: String,
    val imageurl: String,
    val bio: String,
){
    fun toInstructor()=Instructor(
        id = id,
        idprogram = idprogram,
        names = names,
        imageurl = imageurl,
        bio = bio
    )
}
