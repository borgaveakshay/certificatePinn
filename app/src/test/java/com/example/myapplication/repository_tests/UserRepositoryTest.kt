package com.example.myapplication.repository_tests

import com.example.myapplication.data_layer.api.API
import com.example.myapplication.data_layer.repo_impl.UserRepositoryImpl
import com.example.myapplication.presentation_layer.utils.TestUtils
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito

class UserRepositoryTest : KoinTest {


    private val mAPI: API by inject()


    @Before
    fun before() {

        val module = module {
            single {
                Mockito.mock(API::class.java)
            }
        }
        startKoin {

            modules(module)
        }


    }

    @Test
    fun test() {

        Mockito.`when`(mAPI.getUsers(2)).thenReturn(Observable.just(TestUtils.getMockUserUseCaseData()))
        val userRepository = UserRepositoryImpl()
        userRepository.getUsers(2).test()
            .assertValue { result ->

                result.data?.size == 5
            }
            .assertComplete()

    }


}