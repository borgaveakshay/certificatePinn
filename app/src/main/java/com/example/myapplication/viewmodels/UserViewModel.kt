package com.example.myapplication.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.models.Response
import com.example.myapplication.usecase.UserUseCase

class UserViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {


    fun getUserList(page: Int): LiveData<Response> {

        val liveData: MutableLiveData<Response> = MutableLiveData()

        compositeDisposable.add(userUseCase.observable(page).subscribe({ response ->

            response.let {
                liveData.postValue(response)
                Toast.makeText(getApplication(), "Api Response received successfully!!!", Toast.LENGTH_LONG).show()
            }


        }, { e ->

            liveData.postValue(null)
            Toast.makeText(getApplication(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }))

        return liveData
    }


}