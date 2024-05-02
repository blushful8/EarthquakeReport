package com.earthquakereport.presentation

import android.os.Bundle
import android.util.Log
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
import com.earthquakereport.earthquakereport.R

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainInfoFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main_info, container, false)

    private lateinit var linearLayout: LinearLayout
    private lateinit var appTextView: TextView
    private lateinit var errorTextView: TextView
    private lateinit var appStorageOpen: AppStorageOpen
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recView = view.findViewById<RecyclerView>(R.id.rec_view)
        appTextView = view.findViewById(R.id.app_name)
        linearLayout = view.findViewById(R.id.linearLayout)
        errorTextView = view.findViewById(R.id.text_error)
        recView.layoutManager = LinearLayoutManager(requireContext())
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        appStorageOpen = AppStorageOpen(requireActivity())
        val switchToSettings = view.findViewById<ImageView>(R.id.arrow_go_setting)
        switchToSettings.setOnClickListener {
            findNavController().navigate(R.id.action_mainInfoFragment_to_settingsFragment)
        }
        setRecycler(recView)
        swipe.setOnRefreshListener {
            appStorageOpen = AppStorageOpen(requireActivity())
            appStorageOpen.saveResult(true)
            setRecycler(recView)
            swipe.isRefreshing = false
        }
    }

    override fun onStart() {
        super.onStart()
        setTextColor()
        setTextFontStyle()
        setTopColor()
    }

    private fun setTextColor() {
        val textColor = appStorageOpen.getTextColor()
        errorTextView.setTextColor(Parser.parseColor(textColor))
        appTextView.setTextColor(Parser.parseColor(textColor))
    }

    private fun setTextFontStyle() {
        val textFontStyle = appStorageOpen.getFontStyle()
        Parser.setFontFamily(errorTextView, requireActivity(), textFontStyle)
        Parser.setFontFamily(appTextView, requireActivity(), textFontStyle)
    }

    private fun setTopColor() {
        val topColor = appStorageOpen.getTopFieldColor()
        linearLayout.setBackgroundColor(Parser.parseColor(topColor))
    }

    private fun setRecycler(recyclerView: RecyclerView) {
        val appStorageOpen = AppStorageOpen(requireActivity())
        val boolean = appStorageOpen.getResult()
        viewModel.getEarthquakeList(checkFirstOpen = boolean) { mutableList ->
            Log.d("List", mutableList.toString())
            if (mutableList.isNotEmpty()) {
                val adapter = EarthquakeAdapter(mutableList, appStorageOpen, requireActivity())
                recyclerView.adapter = adapter
                if (boolean && mutableList.isNotEmpty()) appStorageOpen.saveResult(false)
                errorTextView.visibility = View.GONE
                return@getEarthquakeList
            }
            errorTextView.visibility = View.VISIBLE
        }

    }
}