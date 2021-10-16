package com.kyawhut.codetest.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kyawhut.codetest.base.BaseAdapter
import com.kyawhut.codetest.base.BaseViewHolder
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.databinding.CardMoviesBinding
import com.kyawhut.codetest.rv.viewholder.MoviesViewHolder

/**
 * @author kyawhtut
 * @date 10/16/21
 */
class MoviesAdapter(
    itemClickListener: (Int, MoviesEntity) -> Unit
) : BaseAdapter<MoviesEntity>(itemClickListener) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*, MoviesEntity> {
        return MoviesViewHolder(
            CardMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener
        )
    }
}
