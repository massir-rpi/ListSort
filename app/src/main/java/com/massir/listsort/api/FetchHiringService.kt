package com.massir.listsort.api

import retrofit2.Response
import retrofit2.http.GET

interface FetchHiringService {
    @GET("hiring.json")
    suspend fun getHiringList() : Response<List<ListItem>>
}
