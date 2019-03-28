package com.example.myapplication.viewmodel_tests


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.domain_layer.repository.UserRepository
import com.example.myapplication.domain_layer.usecase.UserUseCase
import com.example.myapplication.presentation_layer.utils.TestTransformer
import com.example.myapplication.presentation_layer.utils.TestUtils
import com.example.myapplication.presentation_layer.viewmodels.UserViewModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito


class UserViewModelTest : KoinTest {



    private val mUserUseCase: UserUseCase by inject()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun before() {
        val module = module {
            single {
                UserUseCase(TestTransformer())
            }
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

        Mockito.`when`(mUserUseCase.createObservable(2)).thenReturn(Observable.just(TestUtils.getMockUserUseCaseData()))
        val userViewModel = UserViewModel()
        userViewModel.getUserList(2).observeForever {
            if (it?.data?.size == 5)
                assert(true)
            else assert(false)
        }
    }

}