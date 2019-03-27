package com.example.myapplication.repository_tests

import com.example.myapplication.api.API
import com.example.myapplication.repository.implementation.UserRepositoryImpl
import com.example.myapplication.utils.TestUtils
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UserRepositoryTest {


    lateinit var mAPI: API


    @Before
    fun before() {

        mAPI = Mockito.mock(API::class.java)

    }


    @Test
    fun test() {

        Mockito.`when`(mAPI.getUsers(2)).thenReturn(Observable.just(TestUtils.getMockUserUseCaseData()))
        val userRepository = UserRepositoryImpl(mAPI)
        userRepository.getUsers(2).test()
            .assertValue { result ->

                result.data?.size == 5
            }
            .assertComplete()

    }


}