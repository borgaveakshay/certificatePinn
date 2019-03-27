package com.example.myapplication.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.models.Response
import com.example.myapplication.usecase.UserUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserViewModel() : BaseViewModel(), KoinComponent {

    private val userUseCase: UserUseCase by inject()

    fun getUserList(page: Int): LiveData<Response> {

        val liveData: MutableLiveData<Response> = MutableLiveData()

        compositeDisposable.add(userUseCase.observable(page).subscribe({ response ->

            response.let {
                liveData.postValue(response)
            }


        }, { e ->

            liveData.postValue(null)
            Toast.makeText(getApplication(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }))

        return liveData
    }


}