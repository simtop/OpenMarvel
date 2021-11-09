package com.simtop.openmarvel.presentation.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.simtop.openmarvel.R
import com.simtop.openmarvel.core.BaseBindView
import com.simtop.openmarvel.core.convertUrlToHTTPS
import com.simtop.openmarvel.databinding.RowCharactersListBinding
import com.simtop.openmarvel.domain.models.MarvelHero

class CharacterItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseBindView<MarvelHero>(context, attrs, defStyleAttr) {

    private val rowCharacterListBinding: RowCharactersListBinding =
        RowCharactersListBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    init {
        if (isInEditMode) {
            val marvelHero = MarvelHero(
                id = "1",
                name = "Iron Man",
                description = "Richest hero",
                marvelThumbnailUrl = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            )
            bind(marvelHero)
        }
    }

    override fun bind(value: MarvelHero, showDetail: Boolean) {
        with(rowCharacterListBinding) {
            heroName.text = value.name
            Glide.with(context)
                .load(value.marvelThumbnailUrl.convertUrlToHTTPS())
                .error(R.drawable.blue_image)
                .into(heroImage)

            if(showDetail) {
                with(heroDescription) {
                    visibility = VISIBLE
                    text = value.description
                }
            }
        }
    }
}