package com.example.tarea_n2.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tarea_n2.ui.model.Category

@Entity
data class CategoryEntity(
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "encargado") val encargado:String,
)
