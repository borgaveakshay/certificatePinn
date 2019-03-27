package com.example.myapplication.repository.implementation

import com.example.myapplication.api.API
import com.example.myapplication.models.Response
import com.example.myapplication.repository.UserRepository
import io.reactivex.Observable
import org.koin.core.KoinComponent

class UserRepositoryImpl(private val api: API) : UserRepository, KoinComponent {


    override fun getUsers(page: Int): Observable<Response> {
        return api.getUsers(page)
    }
}