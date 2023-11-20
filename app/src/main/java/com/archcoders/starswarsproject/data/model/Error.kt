package com.archcoders.starswarsproject.data.model

import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

sealed interface ErrorModel {
    class Server(val code: Int) : ErrorModel
    object Connectivity : ErrorModel
    class Unknown(val message: String) : ErrorModel
}

fun Throwable.toError() = when (this) {
    is IOException -> ErrorModel.Connectivity
    is HttpException -> ErrorModel.Server(code())
    else -> ErrorModel.Unknown(message = message ?: "")
}

inline fun <T> tryCall(action: () -> T): ErrorModel? = try {
    action()
    null
} catch (e: Exception) {
    e.toError()
}