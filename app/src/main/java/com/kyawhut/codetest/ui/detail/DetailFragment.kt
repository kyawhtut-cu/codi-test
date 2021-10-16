package com.kyawhut.codetest.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kyawhut.codetest.R
import com.kyawhut.codetest.base.BaseActivity
import com.kyawhut.codetest.base.BaseFragmentWithVM
import com.kyawhut.codetest.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@AndroidEntryPoint
class DetailFragment : BaseFragmentWithVM<FragmentDetailBinding, DetailViewModel>() {

    override val layoutID: Int
        get() = R.layout.fragment_detail

    override val vm: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(vm.getMovies(args.moviesId)) {
            if (this == null) {
                findNavController().popBackStack()
                return@with
            }
            (requireActivity() as BaseActivity<*>).supportActionBar?.title = moviesTitle
            vb.moviesData = this
            vb.executePendingBindings()
        }
    }
}
