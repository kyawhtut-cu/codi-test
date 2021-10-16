package com.kyawhut.codetest.ui.popular

import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.utils.network.NetworkResponse

/**
 * @author kyawhtut
 * @date 10/16/21
 */
interface PopularRepository {

    fun getPopularList(): List<MoviesEntity>

    suspend fun fetchPopularList(
        page: Int,
        callback: (NetworkResponse<Pair<Int, List<MoviesEntity>>>) -> Unit
    )
}
