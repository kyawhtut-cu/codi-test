package com.kyawhut.codetest.data.db.datasourceImpl

import com.kyawhut.codetest.data.db.dao.MoviesDao
import com.kyawhut.codetest.data.db.datasource.MoviesDataSource
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.data.db.utils.MoviesType
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 10/16/21
 */
class MoviesDataSourceImpl @Inject constructor(
    private val dao: MoviesDao
) : MoviesDataSource {

    override fun insert(vararg data: MoviesEntity) {
        dao.insert(*data)
    }

    override fun getMoviesByID(moviesID: Int): MoviesEntity? {
        return dao.getByID(moviesID)
    }

    override fun getAll(source: MoviesType): List<MoviesEntity> {
        return dao.getAll(source)
    }

    override fun delete(source: MoviesType) {
        dao.delete(source)
    }
}
