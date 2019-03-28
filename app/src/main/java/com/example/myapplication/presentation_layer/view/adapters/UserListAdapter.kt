package com.example.myapplication.presentation_layer.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.presentation_layer.models.DataItem
import com.squareup.picasso.Picasso

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var dataList = mutableListOf<DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


    fun updateList(list: List<DataItem>) {
        if (list.isNotEmpty()) {
            dataList.clear()
            dataList.addAll(list)
            notifyDataSetChanged()
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataItem) {
            with(itemView) {
                var fullname = data.firstName + " " + data.lastName
                name.text = fullname
                Picasso.get().load(data.avatar).fit().into(avatar)
            }
        }
    }
}