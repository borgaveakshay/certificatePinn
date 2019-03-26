package com.example.myapplication.repository.implementation

import com.example.myapplication.api.API
import com.example.myapplication.models.Response
import com.example.myapplication.repository.UserRepository
import io.reactivex.Observable

class UserRepositoryImpl(private val api: API) : UserRepository {

    override fun getUsers(page: Int): Observable<Response> {
        return api.getUsers(page)
    }
}