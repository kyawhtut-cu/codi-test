package com.kyawhut.codetest.data.db.datasource

import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.data.db.utils.MoviesType

/**
 * @author kyawhtut
 * @date 10/16/21
 */
interface MoviesDataSource {

    fun insert(vararg data: MoviesEntity)

    fun getMoviesByID(moviesID: Int): MoviesEntity?

    fun getAll(source: MoviesType): List<MoviesEntity>

    fun delete(source: MoviesType)
}
