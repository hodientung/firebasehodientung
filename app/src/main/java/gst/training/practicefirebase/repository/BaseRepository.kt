package gst.training.practicefirebase.repository

import gst.training.practicefirebase.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.error("data isn't active", null)
                    }
                    else -> {
                        Resource.error("data isn't active",null)
                    }
                }
            }
        }
    }
}