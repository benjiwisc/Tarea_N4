package com.example.tarea_n2.data.remote.dto

import com.google.gson.annotations.SerializedName

class CategoryDto (
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("encargado") val encargado: String
)