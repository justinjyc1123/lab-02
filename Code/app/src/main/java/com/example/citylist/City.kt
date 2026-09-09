package com.example.practicelab2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class City(
    var name: String,
) {
    var isSelected by mutableStateOf(false)
    fun changeSelect(){ //改变当前状态
        isSelected = !isSelected
    }

    fun checkSelect(): Boolean{ //检查是否被选择
        return isSelected
    }

    fun getCityName(): String{
        return name
    }
}
