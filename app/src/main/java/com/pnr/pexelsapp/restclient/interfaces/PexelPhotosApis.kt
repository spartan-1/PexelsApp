package com.pnr.pexelsapp.restclient.interfaces

import com.pnr.pexelsapp.model.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface PexelsPhotosApis {

    /**
     * search Pexel Photos
     *
     * @param query
     * @param pageIndex
     * @return
     */
    @GET("search")
    @Headers("Authorization:563492ad6f9170000100000138bf96f0b51742d88cff108631124730")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") pageIndex: Int
    ): Response<ApiResult>
}