package com.example.weatherapp

import com.google.gson.Gson
import java.net.URL

class Request(val url:String){
    fun getCategories() : Categories {
        val forecastJsonString = URL(url).readText()
        val jsonString = "{\"categories\":"+forecastJsonString+"}"
//        Log.i("blood stains ----> ",jsonString);
        return Gson().fromJson(jsonString,Categories::class.java)
    }

    fun getJokes() : JokesModel {
        val jsonString = URL(url).readText()
        return Gson().fromJson(jsonString, JokesModel::class.java)
    }
}