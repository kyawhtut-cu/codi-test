package com.kyawhut.codetest.ui.upcoming

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kyawhut.codetest.R
import com.kyawhut.codetest.base.BaseFragmentWithVM
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.databinding.FragmentUpcomingBinding
import com.kyawhut.codetest.rv.adapter.MoviesAdapter
import com.kyawhut.codetest.utils.extension.onLoadMoreEnd
import com.kyawhut.codetest.utils.network.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@AndroidEntryPoint
class UpcomingFragment : BaseFragmentWithVM<FragmentUpcomingBinding, UpComingViewModel>() {

    override val layoutID: Int
        get() = R.layout.fragment_upcoming

    override val vm: UpComingViewModel by viewModels()

    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter { index, item ->
            findNavController().navigate(
                UpcomingFragmentDirections.actionUpcomingFragmentToDetailFragment(
                    item.id
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null && !vm.isViewCreated) {
            vm.isViewCreated = true
            vm.fetchUpComing(true, ::onUpComingNetworkState)
        }

        adapter.addAll(vm.upComingMovies)

        vb.apply {
            adapter = this@UpcomingFragment.adapter
            executePendingBindings()
        }

        vb.swipeRefresh.setOnRefreshListener {
            vm.fetchUpComing(true, ::onUpComingNetworkState)
        }

        vb.rvUpcoming.onLoadMoreEnd {
            vm.fetchUpComing(false, ::onUpComingNetworkState)
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
            Snackbar.make(vb.rvUpcoming, errorMessage, Snackbar.LENGTH_INDEFINITE).apply {
                setAction("Retry") {
                    vm.fetchUpComing(false, ::onUpComingNetworkState)
                }
            }.show()
        }
    }
}
