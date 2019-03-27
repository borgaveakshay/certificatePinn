package com.example.myapplication.usecase

import com.example.myapplication.models.Response
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.utils.Transformer
import io.reactivex.Observable

class UserUseCase(
    transformer: Transformer<Response>,
    private val userRepository: UserRepository
) : BaseUseCase<Response, Int>(transformer) {

    override fun createObservable(request: Int?): Observable<Response> {

        return userRepository.getUsers(request ?: 0)
    }
}