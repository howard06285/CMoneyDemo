package com.shigaga.cmoneydemo.ui.second_page

import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shigaga.cmoneydemo.data.ContentItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class SecondViewModel : ViewModel() {

    fun fetchDataByHttpUrlConnection(){

        viewModelScope.launch(Dispatchers.IO) {

            val url = URL("https://jsonplaceholder.typicode.com/photos")

            //Log.d(TAG, "fetchDataByHttpUrlConnection, url.readText()=${url.readText()}")

            with(url.openConnection() as HttpURLConnection){

//                val bufferReader = inputStream.bufferedReader().lines().forEach {
//                    Log.d(TAG, "it=$it")
//                }

                val bufferReader = inputStream.bufferedReader()

                var line = bufferReader.readLine()

                val json = StringBuffer()

                while (line != null) {

                    json.append(line)

                    line = bufferReader.readLine()

                }

                val listType = object : TypeToken<ArrayList<ContentItem>>() {}.type
                val jsonArr = Gson().fromJson<ArrayList<ContentItem>>(json.toString(), listType)

                jsonArr.forEach {
                    Log.e(TAG, "fetchDataByHttpUrlConnection, it=$it")
                }
            }


        }

    }
}
