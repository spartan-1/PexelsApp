package com.pnr.pexelsapp.restclient.webservices

import com.pnr.pexelsapp.model.ApiResult
import com.pnr.pexelsapp.restclient.interfaces.PexelsPhotosApis
import dagger.Module
import retrofit2.Response
import javax.inject.Inject

@Module
class PexelsWebService @Inject constructor(private var pexelsPhotosApis: PexelsPhotosApis) {

    /**
     * Webservice helper
     *
     * @param queryString
     * @param pageCount
     * @return
     */
    suspend fun searchPexel(
        queryString: String,
        pageCount: Int
    ): Response<ApiResult>? {
        return try {
            pexelsPhotosApis.search(queryString, pageCount)
        } catch (e: Exception) {
            //when there is no internet connection or other issue
            null
        }
    }
}