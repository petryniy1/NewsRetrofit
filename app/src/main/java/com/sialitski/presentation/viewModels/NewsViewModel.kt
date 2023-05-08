package com.sialitski.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sialitski.domain.repository.NewsRepository
import com.sialitski.domain.storage.models.News
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

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
            _news.value = repository.getDataNews().reversed()
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
}
