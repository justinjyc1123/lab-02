package com.example.practicelab2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class City(
    var name: String,
) {
    var isSelected by mutableStateOf(false)
    fun changeSelect(){ //change the selected
        isSelected = !isSelected
    }

    fun checkSelect(): Boolean{
        return isSelected
    }

    fun getCityName(): String{
        return name
    }
}
