package com.inspirationdriven.uhomework.repo

import com.inspirationdriven.uhomework.misc.CAAS_URL
import com.inspirationdriven.uhomework.model.CatMeta
import com.inspirationdriven.uhomework.retrofit.CatService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class CatsRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl(CAAS_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()
        .create(CatService::class.java)

    suspend fun getCats(offset: Int, limit: Int): List<CatMeta>{
        return retrofit.getCats(offset, limit)
    }

}