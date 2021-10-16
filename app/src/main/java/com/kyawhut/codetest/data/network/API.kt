package com.kyawhut.codetest.data.network

import com.kyawhut.codetest.data.network.response.PopularResponse
import com.kyawhut.codetest.data.network.response.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author kyawhtut
 * @date 10/16/21
 */
interface API {

    @GET("3/movie/upcoming")
    suspend fun getUpComing(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): UpcomingResponse

    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): PopularResponse
}
