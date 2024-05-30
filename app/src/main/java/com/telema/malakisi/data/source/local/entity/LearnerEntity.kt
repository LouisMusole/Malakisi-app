package com.telema.malakisi.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.telema.malakisi.domain.model.Learner
import kotlinx.serialization.Serializable

@Entity
data class LearnerEntity(
    @PrimaryKey
    val id: String,
    val idprogram : String,
    val names: String,
    val imageurl: String,
    val program: String
){
    fun toLearner() = Learner(
        id=id,
        idprogram = idprogram,
        names = names,
        imageurl = imageurl,
        program = program)
}
