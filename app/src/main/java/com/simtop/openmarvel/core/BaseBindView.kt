package com.simtop.openmarvel.core

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.simtop.openmarvel.domain.models.MarvelHero

abstract class BaseBindView<in T> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    abstract fun bind(value: T, showDetail: Boolean = false)
}