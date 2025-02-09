package com.kaecals.data.source

import com.kaecals.data.model.response.MainResponse

interface RemoteSource {
    suspend fun getAge(name: String): MainResponse
}