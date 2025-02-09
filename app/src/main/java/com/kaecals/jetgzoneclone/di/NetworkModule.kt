package com.kaecals.jetgzoneclone.di


import com.kaecals.data.api.ApiService
import com.kaecals.data.api.RetrofitInstance
import com.kaecals.data.repository.MainRepository
import com.kaecals.data.repository.MainRepositoryImpl
import com.kaecals.data.source.LocalDataSource
import com.kaecals.data.source.LocalDataSourceImpl
import com.kaecals.data.source.RemoteSource
import com.kaecals.data.source.RemoteSourceImpl

import org.koin.dsl.module

val networkModule = module {
    single<ApiService> { RetrofitInstance.api }
    // Source
    single<RemoteSource> { RemoteSourceImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl() }

    //Repository
    single<MainRepository> { MainRepositoryImpl(get()) }

}