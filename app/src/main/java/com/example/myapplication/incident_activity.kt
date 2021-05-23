package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_incident.*
import kotlinx.android.synthetic.main.general_information_view.view.*

class incident_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident)

        if (intent != null) {
            val response = intent.getStringExtra("incident")
            if (response != null) {
                var incident = Gson().fromJson(response, Incident::class.java)
                description.text = "${incident.DESCRIPTION} (${incident.TICKETID})"
                general_information.reported_by.text = incident.REPORTEDBY
                general_information.crit_level.text = incident.CRITIC_LEVEL.toString()
                general_information.date_detection_incident_activity.text =
                    incident.ISKNOWNERRORDATE
                general_information.date_finis_incident_activity.text = incident.TARGETFINISH
                general_information.system.text = incident.EXTSYSNAME
                general_information.status_incident_activity.text = incident.STATUS
                general_information.dismiss.text = incident.NORM
                if (incident.LNORM != "0")
                    general_information.distance.text = incident.LNORM
                else
                    general_information.distance.text = "Не указано"

            }

        }
    }
}