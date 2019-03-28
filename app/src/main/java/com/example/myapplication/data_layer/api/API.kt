package com.example.myapplication.data_layer.api

import com.example.myapplication.presentation_layer.models.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("api/users")
    fun getUsers(@Query("page") pageNumber: Int): Observable<Response>

}