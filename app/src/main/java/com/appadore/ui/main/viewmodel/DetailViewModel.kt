package com.appadore.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.appadore.data.model.ResponseItem
import com.appadore.data.repository.MainRepository
import com.appadore.ui.main.view.activity.BaseActivity

class DetailViewModel(private val mainRepository: MainRepository, val mContext: BaseActivity) : ViewModel() {

    fun getParticularData(id: Int): LiveData<List<ResponseItem>> {
        return mainRepository.getParticularData(id)
    }
//    fun updateData(title: String, body: String,id: Int) {
//        mainRepository.updateData(title,body,id)
//    }
//
//    fun deleteData(id: Int) {
//        return mainRepository.deleteData(id)
//    }

}