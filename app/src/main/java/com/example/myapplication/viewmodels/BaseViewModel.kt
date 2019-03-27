package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val compositeDisposable by lazy { CompositeDisposable() }
    var isError: Boolean = false

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}