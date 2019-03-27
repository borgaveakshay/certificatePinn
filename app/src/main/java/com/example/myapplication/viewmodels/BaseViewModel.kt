package com.example.myapplication.viewmodels

import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.utils.App
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : AndroidViewModel(App.INSTANCE) {

    val compositeDisposable by lazy { CompositeDisposable() }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}