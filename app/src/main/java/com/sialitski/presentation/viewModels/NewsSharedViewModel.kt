package com.sialitski.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sialitski.domain.repository.NewsRepository
import com.sialitski.domain.storage.models.News
import kotlinx.coroutines.launch

class NewsSharedViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    private val _dataNews = MutableLiveData<List<News>>()
    val daoNews: LiveData<List<News>> get() = _dataNews

    init {
        Log.e("aaa", "VM OPEN")
    }

    fun onNewsItemClicked(position: Int) {
        val item = _news.value?.get(position) ?: return
        val list = _news.value?.toMutableList() ?: return
        list[position] = item.copy(isChecked = !item.isChecked)
        _news.value = list
    }

    fun loadRetrofitNews() {
        viewModelScope.launch {
            _news.value = repository.getNetworkNews()
        }
    }

    fun loadDataNews() {
        viewModelScope.launch {
            _dataNews.value = repository.getDataNews()
        }
    }

    fun saveNews(news: News) {
        viewModelScope.launch {
            repository.insertDataNews(news)
        }
    }

    fun deleteDataNews(news: News) {
        viewModelScope.launch {
            repository.deleteDataNews(news)
        }
    }

    override fun onCleared() {
        Log.e("aaa", "VM OPEN")
        super.onCleared()
    }
}
