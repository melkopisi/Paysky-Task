package me.melkopisi.payskytask.core.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.melkopisi.payskytask.domain.remote.exceptions.Exceptions
import retrofit2.Response

fun <T> Flow<Response<T>>.parseResponse(): Flow<T> {
    return map { response ->
        if (response.isSuccessful) {
            if (response.body() != null) response.body()!! else throw Exceptions.NoDataException()
        } else {
            when (response.code()) {
                in 400..404 -> {
                    throw Exceptions.DataRetrievingFailException("Data Retrieving Fail")
                }

                in 500..504 -> throw Exceptions.ServerError()
                else -> throw Exceptions.GeneralError()
            }
        }
    }
}