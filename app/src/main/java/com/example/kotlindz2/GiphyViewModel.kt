package com.example.kotlindz2

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphyViewModel(private val repository: GiphyRepository) : ViewModel() {
    private val _gifs = MutableLiveData<List<Gif>>()
    val gifs: LiveData<List<Gif>> get() = _gifs

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var offset = 0;
    private val limit = 20;

    init {
        loadGifs()
    }

    private fun loadGifs() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getTrendingGifs(
                    "MKezopPG85N5i3lmRCHUJLi8mRtc8B7U",
                    limit, offset
                )
                if (response.isSuccessful) {
                    val newGifs = response.body()?.data ?: emptyList()
                    val currentGifs = _gifs.value ?: emptyList()
                    _gifs.value = currentGifs + newGifs
                    offset += limit
                } else {
                    _error.value = "Error loading gifs"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retry() {
        loadGifs()
    }

    fun loadMoreGifs() {
        loadGifs()
    }
}

class GiphyViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GiphyViewModel(
            GiphyRepository(
                Retrofit.Builder()
                    .baseUrl("https://api.giphy.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GiphyApi::class.java),
                application.applicationContext
            )
        ) as T
    }
}