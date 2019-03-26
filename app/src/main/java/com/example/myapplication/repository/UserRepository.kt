package com.example.myapplication.repository

import com.example.myapplication.models.Response
import io.reactivex.Observable

interface UserRepository {

    fun getUsers(page: Int): Observable<Response>
}