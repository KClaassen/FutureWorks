package com.example.android.futureworks.articlemain

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.futureworks.data.FutureWorksDatabase
import com.example.android.futureworks.models.Article
import com.example.android.futureworks.repository.FutureWorksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ArticleMainViewModel(application: Application): ViewModel() {

    private val database = FutureWorksDatabase.getDatabase(application)
    private val repository = FutureWorksRepository(database)

    //val articles get() = repository.articles

    var articleListLiveData: LiveData<List<Article>> = database.futureWorksDao.getArticles()

    init {
        viewModelScope.launch {
            try {
                repository.getArticles()
                Log.d("viewmodel articles", "viewmodel receives articles")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    fun getArticles() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                repository.getArticles()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

    // Internally, we use a MutableLiveData to handle navigation to the selected article
    private val _navigateToSelectedArticle = MutableLiveData<Article>()

    // The external immutable LiveData for the navigation article
    val navigateToSelectedArticle
        get() = _navigateToSelectedArticle

    //When the article is clicked, set the [_navigateToSelectedArticle] [MutableLiveData]Article that was clicked on.
    fun onArticleClicked(article: Article) {
        _navigateToSelectedArticle.value = article
    }

    // After the navigation has taken place, make sure navigateToSelectedProperty is set to null
    fun displayArticleDetailsComplete() {
        _navigateToSelectedArticle.value = null
    }

    // Factory for constructing CountriesListViewModel with parameter
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArticleMainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArticleMainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}