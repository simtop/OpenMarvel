package com.simtop.openmarvel.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.simtop.openmarvel.R
import com.simtop.openmarvel.databinding.FragmentDetailBinding
import com.simtop.openmarvel.domain.usecases.GetMarvelCharacterDetailUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _detailFragmentBinding: FragmentDetailBinding? = null
    private val detailFragmentBinding get() = _detailFragmentBinding!!

    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var test: GetMarvelCharacterDetailUseCase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        _detailFragmentBinding = binding

        detailFragmentBinding.detailText.text = args.detailHeroId

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _detailFragmentBinding = null
    }
}