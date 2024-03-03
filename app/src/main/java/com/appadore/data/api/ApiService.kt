package com.appadore.data.api

import com.appadore.data.model.GroupResponseModel
import com.appadore.data.model.PostAPIResponse
import retrofit2.http.GET
import rx.Observable

interface ApiService {
    @GET("huddles/podium_dummy")
    fun getPost():  Observable<GroupResponseModel>

}