package com.example.myapplication.domain_layer.repository

import com.example.myapplication.presentation_layer.models.Response
import io.reactivex.Observable

interface UserRepository {

    fun getUsers(page: Int): Observable<Response>
}