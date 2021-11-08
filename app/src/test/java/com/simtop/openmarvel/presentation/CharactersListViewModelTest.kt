package com.simtop.openmarvel.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.simtop.openmarvel.*
import com.simtop.openmarvel.core.Either
import com.simtop.openmarvel.domain.usecases.GetMarvelCharactersUseCase
import com.simtop.openmarvel.presentation.home.CharactersListViewModel
import com.simtop.openmarvel.presentation.home.CharactersListViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharactersListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val getMarvelCharactersUseCase: GetMarvelCharactersUseCase = mockk()

    @Test
    fun `when usecase succeeds we get success state`() = coroutineScope.runBlocking {
        // Arrange

        coEvery {
            getMarvelCharactersUseCase(any())
        } returns Either.Right(fakeMarvelCharacters)

        // Act

        coroutineScope.dispatcher.pauseDispatcher()

        val charactersListViewModel =
            CharactersListViewModel(coroutineScope.testDispatcherProvider, getMarvelCharactersUseCase)

        val liveDataUnderTest = charactersListViewModel.charactersListViewState.testObserver()

        coroutineScope.dispatcher.resumeDispatcher()

        // Assert

        coVerify(exactly = 1) {
            getMarvelCharactersUseCase(any())
        }

        liveDataUnderTest.observedValues.size shouldBeEqualTo 2
        liveDataUnderTest.observedValues[0] shouldBeEqualTo CharactersListViewState.Loading
        liveDataUnderTest.observedValues[1] shouldBeEqualTo CharactersListViewState.Success(
            fakeMarvelCharacters
        )
    }

    @Test
    fun `when usecase fails we get error state`() = coroutineScope.runBlocking {
        // Arrange

        coEvery {
            getMarvelCharactersUseCase(any())
        } returns Either.Left(fakeException)

        // Act

        coroutineScope.dispatcher.pauseDispatcher()

        val charactersListViewModel =
            CharactersListViewModel(coroutineScope.testDispatcherProvider, getMarvelCharactersUseCase)

        val liveDataUnderTest = charactersListViewModel.charactersListViewState.testObserver()

        coroutineScope.dispatcher.resumeDispatcher()

        // Assert

        coVerify(exactly = 1) {
            getMarvelCharactersUseCase(any())
        }

        liveDataUnderTest.observedValues.size shouldBeEqualTo 2
        liveDataUnderTest.observedValues[0] shouldBeEqualTo CharactersListViewState.Loading
        liveDataUnderTest.observedValues[1] shouldBeEqualTo CharactersListViewState.Error(fakeErrorName)
    }
}