package com.examples.marsphotos

import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import com.examples.marsphotos.fake.FakeDataSource
import com.examples.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.examples.marsphotos.rule.TestDispatcherRule
import junit.framework.Assert
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )
//            Assert.assertEquals(
//                MarsUiState.Success(
//                    "
//                ),
//                marsViewModel.marsUiState
//            )
        }
}