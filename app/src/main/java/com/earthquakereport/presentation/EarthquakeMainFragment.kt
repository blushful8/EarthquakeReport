package com.earthquakereport.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.earthquakereport.R
import com.earthquakereport.presentation.UISetter.setFontStyle
import com.earthquakereport.presentation.UISetter.setTextColor
import com.earthquakereport.presentation.UISetter.setTopColor
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthquakeMainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_earthquake_info, container, false)

    private lateinit var linearLayout: LinearLayout
    private lateinit var appTextView: TextView
    private lateinit var errorTextView: TextView
    private lateinit var earthquakeStorage: EarthquakeStorage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recView = view.findViewById<RecyclerView>(R.id.rec_view)
        appTextView = view.findViewById(R.id.app_name)
        linearLayout = view.findViewById(R.id.linearLayout)
        errorTextView = view.findViewById(R.id.text_error)
        recView.layoutManager = LinearLayoutManager(requireContext())
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        earthquakeStorage = EarthquakeStorage(requireActivity())
        val switchToSettings = view.findViewById<ImageView>(R.id.arrow_go_setting)

        Hawk.init(requireContext()).build()

        switchToSettings.setOnClickListener {
            findNavController().navigate(R.id.action_mainInfoFragment_to_settingsFragment)
        }



        setRecycler(recView)
        swipe.setOnRefreshListener {
            setRecycler(recView)
            swipe.isRefreshing = false
        }

    }


    override fun onStart() {
        super.onStart()
        setTextColor(earthquakeStorage, errorTextView, appTextView)
        setFontStyle(earthquakeStorage, errorTextView, appTextView)
        setTopColor(earthquakeStorage, linearLayout)
    }

    private fun setRecycler(recyclerView: RecyclerView) {
        viewModel.getEarthquakeList { mutableList ->

            if (mutableList.isNotEmpty()) {
                val adapter = EarthquakeAdapter(mutableList, earthquakeStorage)
                recyclerView.adapter = adapter

                errorTextView.visibility = View.GONE
                return@getEarthquakeList
            }
            errorTextView.visibility = View.VISIBLE
        }

    }
}