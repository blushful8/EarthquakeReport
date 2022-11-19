package com.earthquakereport.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.earthquakereport.earthquakereport.R

class SettingsFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    private lateinit var seekBarFontFamily: SeekBar
    private lateinit var seekBarColorText: SeekBar
    private lateinit var seekBarTopFieldColor: SeekBar
    private lateinit var textViewFontFamily: TextView
    private lateinit var textViewFieldTopColor: TextView
    private lateinit var textViewColorText: TextView
    private lateinit var appStorageOpen: AppStorageOpen
    private lateinit var linearLayout: LinearLayout
    private lateinit var textSettingsView: TextView
    private lateinit var imageViewGoBack: ImageView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.apply {
            appStorageOpen = AppStorageOpen(requireActivity())
            seekBarFontFamily = findViewById(R.id.fontStyleSeekBar)
            seekBarColorText = findViewById(R.id.colorTextSeekBar)
            seekBarTopFieldColor = findViewById(R.id.colorTopFieldSeekBar)
            textViewFontFamily = findViewById(R.id.textView_font_family)
            textViewFieldTopColor = findViewById(R.id.colorTopTextView)
            textViewColorText = findViewById(R.id.textView_Color)
            linearLayout = findViewById(R.id.linearLayout)
            textSettingsView = findViewById(R.id.text_settings)
            imageViewGoBack = findViewById(R.id.go_back)
        }
        imageViewGoBack.setOnClickListener {
            findNavController().popBackStack()
        }
        seekBarFontFamily.setOnSeekBarChangeListener(this)
        seekBarColorText.setOnSeekBarChangeListener(this)
        seekBarTopFieldColor.setOnSeekBarChangeListener(this)
        seekBarFontFamily.progress = appStorageOpen.getFontStyle()
        seekBarColorText.progress = appStorageOpen.getTextColor()
        seekBarTopFieldColor.progress = appStorageOpen.getTopFieldColor()
    }

    override fun onStart() {
        super.onStart()
        setTextColor()
        setFontStyle()
        setTopColor()
    }

    private fun setTopColor() {
        val topColor = appStorageOpen.getTopFieldColor()
        linearLayout.setBackgroundColor(CustomParser.parseColor(topColor))
    }

    private fun setFontStyle() {
        val style = appStorageOpen.getFontStyle()
        CustomParser.setFontFamily(textViewColorText, requireActivity(), style)
        CustomParser.setFontFamily(textViewFontFamily, requireActivity(), style)
        CustomParser.setFontFamily(textViewFieldTopColor, requireActivity(), style)
        CustomParser.setFontFamily(textSettingsView, requireActivity(), style)
    }

    private fun setTextColor() {
        val textColor = appStorageOpen.getTextColor()
        textViewColorText.setTextColor(CustomParser.parseColor(textColor))
        textViewFontFamily.setTextColor(CustomParser.parseColor(textColor))
        textViewFieldTopColor.setTextColor(CustomParser.parseColor(textColor))
        textSettingsView.setTextColor(CustomParser.parseColor(textColor))
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when (seekBar?.id) {
            seekBarTopFieldColor.id -> {
                appStorageOpen.saveTopFieldColor(progress)
                setTopColor()
            }
            seekBarColorText.id -> {
                appStorageOpen.saveTextColor(progress)
                setTextColor()
            }
            seekBarFontFamily.id -> {
                appStorageOpen.saveFontStyle(progress)
                setFontStyle()
            }
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}