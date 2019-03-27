package com.example.myapplication.usecase

import com.example.myapplication.utils.Transformer
import io.reactivex.Observable

abstract class BaseUseCase<T, R>(private val transformer: Transformer<T>) {


    abstract fun createObservable(request: R?): Observable<T>


    fun observable(request: R?): Observable<T> {

        return createObservable(request).compose(transformer)
    }
}