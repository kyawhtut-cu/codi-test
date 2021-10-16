package com.kyawhut.codetest.ui.detail

import com.kyawhut.codetest.data.db.dao.MoviesDao
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 10/16/21
 */
class DetailRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
) : DetailRepository {

    override fun getMovies(moviesID: Int): MoviesEntity? {
        return moviesDao.getByID(moviesID)
    }
}
