package com.telema.malakisi.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.telema.malakisi.domain.model.Program
import kotlinx.serialization.Serializable

@Entity
data class ProgramEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val coverimageurl: String,
    val duration: Int,
){
    fun toProgram()= Program(id,name,description,coverimageurl,duration)
}
