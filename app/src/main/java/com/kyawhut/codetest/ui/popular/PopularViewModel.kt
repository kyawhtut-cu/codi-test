package com.kyawhut.codetest.ui.popular

import com.kyawhut.codetest.base.BaseViewModel
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: PopularRepository,
) : BaseViewModel() {

    /* that is use to avoid from fetching data come back from another fragment*/
    var isViewCreated: Boolean = false

    private var page: Int = 1
    val isFirstPage: Boolean
        get() = page == 1
    private var totalPage: Int = 1
    private var isLoading: Boolean = false

    val popularMovies: List<MoviesEntity>
        get() = repository.getPopularList()

    fun fetchPopular(
        isForceRefresh: Boolean = false,
        callback: (NetworkResponse<Pair<Int, List<MoviesEntity>>>) -> Unit
    ) {
        if (isForceRefresh) page = 1
        if (page > totalPage || isLoading) return
        viewModelScope {
            repository.fetchPopularList(page) {
                callback(it)
                isLoading = it.isLoading
                if (it.isSuccess) {
                    page += 1
                    totalPage = it.data?.first ?: 0
                }
            }
        }
    }
}
