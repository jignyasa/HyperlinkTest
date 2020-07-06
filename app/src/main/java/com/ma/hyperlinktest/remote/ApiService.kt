package com.ma.hyperlinktest.remote

import com.google.gson.JsonObject
import com.ma.hyperlinktest.model.ImageItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.*
import kotlin.collections.ArrayList

interface ApiService {
    @GET
    fun getImages(@Url url:String) : Observable<JsonObject>
}