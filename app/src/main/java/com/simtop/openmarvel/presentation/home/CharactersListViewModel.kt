package com.simtop.openmarvel.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simtop.openmarvel.core.CoroutineDispatcherProvider
import com.simtop.openmarvel.core.Either
import com.simtop.openmarvel.domain.models.MarvelCharacters
import com.simtop.openmarvel.domain.usecases.GetMarvelCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcherProvider,
    private val getMarvelCharactersUseCase: GetMarvelCharactersUseCase
) : ViewModel() {

    private val _charactersListViewState =
        MutableLiveData<CharactersListViewState<MarvelCharacters>>()
    val charactersListViewState: LiveData<CharactersListViewState<MarvelCharacters>>
        get() = _charactersListViewState

    init {
        getAllMarvelCharacters()
    }

    fun getAllMarvelCharacters(offset: Int = 0, limit : Int = 100) {
        _charactersListViewState.postValue(CharactersListViewState.Loading)
        viewModelScope.launch(coroutineDispatcher.io) {
            getMarvelCharactersUseCase(getMarvelCharactersUseCase.Params(offset = offset, limit = limit))
                .also(::process)
        }
    }

    private fun process(result: Either<Exception, MarvelCharacters>) {
        result.either(
            {
                _charactersListViewState.postValue(CharactersListViewState.Error(it.message))
            },
            {
                if (it.marvelHeroResponses.isEmpty()) _charactersListViewState.postValue(CharactersListViewState.EmptyState)
                else _charactersListViewState.postValue(CharactersListViewState.Success(it))
            }
        )
    }

    fun showEmptyState() {
        _charactersListViewState.postValue(CharactersListViewState.EmptyState)
    }
}

sealed class CharactersListViewState<out T> {
    data class Success<out T>(val result: T) : CharactersListViewState<T>()
    data class Error(val result: String?) : CharactersListViewState<Nothing>()
    object Loading : CharactersListViewState<Nothing>()
    object EmptyState : CharactersListViewState<Nothing>()
}