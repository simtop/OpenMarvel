package com.simtop.openmarvel.presentation.detail

import androidx.lifecycle.*
import com.simtop.openmarvel.core.CoroutineDispatcherProvider
import com.simtop.openmarvel.core.Either
import com.simtop.openmarvel.domain.models.MarvelHero
import com.simtop.openmarvel.domain.usecases.GetMarvelCharacterDetailUseCase
import com.simtop.openmarvel.presentation.home.CharactersListViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class HeroDetailViewModel @AssistedInject constructor(
    private val coroutineDispatcher: CoroutineDispatcherProvider,
    private val getMarvelCharacterDetailUseCase: GetMarvelCharacterDetailUseCase,
    @Assisted private val characterId: Int
) : ViewModel() {

    private val _heroDetailVieState =
        MutableLiveData<HeroDetailVieState<MarvelHero>>()
    val heroDetailVieState: LiveData<HeroDetailVieState<MarvelHero>>
        get() = _heroDetailVieState

    init {
        getHeroDetail(characterId)
    }

    fun getHeroDetail(characterId: Int) {
        _heroDetailVieState.postValue(HeroDetailVieState.Loading)
        viewModelScope.launch(coroutineDispatcher.io) {
            getMarvelCharacterDetailUseCase(getMarvelCharacterDetailUseCase.Params(characterId = characterId))
                .also(::process)
        }
    }

    private fun process(result: Either<Exception, MarvelHero>) {
        result.either(
            {
                _heroDetailVieState.postValue(HeroDetailVieState.Error(it.message))
            },
            {
                _heroDetailVieState.postValue(HeroDetailVieState.Success(it))
            }
        )
    }

    fun showEmptyState() {
        _heroDetailVieState.postValue(HeroDetailVieState.EmptyState)
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(characterId: Int): HeroDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            characterId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(characterId) as T
            }
        }
    }
}

sealed class HeroDetailVieState<out T> {
    data class Success<out T>(val result: T) : HeroDetailVieState<T>()
    data class Error(val result: String?) : HeroDetailVieState<Nothing>()
    object Loading : HeroDetailVieState<Nothing>()
    object EmptyState : HeroDetailVieState<Nothing>()
}