package com.earthquakereport.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.earthquakereport.domain.model.EarthquakeDomain
import com.earthquakereport.earthquakereport.R
import kotlinx.android.synthetic.main.earthquake_item.view.*
import java.lang.String.format
import java.text.DateFormat
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class EarthquakeAdapter(private val earthquakeList: List<EarthquakeDomain>) :
    RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder>() {

    class EarthquakeViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.earthquake_item, parent, false)
        return EarthquakeViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        holder.itemView.mag.text = earthquakeList[position].mag.toString()
        holder.itemView.place.text = earthquakeList[position].place
        val time = earthquakeList[position].time
        holder.itemView.time.text = time.toString()
        holder.itemView.url.text = earthquakeList[position].url

    }

    override fun getItemCount(): Int {
        return earthquakeList.size
    }
}