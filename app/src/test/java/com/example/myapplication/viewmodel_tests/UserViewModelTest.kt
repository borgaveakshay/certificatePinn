package com.example.myapplication.viewmodel_tests


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.usecase.UserUseCase
import com.example.myapplication.utils.TestTransformer
import com.example.myapplication.utils.TestUtils
import com.example.myapplication.viewmodels.UserViewModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


class UserViewModelTest {


    private lateinit var mUserRepository: UserRepository

    private lateinit var mUserUseCase: UserUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun before() {
        mUserRepository = Mockito.mock(UserRepository::class.java)
        mUserUseCase = UserUseCase(TestTransformer(), mUserRepository)
    }


    @Test
    fun test() {

        Mockito.`when`(mUserUseCase.createObservable(2)).thenReturn(Observable.just(TestUtils.getMockUserUseCaseData()))
        val userViewModel = UserViewModel(mUserUseCase)
        userViewModel.getUserList(2).observeForever {
            if (it?.data?.size == 5)
                assert(true)
            else assert(false)
        }
    }

}