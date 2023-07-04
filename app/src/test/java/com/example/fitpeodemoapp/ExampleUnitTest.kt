package com.example.fitpeodemoapp

import com.example.fitpeodemoapp.core.api.ApiHelper
import com.example.fitpeodemoapp.core.api.ApiService
import com.example.fitpeodemoapp.feature.data.model.Photos
import com.example.fitpeodemoapp.feature.data.repository.PhotosRepository
import com.example.fitpeodemoapp.feature.ui.home.viewmodel.PhotosViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Mock
    private lateinit var mockApiService: ApiHelper

    private lateinit var repository: PhotosRepository

    @Before
    fun setup() {
        repository = PhotosRepository(mockApiService)
    }

    @Test
    fun `getDataFromApi returns data`() {
        // Arrange
        val testData = listOf(Photos(1, "Item 1"), Photos(2, "Item 2"))
        runBlocking {
            Mockito.`when`(mockApiService.getPhoto()).thenReturn(testData)

            // Act
            val result = repository.getPhotos()

            // Assert
            assertEquals(testData, result)
        }
    }
}

private fun Any.thenReturn(testData: List<Photos>) {
    return Unit
}
