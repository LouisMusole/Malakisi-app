package com.telema.malakisi.domain.ui

import kotlinx.serialization.Serializable


@Serializable
data object Home

@Serializable
data class DetailsProgram(
    val id: String,
    val name: String,
    val description: String,
    val coverimageurl: String,
    val duration: Int
)
