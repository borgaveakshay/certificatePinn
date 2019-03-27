package com.example.myapplication.viewmodel_tests


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.usecase.UserUseCase
import com.example.myapplication.utils.TestTransformer
import com.example.myapplication.utils.TestUtils
import com.example.myapplication.viewmodels.UserViewModel
import com.example.myapplication.viewmodels.factory.ViewModelFactory
import com.jraska.livedata.test
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.mockito.Mockito


class UserViewModelTest : KoinComponent {


    private lateinit var mUserRepository: UserRepository

    private lateinit var mUserUseCase: UserUseCase

    private lateinit var mViewModelFactory: ViewModelFactory

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()




    @Before
    fun before() {

        startKoin { }
        mViewModelFactory = ViewModelFactory()
        mUserRepository = Mockito.mock(UserRepository::class.java)
        mUserUseCase = UserUseCase(TestTransformer(), mUserRepository)
    }


    @Test
    fun test() {

        Mockito.`when`(mUserUseCase.createObservable(2)).thenReturn(Observable.just(TestUtils.getMockUserUseCaseData()))
        val userViewModel = UserViewModel(mUserUseCase)
        userViewModel.getUserList(2).test()
            .map {
                it?.let { result ->
                    result.data?.size == 5

                }
            }
            .assertHasValue()
    }

}