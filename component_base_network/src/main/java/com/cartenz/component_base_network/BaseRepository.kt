package com.cartenz.component_base_network

import com.cartenz.component_base_network.coroutine.CoroutineContextProvider
import com.cartenz.component_base_network.utils.Connectivity
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository<T : Any, R : com.cartenz.component_base_network.DomainMapper<T>> :
    KoinComponent {
    private val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()
    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> com.cartenz.component_base_domain.Result<T>,
        dbDataProvider: suspend () -> R
    ): com.cartenz.component_base_domain.Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) com.cartenz.component_base_domain.Success(dbResult.mapToDomainModel()) else com.cartenz.component_base_domain.Failure(
                    com.cartenz.component_base_domain.HttpError(Throwable(DB_ENTRY_ERROR))
                )
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> com.cartenz.component_base_domain.Result<T>
    ): com.cartenz.component_base_domain.Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                com.cartenz.component_base_domain.Failure(
                    com.cartenz.component_base_domain.HttpError(Throwable(DB_ENTRY_ERROR))
                )
            }
        }
    }

}