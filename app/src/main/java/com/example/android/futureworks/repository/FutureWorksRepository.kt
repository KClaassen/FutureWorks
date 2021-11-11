package com.example.android.futureworks.repository

import android.util.Log
import com.example.android.futureworks.data.FutureWorksDatabase
import com.example.android.futureworks.network.ArticleApi

class FutureWorksRepository(private val database: FutureWorksDatabase) {

    suspend fun getArticles() {
        val articles = ArticleApi.retrofitService.getArticles()
        database.futureWorksDao.insertAll(articles)
        Log.d("repo", "$articles")
    }
}