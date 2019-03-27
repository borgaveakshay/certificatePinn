package com.example.myapplication.utils

import com.example.myapplication.models.DataItem
import com.example.myapplication.models.Response

object TestUtils {


    fun getMockUserUseCaseData(): Response {

        val response = Response()
        val dataItemList: MutableList<DataItem> = mutableListOf()

        for (i in 0..4) {

            val dataItem = DataItem()
            dataItem.id = i

            dataItemList.add(dataItem)

        }
        response.data = dataItemList

        return response
    }

}