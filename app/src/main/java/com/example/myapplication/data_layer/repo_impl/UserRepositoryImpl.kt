package com.example.myapplication.data_layer.repo_impl

import com.example.myapplication.data_layer.api.API
import com.example.myapplication.presentation_layer.models.Response
import com.example.myapplication.domain_layer.repository.UserRepository
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserRepositoryImpl : UserRepository, KoinComponent {

    private val api: API by inject()

    override fun getUsers(page: Int): Observable<Response> {
        return api.getUsers(page)
    }
}