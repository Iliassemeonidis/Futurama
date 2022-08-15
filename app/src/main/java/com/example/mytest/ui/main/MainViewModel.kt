package com.example.mytest.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytest.model.AppState
import com.example.mytest.repositiry.interactor.MainInteractor
import kotlinx.coroutines.*

class MainViewModel(private val interactor: MainInteractor) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<AppState> = MutableLiveData()

    private val liveData : LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> = liveData

    fun getData() {
        _mutableLiveData.value = AppState.Loading(1)

        viewModelScope.launch(
            Dispatchers.Main
                    + SupervisorJob()
                    + CoroutineExceptionHandler { _, it -> handlerError(it) }
        ) {
            withContext(Dispatchers.IO) {
                _mutableLiveData.postValue(interactor.getData())
            }
        }
    }

    private fun handlerError(error: Throwable) {
        _mutableLiveData.value = AppState.Error(error)
    }
}