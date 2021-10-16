package com.kyawhut.codetest.ui.upcoming

import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.utils.network.NetworkResponse

/**
 * @author kyawhtut
 * @date 10/16/21
 */
interface UpComingRepository {

    fun getUpComingList(): List<MoviesEntity>

    suspend fun fetchUpComingList(
        page: Int,
        callback: (NetworkResponse<Pair<Int, List<MoviesEntity>>>) -> Unit
    )
}
