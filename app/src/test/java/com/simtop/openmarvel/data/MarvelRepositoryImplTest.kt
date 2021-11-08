package com.simtop.openmarvel.data

import com.simtop.openmarvel.*
import com.simtop.openmarvel.data.remotesources.MarvelRemoteSource
import com.simtop.openmarvel.data.repository.MarvelRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MarvelRepositoryImplTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val marvelRemoteSource: MarvelRemoteSource = mockk()

    @Test
    fun `when remote source succeeds we get a success response`() = coroutineScope.runBlocking {
        // Arrange

        val getMarvelCharacters = MarvelRepositoryImpl(marvelRemoteSource)

        coEvery { marvelRemoteSource.getMarvelCharacterList(any(), any(), any(), any()) } returns fakeMarvelApiResponse

        // Act

        val result = getMarvelCharacters.getMarvelCharacters()

        // Assert

        coVerify(exactly = 1) { marvelRemoteSource.getMarvelCharacterList(any(), any(), any(), any()) }

        result shouldBeEqualTo fakeMarvelCharacters
    }

    @Test(expected = Exception::class)
    fun `when remote source fails we throw an exception`() = coroutineScope.runBlocking {
        // Arrange
        val getMarvelCharacters = MarvelRepositoryImpl(marvelRemoteSource)

        coEvery { marvelRemoteSource.getMarvelCharacterList(any(), any(), any(), any()) } throws fakeException

        // Act

        getMarvelCharacters.getMarvelCharacters()

        // Assert
    }
}