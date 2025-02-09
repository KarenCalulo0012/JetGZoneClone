package com.kaecals.data.source

import com.kaecals.data.api.ApiService
import com.kaecals.data.model.response.MainResponse

class RemoteSourceImpl(private val apiService: ApiService) : RemoteSource {
    override suspend fun getAge(name: String): MainResponse {
        val request = apiService.getAge(name)
        return request
    }
}