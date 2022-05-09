package com.earthquakereport.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.earthquakereport.domain.model.EarthquakeDomain
import com.earthquakereport.earthquakereport.R
import kotlinx.android.synthetic.main.earthquake_item.view.*
import java.util.*

class EarthquakeAdapter(private val earthquakeList: List<EarthquakeDomain>) :
    RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder>() {

    class EarthquakeViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.earthquake_item, parent, false)

        return EarthquakeViewHolder(view)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        val mag = earthquakeList[position].mag
        val time = earthquakeList[position].time
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd, HH:mm:ss", Locale.ENGLISH)
        val date = Date(time)

        holder.itemView.mag.text = String.format("%.1f", mag)
        holder.itemView.place.text = earthquakeList[position].place.replace("?", "a")
        holder.itemView.time.text = sdf.format(date)
        setBackGroundColor(mag = mag, holder = holder)
        clickableItemView(holder = holder, position = position)
    }

    override fun getItemCount(): Int {
        return earthquakeList.size
    }

    private fun setBackGroundColor(mag: Float, holder: EarthquakeViewHolder) {
        if (mag >= 0f && mag < 2f) {
            holder.itemView.mag.setBackgroundResource(R.drawable.round_silver)
        } else if (mag > 2f && mag <= 3f) {
            holder.itemView.mag.setBackgroundResource(R.drawable.round_yellow)
        } else if (mag > 3f && mag <= 5f) {
            holder.itemView.mag.setBackgroundResource(R.drawable.round_magenta)
        } else if (mag > 5f) {
            holder.itemView.mag.setBackgroundResource(R.drawable.round_red)
        }
    }

    private fun clickableItemView(holder: EarthquakeViewHolder, position: Int) {
        val i = Intent(Intent.ACTION_VIEW)
        holder.itemView.setOnClickListener {
            i.data = Uri.parse(earthquakeList[position].url)
            holder.itemView.context.startActivity(i)
        }
    }
}