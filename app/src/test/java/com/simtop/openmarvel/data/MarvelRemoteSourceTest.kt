package com.simtop.openmarvel.data

import com.simtop.openmarvel.FAKE_JSON
import com.simtop.openmarvel.TestMockWebService
import com.simtop.openmarvel.fakeMarvelApiResponse
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyString
import java.net.HttpURLConnection

class MarvelRemoteSourceTest : TestMockWebService() {

    @Test
    fun `when service succeeds we get a success response`() {
        // Arrange

        val expectedResult = fakeMarvelApiResponse
        mockHttpResponse(FAKE_JSON, HttpURLConnection.HTTP_OK)

        // Act

        val response = runBlocking {
            apiService.getMarvelCharacters(anyString(), anyString(), anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
        }

        // Assert

        response.toString() shouldBeEqualTo expectedResult.toString()
    }

    @Test(expected = Exception::class)
    fun `when service fails succeeds we throw an exception`() {
        // Arrange

        mockHttpResponse(FAKE_JSON, HttpURLConnection.HTTP_UNAVAILABLE)

        // Act

        runBlocking {
            apiService.getMarvelCharacters(anyString(), anyString(), anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
        }

        // Assert
    }
}