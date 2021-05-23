package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
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

            if (!fileOut.canRead())
                fileOut.createNewFile()

            val sdCard = Environment.getExternalStorageDirectory().path + '/' + "incidents.json"

//            val inputStream = getJsonDataFromSDcard(sdCard)

            val inputStream =
                this.assets.open("incidents.json").bufferedReader().use { it.readText() }

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

                listView.setOnItemClickListener { parent, view, position, id ->
                    val incident_activity = Intent(this, incident_activity::class.java)
                    incident_activity.putExtra("incident", gson.toJson(incidents[id.toInt()]))
                    startActivity(incident_activity)
                }
            } else {
                val empty_file = TextView(this)
                empty_file.layoutParams =
                    CoordinatorLayout.LayoutParams(
                        CoordinatorLayout.LayoutParams.MATCH_PARENT,
                        CoordinatorLayout.LayoutParams.MATCH_PARENT
                    )
                empty_file.textSize = 20F
                empty_file.gravity = Gravity.CENTER
                empty_file.setTextColor(Color.parseColor("#000000"))
                empty_file.text = "Аварий не обнуружено, все живы, ура!!!"
                coordLayout.addView(empty_file)
            }


        }

    }
}