package com.example.android.futureworks.network

import com.example.android.futureworks.models.Article
import com.example.android.futureworks.utils.Constants.ACCESS_TOKEN
import com.example.android.futureworks.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//val retrofitRESTClient = Retrofit.Builder()
//    .baseUrl(BASE_URL)
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .build()

interface ArticleApiService {

    @Headers("Content-Type: application/json")
    @GET("articles")
    suspend fun getArticles(
        @Header("Authorization") bearerToken: String
    ):List<Article>

    @GET("articles/{articleId}")
    suspend fun getArticleById():List<Article>

}

//object ArticleApi {
//    val retrofitService: ArticleApiService by lazy {
//        retrofitRESTClient.create(ArticleApiService::class.java)
//    }
//}