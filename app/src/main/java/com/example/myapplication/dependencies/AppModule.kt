package com.example.myapplication.dependencies

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.api.API
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.repository.implementation.UserRepositoryImpl
import com.example.myapplication.usecase.UserUseCase
import com.example.myapplication.utils.AsyncTransformer
import com.example.myapplication.viewmodels.UserViewModel
import com.example.myapplication.viewmodels.factory.ViewModelFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class AppModule(private val context: Context) {

    fun getAppModule() = module {


        single<OkHttpClient> {

            val url = URL(context.getString(R.string.base_url))
            val hostName = url.host
            val certificatePinner = CertificatePinner.Builder()
                .add(hostName, "sha256/CZEvkurQ3diX6pndH4Z5/dUNzK1Gm6+n8Hdx/DQgjO1=")
                .build()

            OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build()
        }

        single<Retrofit> {

            Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()

        }

        single<API> {

            val retrofit = get<Retrofit>()
            retrofit.create(API::class.java)
        }
        single<UserRepository> {

            UserRepositoryImpl()
        }

        single {

            UserUseCase(AsyncTransformer())
        }

        single { ViewModelFactory() }

    }


}