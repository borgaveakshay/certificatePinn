package com.example.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.viewmodels.UserViewModel
import com.example.myapplication.viewmodels.factory.ViewModelFactory
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    private val mViewModelFactory: ViewModelFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserViewModel::class.java)


    }

    override fun onResume() {
        super.onResume()
        mUserViewModel.getUserList(2)
    }
}
