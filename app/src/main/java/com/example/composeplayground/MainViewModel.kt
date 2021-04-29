package com.example.composeplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    // First name LiveData
    private val _firstName = MutableLiveData("")
    val firstName: LiveData<String> get() = _firstName

    // Last name LiveData
    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> get() = _lastName

    // Full name LiveData
    private val _fullName = MutableLiveData("")
    val fullName: LiveData<String> get() = _fullName

    // method that UI can invoke when there is some change.
    fun onFirstNameChange(newValue: String) {
        _firstName.postValue(newValue)
    }

    // method that UI can invoke when there is some change.
    fun onLastNameChange(newValue: String) {
        _lastName.postValue(newValue)
    }

    // method that UI can invoke when there is some change.
    fun onFullNameSubmit() {
        _fullName.postValue("${firstName.value} ${lastName.value}")
    }
}