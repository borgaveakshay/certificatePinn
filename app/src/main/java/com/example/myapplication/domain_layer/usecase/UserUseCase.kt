package com.example.myapplication.domain_layer.usecase

import com.example.myapplication.presentation_layer.models.Response
import com.example.myapplication.domain_layer.repository.UserRepository
import com.example.myapplication.presentation_layer.utils.Transformer
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserUseCase(transformer: Transformer<Response>) : BaseUseCase<Response, Int>(transformer), KoinComponent {

    private val userRepository: UserRepository by inject()

    override fun createObservable(request: Int?): Observable<Response> {

        return userRepository.getUsers(request ?: 0)
    }
}