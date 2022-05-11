package com.inspirationdriven.uhomework.retrofit

import com.inspirationdriven.uhomework.model.CatMeta
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("/api/cats")
    suspend fun getCats(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10
    ): List<CatMeta>
}

