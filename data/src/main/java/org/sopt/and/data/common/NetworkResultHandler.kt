package org.sopt.and.data.common

import org.sopt.and.domain.exception.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import org.json.JSONObject
import org.json.JSONException

inline fun <T> execute(block: () -> T): Result<T> = runCatching {
    Result.Success(block())
}.getOrElse {
    Result.Error(handleNetworkError(it))
}

fun handleNetworkError(e: Throwable): NetworkError {
    return when (e) {
        is ConnectException -> ConnectError
        is SocketTimeoutException -> TimeoutError
        is UnknownHostException -> UnknownHostError
        is HttpException -> mapHttpError(e)
        else -> UnknownError
    }
}

fun mapHttpError(e: HttpException): NetworkError {
    return try {
        val statusCode = e.code().toString()
        val errorBody = e.response()?.errorBody()?.string()
        val errorCode = extractErrorCode(errorBody).toString()
        HttpCodeError(statusCode, errorCode)
    } catch (ex: Exception) {
        UnknownError
    }
}

fun extractErrorCode(errorBody: String?): String? {
    return try {
        errorBody?.let { JSONObject(it).getString("code") }
    } catch (e: JSONException) {
        null
    }
}

