package com.appadore.data.api

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.appadore.data.model.GroupResponseModel
import com.appadore.ui.main.view.activity.BaseActivity
import retrofit2.HttpException
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class ApiHelper(private val apiService: ApiService,
                private val mContext: BaseActivity)
{
    private var postAPIResponseLiveData = MutableLiveData<GroupResponseModel>()
    private val compositeSubscription = CompositeSubscription()

    fun getPost(): MutableLiveData<GroupResponseModel> {
        compositeSubscription.add(
            apiService.getPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { postResult ->
                        postAPIResponseLiveData.postValue(postResult)
                    },
                    { error ->
                        Log.d("error", error.message.toString())
                        if (error is HttpException) {
                            Toast.makeText(mContext, error.message(), Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(mContext, error.message, Toast.LENGTH_LONG).show()
                        }
                    }
                )
        )
        return postAPIResponseLiveData
    }

}