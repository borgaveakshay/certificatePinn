package com.example.myapplication.presentation_layer.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.presentation_layer.viewmodels.UserViewModel
import org.koin.core.KoinComponent

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory, KoinComponent {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        return when (modelClass.simpleName) {

            UserViewModel::class.java.simpleName -> UserViewModel() as T
            else -> null!!
        }

    }
}