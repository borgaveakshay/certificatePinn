package com.example.myapplication.usecase_tests

import com.example.myapplication.repository.UserRepository
import com.example.myapplication.usecase.UserUseCase
import com.example.myapplication.utils.TestTransformer
import com.example.myapplication.utils.TestUtils
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito

class UserUseCaseTest : KoinTest {


    private val mUserRepository: UserRepository by inject()


    @Before
    fun before() {


        val module = module {

            single {
                Mockito.mock(UserRepository::class.java)
            }
        }

        startKoin {
            modules(module)
        }
    }

    @Test
    fun test() {

        Mockito.`when`(mUserRepository.getUsers(2)).thenReturn(Observable.just(TestUtils.getMockUserUseCaseData()))
        val userUseCase = UserUseCase(TestTransformer())
        userUseCase.observable(2).test()
            .assertValue { result ->
                result.data?.size == 5
            }
            .assertComplete()
    }

}