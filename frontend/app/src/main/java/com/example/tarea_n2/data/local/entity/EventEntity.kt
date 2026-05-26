package com.example.tarea_n2.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "fecha_hora") val fecha_hora: String,
    @ColumnInfo(name = "lugar") val lugar: String,
    @ColumnInfo(name = "representante") val representante: String,
    @ColumnInfo(name = "category") val category: String
)
