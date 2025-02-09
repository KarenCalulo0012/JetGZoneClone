package com.kaecals.data.api

import com.kaecals.data.model.response.MainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getAge(@Query("name") name: String): MainResponse
}