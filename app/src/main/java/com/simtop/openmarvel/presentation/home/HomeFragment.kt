package com.simtop.openmarvel.presentation.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.simtop.openmarvel.R
import com.simtop.openmarvel.core.observe
import com.simtop.openmarvel.core.showToast
import com.simtop.openmarvel.databinding.FragmentHomeBinding
import com.simtop.openmarvel.domain.models.MarvelCharacters
import com.simtop.openmarvel.domain.models.MarvelHero
import com.simtop.openmarvel.presentation.adapters.MarvelHeroAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val charactersListViewModel: CharactersListViewModel by viewModels()

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val fragmentHomeBinding get() = _fragmentHomeBinding!!

    private lateinit var heroAdapter: MarvelHeroAdapter

    override fun onResume() {
        if (charactersListViewModel.charactersListViewState.value is CharactersListViewState.EmptyState) charactersListViewModel.getAllMarvelCharacters()
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        _fragmentHomeBinding = binding

        setUpToolbar()

        setUpCharactersRecyclerView()

        observe(
            charactersListViewModel.charactersListViewState,
            { viewState -> viewState?.let { treatViewState(it) } })

    }

    private fun setUpToolbar() {
        fragmentHomeBinding.toolbar.title = requireContext().getString(R.string.home_fragment_label)
    }


    private fun setUpCharactersRecyclerView() {
        heroAdapter = MarvelHeroAdapter(
            listener = ::onHeroClicked
        )
        fragmentHomeBinding.charactersRecyclerview.apply {
            adapter = heroAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun treatViewState(charactersListViewState: CharactersListViewState<MarvelCharacters>) {
        when (charactersListViewState) {
            CharactersListViewState.EmptyState -> {
                fragmentHomeBinding.apply {
                    progressBar.visibility = View.GONE
                    charactersRecyclerview.visibility = View.GONE
                    emptyState.visibility = View.VISIBLE
                }
            }
            is CharactersListViewState.Error -> treatError(charactersListViewState.result)
            CharactersListViewState.Loading -> {
                fragmentHomeBinding.apply {
                    progressBar.visibility = View.VISIBLE
                    charactersRecyclerview.visibility = View.GONE
                    emptyState.visibility = View.GONE
                }
            }
            is CharactersListViewState.Success -> treatSuccess(charactersListViewState.result)
        }
    }


    private fun treatSuccess(marvelCharacters: MarvelCharacters) {

        heroAdapter.submitList(marvelCharacters.marvelHeroResponses)

        fragmentHomeBinding.apply {
            progressBar.visibility = View.GONE
            charactersRecyclerview.visibility = View.VISIBLE
            emptyState.visibility = View.GONE
        }
    }

    private fun onHeroClicked(marvelHero: MarvelHero) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(marvelHero.id)
        findNavController().navigate(action)
    }


    private fun treatError(exception: String?) {
        if (fragmentHomeBinding.charactersRecyclerview.visibility != View.VISIBLE) charactersListViewModel.showEmptyState()
        exception?.let { requireActivity().showToast(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentHomeBinding = null
    }
}