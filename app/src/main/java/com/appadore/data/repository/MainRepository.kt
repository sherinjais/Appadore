package com.appadore.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.appadore.data.api.ApiHelper
import com.appadore.data.model.GroupResponseModel
import com.appadore.data.model.PostAPIResponse
import com.appadore.data.model.ResponseItem
import com.appadore.db.AppDatabase
import com.appadore.ui.main.view.activity.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainRepository(private val apiHelper: ApiHelper, private val mContext: BaseActivity)  {
    var appDatabase: AppDatabase? = null

    fun getPost(): MutableLiveData<GroupResponseModel>  {
        return apiHelper.getPost()
    }

    private fun initializeDB(context: Context) : AppDatabase {
        return AppDatabase.getDataseClient(context)
    }

    fun insertData(data: ResponseItem) {

        appDatabase = initializeDB(mContext)

        CoroutineScope(Dispatchers.IO).launch {
            appDatabase!!.appDao().insertAll(data)
        }

    }
    fun getAllPost(): LiveData<PagedList<ResponseItem>> {
        appDatabase = initializeDB(mContext)
        val personsLiveData: LiveData<PagedList<ResponseItem>>
        val config= PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .build()
        val factory: DataSource.Factory<Int, ResponseItem> = appDatabase!!.appDao().getPost()


        val data: LivePagedListBuilder<Int, ResponseItem> = LivePagedListBuilder(factory,config)
        personsLiveData=data.build()

        return personsLiveData


    }

    fun getParticularData(id: Int): LiveData<List<ResponseItem>> {
        appDatabase = initializeDB(mContext)
        return appDatabase!!.appDao().getParticularData(id)
    }

//    fun updateData(title: String, body: String,id: Int) {
//        appDatabase = initializeDB(mContext)
//        CoroutineScope(Dispatchers.IO).launch {
//            appDatabase!!.appDao().updateData(title,body,id)
//        }
//
//
//    }
//
//    fun deleteData(id: Int) {
//        appDatabase = initializeDB(mContext)
//        CoroutineScope(Dispatchers.IO).launch {
//            appDatabase!!.appDao().delete(id)
//        }
//
//
//    }



}