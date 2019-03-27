package com.example.myapplication.usecase_tests

import com.example.myapplication.repository.UserRepository
import com.example.myapplication.usecase.UserUseCase
import com.example.myapplication.utils.TestTransformer
import com.example.myapplication.utils.TestUtils
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UserUseCaseTest {


    private lateinit var mUserRepository: UserRepository


    @Before
    fun before() {
        mUserRepository = Mockito.mock(UserRepository::class.java)
    }

    @Test
    fun test() {

        Mockito.`when`(mUserRepository.getUsers(2)).thenReturn(Observable.just(TestUtils.getMockUserUseCaseData()))
        val userUseCase = UserUseCase(TestTransformer(), mUserRepository)
        userUseCase.observable(2).test()
            .assertValue { result ->
                result.data?.size == 5
            }
            .assertComplete()
    }

}