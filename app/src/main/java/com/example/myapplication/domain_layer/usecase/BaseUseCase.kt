package com.example.myapplication.domain_layer.usecase

import com.example.myapplication.presentation_layer.utils.Transformer
import io.reactivex.Observable

abstract class BaseUseCase<T, R>(private val transformer: Transformer<T>) {


    abstract fun createObservable(request: R?): Observable<T>


    fun observable(request: R?): Observable<T> {

        return createObservable(request).compose(transformer)
    }
}