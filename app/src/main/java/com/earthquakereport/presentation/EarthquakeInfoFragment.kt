package com.earthquakereport.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.earthquakereport.R


class EarthquakeInfoFragment : Fragment() {

    private lateinit var tvInfo: TextView
    private lateinit var tvHeader: TextView
    private lateinit var ivGoBack: ImageView
    private lateinit var btnMoreTips: Button

    private lateinit var earthquakeStorage: EarthquakeStorage
    private lateinit var webViewBuilder: WebViewBuilder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_earthquake_info, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        earthquakeStorage = EarthquakeStorage(requireContext())

        tvInfo = view.findViewById(R.id.text_info)
        tvHeader = view.findViewById(R.id.earthquakeTipsHeader)
        ivGoBack = view.findViewById(R.id.go_back)
        btnMoreTips = view.findViewById(R.id.moreTipsButton)

        UISetter.setTopColor(earthquakeStorage, tvInfo.parent as ViewGroup)
        UISetter.setFontStyle(earthquakeStorage, tvHeader, btnMoreTips, tvInfo)
        UISetter.setTextColor(earthquakeStorage, tvInfo, tvHeader)

        ivGoBack.setOnClickListener {
            findNavController().popBackStack()
        }

        webViewBuilder = WebViewBuilder(this, tvHeader.parent.parent.parent as ViewGroup)
        btnMoreTips.setOnClickListener {
            webViewBuilder.createNewWebView("https://www.usgs.gov/faqs/what-should-i-do-during-earthquake#:~:text=Get%20under%20a%20desk%20or,things%20can%20fall%20on%20you).")
            webViewBuilder.customBackPressedListener()
        }

        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        })
    }
}