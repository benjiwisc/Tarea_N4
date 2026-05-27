package com.example.tarea_n2.data.remote.service

import com.example.tarea_n2.data.remote.dto.CategoryDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryApiService {
    @GET("categorias")
    suspend fun getCategory(): List<CategoryDto>
    @POST("categorias")
    suspend fun createCategory(@Body categoria: CategoryDto): CategoryDto
}