package com.telema.malakisi.domain.model

import com.telema.malakisi.data.source.local.entity.ProgramEntity
import kotlinx.serialization.Serializable

@Serializable
data class Program(
    val id: String,
    val name: String,
    val description: String,
    val coverimageurl: String,
    val duration: Int,
){
    fun toProgramEntity() = ProgramEntity(
        id = id,
        name = name,
        description = description,
        coverimageurl = coverimageurl,
        duration = duration)
}
