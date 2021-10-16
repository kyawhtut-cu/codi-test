package com.kyawhut.codetest.ui.upcoming

import com.kyawhut.codetest.data.db.dao.MoviesDao
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.data.db.entities.MoviesEntity.Companion.toMovieEntity
import com.kyawhut.codetest.data.db.utils.MoviesType
import com.kyawhut.codetest.data.network.API
import com.kyawhut.codetest.utils.network.NetworkResponse
import com.kyawhut.codetest.utils.network.execute
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 10/16/21
 */
class UpComingRepositoryImpl @Inject constructor(
    private val api: API,
    private val moviesDao: MoviesDao,
) : UpComingRepository {

    override fun getUpComingList(): List<MoviesEntity> {
        return moviesDao.getAll(MoviesType.UPCOMING)
    }

    override suspend fun fetchUpComingList(
        page: Int,
        callback: (NetworkResponse<Pair<Int, List<MoviesEntity>>>) -> Unit
    ) {
        NetworkResponse.loading(callback)
        val response = execute {
            api.getUpComing(page)
        }

        if (response.isSuccess) {
            response.data?.results?.map {
                it.toMovieEntity(MoviesType.UPCOMING)
            }?.also {
                if (page == 1) moviesDao.delete(MoviesType.UPCOMING)
                moviesDao.insert(*it.toTypedArray())
            }.also {
                NetworkResponse.success(
                    (response.data?.totalPages ?: 0) to (it ?: listOf()),
                    callback
                )
            }
        } else {
            NetworkResponse.error(response.error, callback)
        }
    }
}
