package com.telema.malakisi.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telema.malakisi.data.repository.MalakisiRepository
import com.telema.malakisi.domain.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: MalakisiRepository) : ViewModel() {

     private val _isLoading = MutableStateFlow(true)
     val isLoading = _isLoading.asStateFlow()

    init {
        initData()
    }

    private fun initData(){
        viewModelScope.launch {
            repository.loadData().collect{result->
                when(result){
                    is Result.Loading->{
                        _isLoading.value=true
                        Log.d("MALAKISIDEMO", "LOADING : ${isLoading.value}")
                    }
                    is Result.Error -> {
                        _isLoading.value=false
                        Log.d("MALAKISIDEMO", "ERROR : ${result.t}")
                    }
                    is Result.Success -> {
                        _isLoading.value=false
                        Log.d("MALAKISIDEMO", "SUCCES : ${isLoading.value}")
                    }
                }

            }
        }
    }

}