package com.kyawhut.codetest.ui.detail

import com.kyawhut.codetest.data.db.entities.MoviesEntity

/**
 * @author kyawhtut
 * @date 10/16/21
 */
interface DetailRepository {

    fun getMovies(moviesID: Int): MoviesEntity?
}
