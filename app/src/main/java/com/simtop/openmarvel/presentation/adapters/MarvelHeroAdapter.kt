package com.simtop.openmarvel.presentation.adapters

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.simtop.openmarvel.core.BaseBindView
import com.simtop.openmarvel.core.ViewWrapper
import com.simtop.openmarvel.domain.models.MarvelHero
import com.simtop.openmarvel.presentation.customviews.CharacterItemView

class MarvelHeroAdapter(private val listener: ((MarvelHero) -> Unit)?): ListAdapter<MarvelHero, ViewWrapper<BaseBindView<MarvelHero>>>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MarvelHero>() {
            override fun areItemsTheSame(oldItem: MarvelHero, newItem: MarvelHero): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MarvelHero, newItem: MarvelHero): Boolean =
                oldItem == newItem
        }
    }

    private fun onCreateItemView(parent: ViewGroup, viewType: Int): BaseBindView<MarvelHero> {
        return CharacterItemView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onBindViewHolder(holder: ViewWrapper<BaseBindView<MarvelHero>>, position: Int) {
        holder.view.bind(getItem(position))
        holder.view.setOnClickListener {
            listener?.invoke(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<BaseBindView<MarvelHero>> =
        ViewWrapper(onCreateItemView(parent, viewType))
}