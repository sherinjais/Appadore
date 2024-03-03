package com.appadore.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appadore.data.api.ApiHelper
import com.appadore.data.repository.MainRepository
import com.appadore.ui.main.view.activity.BaseActivity
import com.appadore.ui.main.viewmodel.DetailViewModel

class DetailViewModelFactory(private val apiHelper: ApiHelper, private val mContext: BaseActivity) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(MainRepository(apiHelper, mContext), mContext) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
