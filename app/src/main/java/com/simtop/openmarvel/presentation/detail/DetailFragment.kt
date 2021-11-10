package com.simtop.openmarvel.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.simtop.openmarvel.R
import com.simtop.openmarvel.core.observe
import com.simtop.openmarvel.core.showToast
import com.simtop.openmarvel.databinding.FragmentDetailBinding
import com.simtop.openmarvel.domain.models.MarvelHero
import com.simtop.openmarvel.domain.usecases.GetMarvelCharacterDetailUseCase
import com.simtop.openmarvel.presentation.home.CharactersListViewState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    @Inject lateinit var heroDetailViewModelAssistedFactory: HeroDetailViewModel.AssistedFactory

    private var assistedCharacterId: Int = 0

    private val heroDetailViewModel: HeroDetailViewModel by viewModels {
        HeroDetailViewModel.provideFactory(
            heroDetailViewModelAssistedFactory, assistedCharacterId
        )
    }

    private var _detailFragmentBinding: FragmentDetailBinding? = null
    private val detailFragmentBinding get() = _detailFragmentBinding!!

    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var test: GetMarvelCharacterDetailUseCase

    override fun onResume() {
        if (heroDetailViewModel.heroDetailVieState.value is HeroDetailVieState.EmptyState) heroDetailViewModel.getHeroDetail(args.detailHeroId.toInt())
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        _detailFragmentBinding = binding

        assistedCharacterId = args.detailHeroId.toInt()

        observe(
            heroDetailViewModel.heroDetailVieState,
            { viewState -> viewState?.let { treatViewState(it) } })
    }

    private fun treatViewState(heroDetailVieState: HeroDetailVieState<MarvelHero>) {
        when(heroDetailVieState) {
            is HeroDetailVieState.Error -> treatError(heroDetailVieState.result)
            is HeroDetailVieState.Loading -> {
                detailFragmentBinding.apply {
                    progressBar.visibility = View.VISIBLE
                    characterDetailView.visibility = View.GONE
                    emptyState.visibility = View.GONE
                }
            }
            is HeroDetailVieState.Success -> treatSuccess(heroDetailVieState.result)
            HeroDetailVieState.EmptyState -> {
                detailFragmentBinding.apply {
                    progressBar.visibility = View.GONE
                    characterDetailView.visibility = View.GONE
                    emptyState.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun treatError(exception: String?) {
        if (detailFragmentBinding.characterDetailView.visibility != View.VISIBLE) heroDetailViewModel.showEmptyState()
        exception?.let { requireActivity().showToast(it) }
    }

    private fun treatSuccess(marvelHero: MarvelHero) {
        detailFragmentBinding.apply {
            progressBar.visibility = View.GONE
            characterDetailView.visibility = View.VISIBLE
            characterDetailView.bind(marvelHero, true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _detailFragmentBinding = null
    }
}