package com.inspirationdriven.uhomework.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirationdriven.uhomework.model.CatMeta
import com.inspirationdriven.uhomework.repo.CatsRepo
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val catRepo = CatsRepo()

    private val catsLiveData = MutableLiveData<Resource<List<CatMeta>>>()
    val cats: LiveData<Resource<List<CatMeta>>>
        get() = catsLiveData

    private var job: Job? = null

    fun startJob() {
        job = viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {
                withContext(Dispatchers.IO) {
                    try {
                        val result = catRepo.getCats(0, 20)
                        catsLiveData.postValue(Resource.Success(result))
                    } catch (e: Throwable) {
                        if (e !is CancellationException) {
                            catsLiveData.postValue(Resource.Error(e))
                        }
                    }
                }
                delay(10000)
            }
        }
    }

    fun stopJob(){
        job?.cancel()
        job = null
    }

    sealed class Resource<out T : Any> {
        data class Success<out T : Any>(val data: T) : Resource<T>()
        data class Error(val exception: Throwable) : Resource<Nothing>()
    }
}