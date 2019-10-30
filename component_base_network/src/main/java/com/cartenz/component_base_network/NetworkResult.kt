package com.cartenz.component_base_network

import com.cartenz.component_base_domain.HttpError
import retrofit2.Response
import java.io.IOException

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (com.cartenz.component_base_domain.HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run {
        action(
            com.cartenz.component_base_domain.HttpError(
                Throwable(
                    message()
                ), code()
            )
        )
    }
}

inline fun <R : DomainMapper<U>, U : Any> Response<R>.getData(
    successLogic: (R) -> Unit,
    failedLogic: (it: HttpError) -> U?
): com.cartenz.component_base_domain.Result<U> {
    try {
        onSuccess {
            successLogic(it)
            return com.cartenz.component_base_domain.Success(
                it.mapToDomainModel()
            )
        }
        onFailure {
            val cachedModel: Any? = failedLogic(it)
            if (cachedModel != null) {
                com.cartenz.component_base_domain.Success(cachedModel)
            } else {
                com.cartenz.component_base_domain.Failure(
                    com.cartenz.component_base_domain.HttpError(Throwable(DB_ENTRY_ERROR))
                )
            }

            return com.cartenz.component_base_domain.Failure(
                com.cartenz.component_base_domain.HttpError(Throwable(GENERAL_NETWORK_ERROR))
            )
        }
        return com.cartenz.component_base_domain.Failure(
            com.cartenz.component_base_domain.HttpError(
                Throwable(GENERAL_NETWORK_ERROR)
            )
        )
    } catch (e: IOException) {
        return com.cartenz.component_base_domain.Failure(
            com.cartenz.component_base_domain.HttpError(
                Throwable(GENERAL_NETWORK_ERROR)
            )
        )
    }
}