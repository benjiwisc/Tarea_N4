package com.example.tarea_n2.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("fecha_hora") val fecha_hora: String,
    @SerializedName("lugar") val lugar: String,
    @SerializedName("representante") val representante: String,
    @SerializedName("category") val category: String
)