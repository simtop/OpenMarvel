package com.simtop.openmarvel.presentation.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.simtop.openmarvel.R
import com.simtop.openmarvel.core.CoroutineDispatcherProvider
import com.simtop.openmarvel.data.network.MarvelService
import com.simtop.openmarvel.data.remotesources.MarvelRemoteSource
import com.simtop.openmarvel.di.MarvelBaseUrlModule.BASE_URL_NAME
import com.simtop.openmarvel.domain.repository.MarvelRepository
import com.simtop.openmarvel.domain.usecases.GetMarvelCharactersUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val charactersListViewModel: CharactersListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}