package com.example.practicelab2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.unit.dp
import kotlin.collections.remove

@Composable
fun CityScreen(){ //控制全部的父级函数
    var clickButton by remember { //确定按钮是否被点击
        mutableStateOf(false)
    }
    var cityName by remember { //确定城市名称
        mutableStateOf("")
    }
    var cityList = remember { //创建城市列表
        InitCityList(mutableStateListOf<City>())
    }
    var selectCity by remember { //选择对应城市
        mutableStateOf<City?>(null)
    }

    Row (Modifier.padding(top = 50.dp)) {
        ButtonAddCity(onClick = { clickButton = true })
    }


    Column(Modifier.padding(100.dp)) {
        if (clickButton) {
            CityInput(
                input = cityName, // 确认输入城市名
                cityName = { cityName = it }, //把城市输入名传回去
                confirmCityName = {
                    addCityList(cityName, cityList) //点击确认按钮后，将城市添加进列表
                    cityName="" //把输入框设为空
                    clickButton = false //关闭输入端口
                }
            )

        }

        ShowCityList(cityList, //显示城市列表
            onCityClick = {selectCity = it} //判断选择的是哪个城市
        )
        ButtonDeletCity( //删除按钮
            onClick = {cityList.remove(selectCity)} //删除对应城市
        )
    }
}


fun InitCityList(cityList: MutableList<City>): MutableList<City>{
    cityList.add(City("Edmonton"))
    cityList.add(City("Winnipeg"))
    return cityList
}




@Composable
fun ButtonAddCity( //添加城市按钮
    onClick:() -> Unit
){

    Button(
        onClick = onClick //当用户点击后执行外面传入的函数
    ) {
        Text("Add City")
    }

}

@Composable
fun ButtonDeletCity( //删除按钮
    onClick: () -> Unit
){
    Button(
        onClick = onClick
    ) {
        Text("delete City")
    }
}

@Composable
fun CityInput( //添加城市
    input: String,
    cityName:(String) -> Unit,
    confirmCityName:() -> Unit
){
    Column(Modifier.padding(16.dp)) { //确保输入按键是从上往下显示
        Text("Enter the City Name")

        OutlinedTextField(
            value = input,
            onValueChange = cityName,
            label = { Text("City Name") }
        )
        Button(
            onClick = confirmCityName
        ) {
            Text("Confirm")
        }
    }

}

fun makeCity(name: String): City{ //创建一个城市
    val city = City(name)
    return city
}


fun addCityList(cityName: String,cityList: MutableList<City>){
    //添加城市到城市列表
    val city = makeCity(cityName)
    cityList.add(city)
}

@Composable
fun ShowCityList( //显示城市列表
    citylist: MutableList<City>,
    onCityClick:(City) -> Unit
){
    Column { //确保城市输入后沿垂直排布
        citylist.forEach { city -> //循环输出对应城市名
            Card (
                modifier = Modifier.clickable(){
                    onCityClick(city)
                }
            ){
                Text(city.getCityName())
            }
        }
    }
}