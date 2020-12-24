package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeViewModel : ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _isRedirected = MutableLiveData<Boolean>()
    val isRedirect: LiveData<Boolean>
    get() = _isRedirected

    val newShoe = Shoe("", 0.0, "", "", emptyList())

    init {
        _isRedirected.value = false
        _shoeList.value = ArrayList()
    }

    fun addNewShoe() {
        _shoeList.value?.add(newShoe)
        _isRedirected.value = true
    }

    fun redirectCompleted() {
        _isRedirected.value = false
    }
}