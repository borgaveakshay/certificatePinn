package com.example.myapplication.presentation_layer.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.presentation_layer.models.DataItem
import com.example.myapplication.presentation_layer.view.adapters.UserListAdapter
import com.example.myapplication.presentation_layer.viewmodels.UserViewModel
import com.example.myapplication.presentation_layer.viewmodels.factory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var mUserViewModel: UserViewModel
    private val mViewModelFactory: ViewModelFactory by inject()
    lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        userListAdapter = UserListAdapter()
        avatarList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        avatarList.setHasFixedSize(true)
        avatarList.adapter = userListAdapter
    }

    override fun onResume() {
        super.onResume()
        mUserViewModel.getUserList(2).observe(this, Observer {

            if(mUserViewModel.isError){
                Toast.makeText(this, "Error while fetching result!!", Toast.LENGTH_LONG).show()
            }
            else {
                it?.let {
                        userListAdapter.updateList(it.data as List<DataItem>)
                        Toast.makeText(this, "Response received!!!", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}
