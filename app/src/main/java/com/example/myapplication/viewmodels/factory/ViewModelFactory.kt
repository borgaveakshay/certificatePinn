package com.example.myapplication.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.usecase.UserUseCase
import com.example.myapplication.viewmodels.UserViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory, KoinComponent {

    private val mUserUseCase: UserUseCase by inject()


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        return when (modelClass.simpleName) {

            UserViewModel::class.java.simpleName -> UserViewModel(mUserUseCase) as T
            else -> null!!
        }

    }
}