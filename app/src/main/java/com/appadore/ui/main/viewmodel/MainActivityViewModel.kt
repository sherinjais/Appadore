package com.appadore.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.appadore.data.model.GroupResponseModel
import com.appadore.data.model.PostAPIResponse
import com.appadore.data.model.ResponseItem
import com.appadore.data.repository.MainRepository
import com.appadore.ui.main.view.activity.BaseActivity
import com.appadore.ui.main.view.activity.MainActivity

class MainActivityViewModel(private val mainRepository: MainRepository,  val mContext: BaseActivity) : ViewModel() {

    fun getPost(): MutableLiveData<GroupResponseModel> {
        return mainRepository.getPost()
    }

    fun insertData(data: ResponseItem) {
        mainRepository.insertData(data)
    }

    fun getAllPost(): LiveData<PagedList<ResponseItem>> {
        return mainRepository.getAllPost()
    }


}