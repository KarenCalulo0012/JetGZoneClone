package com.kaecals.data.model.response

data class Response<T>(
    val code: Int,
    val message: String,
    val data: T,
)

typealias NormalResponse = Response<String>