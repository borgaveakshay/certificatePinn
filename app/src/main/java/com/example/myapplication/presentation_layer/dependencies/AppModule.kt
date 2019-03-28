package com.example.myapplication.presentation_layer.dependencies

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.data_layer.api.API
import com.example.myapplication.domain_layer.repository.UserRepository
import com.example.myapplication.data_layer.repo_impl.UserRepositoryImpl
import com.example.myapplication.domain_layer.usecase.UserUseCase
import com.example.myapplication.presentation_layer.utils.AsyncTransformer
import com.example.myapplication.presentation_layer.viewmodels.factory.ViewModelFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
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
                .add(hostName, "sha256/CZEvkurQ3diX6pndH4Z5/dUNzK1Gm6+n8Hdx/DQgjO0=")
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