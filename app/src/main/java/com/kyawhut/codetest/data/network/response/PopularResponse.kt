package com.kyawhut.codetest.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@Keep
data class PopularResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MoviesResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)
