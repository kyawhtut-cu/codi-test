package com.kyawhut.codetest.ui.detail

import com.kyawhut.codetest.base.BaseViewModel
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository,
) : BaseViewModel() {

    fun getMovies(moviesID: Int): MoviesEntity? {
        return repository.getMovies(moviesID)
    }
}
