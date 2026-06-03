package com.example.tarea_n2.data.remote.service

import com.example.tarea_n2.data.remote.dto.EventDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EventApiService {
    @GET("eventos")
    suspend fun getEventos(): List<EventDto>

    @GET("eventos/{id}")
    suspend fun getEventoById(@Path("id") id: Int): EventDto

    @POST("eventos")
    suspend fun createEvento(@Body evento: EventDto): EventDto

    @PUT("eventos/{id}")
    suspend fun updateEvento(@Path("id") id: Int, @Body evento: EventDto): EventDto

    @DELETE("eventos/{id}")
    suspend fun deleteEvento(@Path("id") id: Int)
}