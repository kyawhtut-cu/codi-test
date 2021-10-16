package com.kyawhut.codetest.ui.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kyawhut.codetest.R
import com.kyawhut.codetest.base.BaseFragmentWithVM
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.databinding.FragmentPopularBinding
import com.kyawhut.codetest.rv.adapter.MoviesAdapter
import com.kyawhut.codetest.utils.extension.onLoadMoreEnd
import com.kyawhut.codetest.utils.network.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@AndroidEntryPoint
class PopularFragment : BaseFragmentWithVM<FragmentPopularBinding, PopularViewModel>() {

    override val layoutID: Int
        get() = R.layout.fragment_popular

    override val vm: PopularViewModel by viewModels()

    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter { index, item ->
            findNavController().navigate(
                PopularFragmentDirections.actionPopularFragmentToDetailFragment(
                    item.id
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null && !vm.isViewCreated) {
            vm.isViewCreated = true
            vm.fetchPopular(true, ::onUpComingNetworkState)
        }

        adapter.addAll(vm.popularMovies)

        vb.apply {
            adapter = this@PopularFragment.adapter
            executePendingBindings()
        }

        vb.swipeRefresh.setOnRefreshListener {
            vm.fetchPopular(true, ::onUpComingNetworkState)
        }

        vb.rvPopular.onLoadMoreEnd {
            vm.fetchPopular(false, ::onUpComingNetworkState)
        }
    }

    private fun onUpComingNetworkState(state: NetworkResponse<Pair<Int, List<MoviesEntity>>>) {
        vb.isLoading = state.isLoading
        if (state.isSuccess) {
            if (vm.isFirstPage) adapter.clear()
            adapter.addAll(state.data?.second ?: listOf())
        } else if (state.isError) {
            val errorMessage = if (state.error?.resId == 0) state.error?.message ?: ""
            else getString(state.error?.resId ?: 0)
            Snackbar.make(vb.rvPopular, errorMessage, Snackbar.LENGTH_INDEFINITE).apply {
                setAction("Retry") {
                    vm.fetchPopular(false, ::onUpComingNetworkState)
                }
            }.show()
        }
    }
}
