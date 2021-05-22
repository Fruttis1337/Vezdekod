package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import example.javatpoint.com.kotlincustomlistview.MyListAdapter
import getJsonDataFromAsset
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sdCard = Environment.getExternalStorageDirectory().getPath() + '/' + "incidents.json"

        val inputStream = getJsonDataFromAsset(applicationContext, sdCard)
        if (inputStream != null) {
            Log.i("data", inputStream)
        }

        val gson = Gson()
        val listIncidentsType = object : TypeToken<List<Incident>>() {}.type

        var incidents: List<Incident> = gson.fromJson(inputStream, listIncidentsType)
        incidents.forEachIndexed { idx, incident -> Log.i("data", "> Item $idx: \n$incident") }

        val myAdapter = MyListAdapter(this, incidents)
        listView.adapter = myAdapter
        incidents.forEach{
            myAdapter.add(it.toString())
        }


    }
}