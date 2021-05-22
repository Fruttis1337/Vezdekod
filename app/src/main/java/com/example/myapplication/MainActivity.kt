package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import example.javatpoint.com.kotlincustomlistview.MyListAdapter
import getJsonDataFromSDcard
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val REQUEST_EXTERNAL_STORAGE = 1
        val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val permission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(
                this,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        if (permission == PackageManager.PERMISSION_GRANTED) {

            var filename = "incidents.json"

            var path = Environment.getExternalStorageDirectory().path

            var fileOut = File(path, filename)

            fileOut.createNewFile()

            val sdCard = Environment.getExternalStorageDirectory().path + '/' + "incidents.json"

            val inputStream = getJsonDataFromSDcard(sdCard)


            val gson = Gson()
            val listIncidentsType = object : TypeToken<List<Incident>>() {}.type
            lateinit var incidents: List<Incident>

            if (!inputStream!!.isNullOrEmpty()) {
                incidents = gson.fromJson(inputStream, listIncidentsType)
                val myAdapter = MyListAdapter(this, incidents)
                listView.adapter = myAdapter
                incidents.forEach {
                    myAdapter.add(it.toString())
                }
            }
        }

    }
}