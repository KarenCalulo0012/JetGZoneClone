package com.kaecals.data.repository

import com.kaecals.data.model.response.MainResponse
import com.kaecals.data.source.RemoteSource

class MainRepositoryImpl(private val remoteSource: RemoteSource): MainRepository {
    override suspend fun getName(name: String): MainResponse {
        return remoteSource.getAge(name)
    }
}