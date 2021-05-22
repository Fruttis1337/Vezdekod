package example.javatpoint.com.kotlincustomlistview

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapplication.Incident
import com.example.myapplication.R

class MyListAdapter(
    private val context: Activity,
    private val incidents: List<Incident>
) : ArrayAdapter<String>(context, R.layout.incident_view_item) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.incident_view_item, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val dateDetection = rowView.findViewById(R.id.date_detection) as TextView
        val dateFinish = rowView.findViewById(R.id.date_finish) as TextView
        val statusOfIncident = rowView.findViewById(R.id.status) as TextView

        titleText.text = incidents.get(position).EXTSYSNAME
        subtitleText.text = incidents.get(position).DESCRIPTION
        dateDetection.text = incidents.get(position).ISKNOWNERRORDATE
        dateFinish.text = incidents.get(position).TARGETFINISH
        statusOfIncident.text = incidents.get(position).STATUS

        return rowView
    }
}