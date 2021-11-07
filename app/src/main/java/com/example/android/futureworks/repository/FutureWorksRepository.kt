package com.example.android.futureworks.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.futureworks.data.FutureWorksDatabase
import com.example.android.futureworks.models.Article
import com.example.android.futureworks.network.ArticleApi

class FutureWorksRepository(private val database: FutureWorksDatabase) {

    //val articles = database.futureWorksDao.getArticles()

    suspend fun getArticles() {
        val articles = ArticleApi.retrofitService.getArticles()
        database.futureWorksDao.insertAll(articles)
        Log.d("repo", "$articles")
    }
}