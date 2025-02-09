package com.kaecals.data.repository

import com.kaecals.data.model.response.MainResponse

interface MainRepository {
    suspend fun getName(name: String): MainResponse
}