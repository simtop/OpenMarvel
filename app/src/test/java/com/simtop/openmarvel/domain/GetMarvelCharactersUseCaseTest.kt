package com.simtop.openmarvel.domain

import com.simtop.openmarvel.MainCoroutineScopeRule
import com.simtop.openmarvel.core.mapLeft
import com.simtop.openmarvel.core.mapRight
import com.simtop.openmarvel.domain.repository.MarvelRepository
import com.simtop.openmarvel.domain.usecases.GetMarvelCharactersUseCase
import com.simtop.openmarvel.fakeException
import com.simtop.openmarvel.fakeMarvelCharacters
import com.simtop.openmarvel.runBlocking
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

@ExperimentalCoroutinesApi
class GetMarvelCharactersUseCaseTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val marvelRepository: MarvelRepository = mockk()

    @Test
    fun `when repository succeeds we get success response`() = coroutineScope.runBlocking {
        // Arrange

        coEvery { marvelRepository.getMarvelCharacters(any(), any()) } returns fakeMarvelCharacters

        val getMarvelCharactersUseCase = GetMarvelCharactersUseCase(marvelRepository)

        // Act

        val response = getMarvelCharactersUseCase(getMarvelCharactersUseCase.Params(
            anyInt(),
            anyInt()
        ))

        // Assert

        coVerify(exactly = 1) { marvelRepository.getMarvelCharacters(any(), any()) }

        response.mapRight {
            it shouldBeEqualTo fakeMarvelCharacters
        }
    }

    @Test
    fun `when repository fails we get error response`() = coroutineScope.runBlocking {
        // Arrange

        coEvery { marvelRepository.getMarvelCharacters(any(), any()) } throws fakeException

        val getMarvelCharactersUseCase = GetMarvelCharactersUseCase(marvelRepository)

        // Act

        val response = getMarvelCharactersUseCase(getMarvelCharactersUseCase.Params(
            anyInt(),
            anyInt()
        ))

        //Assert

        coVerify(exactly = 1) { marvelRepository.getMarvelCharacters(any(), any()) }

        response.mapLeft {
            it shouldBeEqualTo fakeException
        }
    }
}