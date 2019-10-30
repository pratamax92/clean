package com.cartenz.component_base_network

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

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
 */

inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (R) -> Unit,
    fetchFromCacheAction: () -> R
): com.cartenz.component_base_domain.Result<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return com.cartenz.component_base_domain.Success(databaseEntity.mapToDomainModel())
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) com.cartenz.component_base_domain.Success(cachedModel.mapToDomainModel()) else com.cartenz.component_base_domain.Failure(
                com.cartenz.component_base_domain.HttpError(Throwable(DB_ENTRY_ERROR))
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

/**
 * Use this when communicating only with the api service
 */
inline fun <R : DomainMapper<U>, U : Any> Response<R>.getData(): com.cartenz.component_base_domain.Result<U> {
    try {
        onSuccess {
            return com.cartenz.component_base_domain.Success(it.mapToDomainModel())
        }
        onFailure {
            com.cartenz.component_base_domain.Failure(
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
