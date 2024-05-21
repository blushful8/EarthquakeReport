package com.earthquakereport.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.earthquakereport.R
import com.earthquakereport.databinding.EarthquakeItemBinding
import com.earthquakereport.domain.model.EarthquakeDomain
import com.earthquakereport.presentation.core.Clickable
import com.earthquakereport.presentation.core.Settable
import java.util.*


class EarthquakeAdapter(
    private val earthquakeList: List<EarthquakeDomain>,
    private val earthquakeStorage: EarthquakeStorage,
    private val webViewBuilder: WebViewBuilder
) :
    RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder>(), Settable, Clickable {

    class EarthquakeViewHolder(val binding: EarthquakeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val itemBinding =
            EarthquakeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EarthquakeViewHolder(itemBinding)
    }

    @SuppressLint("NewApi", "DefaultLocale")
    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        val mag = earthquakeList[position].mag
        val time = earthquakeList[position].time
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd, HH:mm:ss", Locale.ENGLISH)
        val date = Date(time!!)


        holder.binding.mag.text = String.format("%.1f", mag)
        holder.binding.place.text = earthquakeList[position].place?.replace("?", "a")


        UISetter.setFontStyle(earthquakeStorage, holder.binding.place)
        UISetter.setTextColor(earthquakeStorage, holder.binding.place)

        holder.binding.time.text = sdf.format(date)
        setBackgroundColor(mag = mag!!, holder = holder)
        clickableItemView(holder = holder, position = position)
    }

    override fun getItemCount(): Int {
        return earthquakeList.size
    }

    override fun setBackgroundColor(mag: Float, holder: EarthquakeViewHolder) {
        if (mag >= 0f && mag < 2f) {
            holder.binding.mag.setBackgroundResource(R.drawable.round_silver)
        } else if (mag > 2f && mag <= 3f) {
            holder.binding.mag.setBackgroundResource(R.drawable.round_yellow)
        } else if (mag > 3f && mag <= 5f) {
            holder.binding.mag.setBackgroundResource(R.drawable.round_magenta)
        } else if (mag > 5f) {
            holder.binding.mag.setBackgroundResource(R.drawable.round_red)
        }
    }

    override fun clickableItemView(holder: EarthquakeViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            webViewBuilder.createNewWebView(earthquakeList[position].url.toString())
            webViewBuilder.customBackPressedListener()
        }
    }
}