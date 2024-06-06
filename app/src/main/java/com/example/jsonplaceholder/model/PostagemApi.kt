package com.example.jsonplaceholder.model

import com.example.jsonplaceholder.services.Mensagem
import com.example.jsonplaceholder.services.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostagemApi {
    @GET("posts/{id}/comments")
    suspend fun recuperarMensagem(
        @Path("comments") id: Int
    ): Response<List<Mensagem>>

    @GET("posts/{id}")
    suspend fun recuperarPostagens(
        @Path("id") id: Int
    ): Response<Postagem>

}