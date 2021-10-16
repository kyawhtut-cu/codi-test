package com.kyawhut.codetest.rv.viewholder

import com.kyawhut.codetest.base.BaseViewHolder
import com.kyawhut.codetest.data.db.entities.MoviesEntity
import com.kyawhut.codetest.databinding.CardMoviesBinding

/**
 * @author kyawhtut
 * @date 10/16/21
 */
class MoviesViewHolder(
    vb: CardMoviesBinding,
    itemClickListener: (Int, MoviesEntity) -> Unit
) : BaseViewHolder<CardMoviesBinding, MoviesEntity>(vb, itemClickListener) {

    override fun bind() {
        vb.apply {
            movies = data
            executePendingBindings()
        }
    }
}
