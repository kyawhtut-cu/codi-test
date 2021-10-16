package com.kyawhut.codetest.ui.popular

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
class PopularRepositoryImpl @Inject constructor(
    private val api: API,
    private val moviesDao: MoviesDao,
) : PopularRepository {

    override fun getPopularList(): List<MoviesEntity> {
        return moviesDao.getAll(MoviesType.POPULAR)
    }

    override suspend fun fetchPopularList(
        page: Int,
        callback: (NetworkResponse<Pair<Int, List<MoviesEntity>>>) -> Unit
    ) {
        NetworkResponse.loading(callback)
        val response = execute {
            api.getPopular(page)
        }

        if (response.isSuccess) {
            response.data?.results?.map {
                it.toMovieEntity(MoviesType.POPULAR)
            }?.also {
                if (page == 1) moviesDao.delete(MoviesType.POPULAR)
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
