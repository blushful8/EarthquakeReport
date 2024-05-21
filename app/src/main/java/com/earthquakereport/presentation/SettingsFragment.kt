package com.earthquakereport.presentation

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.earthquakereport.presentation.UISetter.setFontStyle
import com.earthquakereport.presentation.UISetter.setTextColor
import com.earthquakereport.presentation.UISetter.setTopColor
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.earthquakereport.R.layout.fragment_settings, container, false)
    }

    private lateinit var spinnerFontFamily: Spinner

    private lateinit var textViewFontFamily: TextView
    private lateinit var textViewFieldTopColor: TextView
    private lateinit var textViewColorText: TextView
    private lateinit var earthquakeStorage: EarthquakeStorage
    private lateinit var linearLayout: LinearLayout
    private lateinit var textSettingsView: TextView
    private lateinit var imageViewGoBack: ImageView
    private lateinit var tvNotification: TextView
    private lateinit var ivNotifications: ImageView

    private val fonts = mutableListOf(
        com.earthquakereport.R.font.adventuro,
        com.earthquakereport.R.font.beardsons,
        com.earthquakereport.R.font.milky_boba,
        com.earthquakereport.R.font.reach_story
    )

    private val POWERMANAGER_INTENTS = arrayOf(
        Intent().setComponent(
            ComponentName(
                "com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.huawei.systemmanager",
                "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.huawei.systemmanager",
                "com.huawei.systemmanager.optimize.process.ProtectActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.huawei.systemmanager",
                "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.coloros.safecenter",
                "com.coloros.safecenter.permission.startup.StartupAppListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.coloros.safecenter",
                "com.coloros.safecenter.startupapp.StartupAppListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.oppo.safe",
                "com.oppo.safe.permission.startup.StartupAppListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.iqoo.secure",
                "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.iqoo.secure",
                "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.vivo.permissionmanager",
                "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.samsung.android.lool",
                "com.samsung.android.sm.battery.ui.BatteryActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.samsung.android.lool",
                "com.samsung.android.sm.ui.battery.BatteryActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.htc.pitroad",
                "com.htc.pitroad.landingpage.activity.LandingPageActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.asus.mobilemanager",
                "com.asus.mobilemanager.MainActivity"
            )
        ),
        Intent().setComponent(
            ComponentName(
                "com.transsion.phonemanager",
                "com.itel.autobootmanager.activity.AutoBootMgrActivity"
            )
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.apply {
            earthquakeStorage = EarthquakeStorage(requireActivity())
            spinnerFontFamily = findViewById(com.earthquakereport.R.id.fontStyleSpinner)

            textViewFontFamily = findViewById(com.earthquakereport.R.id.textView_font_family)
            textViewFieldTopColor = findViewById(com.earthquakereport.R.id.colorTopTextView)
            textViewColorText = findViewById(com.earthquakereport.R.id.textView_Color)
            linearLayout = findViewById(com.earthquakereport.R.id.linearLayout)
            textSettingsView = findViewById(com.earthquakereport.R.id.text_settings)
            imageViewGoBack = findViewById(com.earthquakereport.R.id.go_back)
            tvNotification = findViewById(com.earthquakereport.R.id.tv_notifications)
            ivNotifications = findViewById(com.earthquakereport.R.id.iv_notifications)
        }
        spinnerFontFamily.visibility = View.GONE

        imageViewGoBack.setOnClickListener {
            findNavController().popBackStack()
        }

        (textViewColorText.parent.parent as LinearLayout).setOnClickListener {
            colorChooser(false)
        }
        (textViewFieldTopColor.parent.parent as LinearLayout).setOnClickListener {
            colorChooser(true)
        }

        (textViewFontFamily.parent.parent as LinearLayout).setOnClickListener {
            spinnerFontFamily.visibility =
                if (spinnerFontFamily.visibility == View.GONE) View.VISIBLE else View.GONE
            if (spinnerFontFamily.visibility == View.VISIBLE)
                spinnerFontFamily.performClick()
        }

        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            requireContext(), com.earthquakereport.R.array.fontNames,
            com.earthquakereport.R.layout.spinner_list
        )
        adapter.setDropDownViewResource(com.earthquakereport.R.layout.spinner_list)

        spinnerFontFamily.adapter = adapter

        spinnerFontFamily.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                earthquakeStorage.saveFontStyle(fonts[position])
                setFontStyle(
                    earthquakeStorage,
                    textViewColorText,
                    textViewFontFamily,
                    textViewFieldTopColor,
                    textSettingsView,
                    tvNotification
                )

                spinnerFontFamily.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        ivNotifications.setBackgroundResource(com.earthquakereport.R.drawable.baseline_notifications_off_24.takeIf { !earthquakeStorage.getNotifications() }
            ?: com.earthquakereport.R.drawable.baseline_notifications_active_24)

        (tvNotification.parent.parent as LinearLayout).setOnClickListener {
            val isEnable = earthquakeStorage.getNotifications()

            earthquakeStorage.saveNotifications(!isEnable)
            if (!isEnable) {
                val permission = earthquakeStorage.getPermission()

                if (!permission)
                    for (intent in POWERMANAGER_INTENTS) if (requireActivity().packageManager.resolveActivity(
                            intent,
                            PackageManager.MATCH_DEFAULT_ONLY
                        ) != null
                    ) {
                        startActivity(intent)
                        unrestrictedBatteryOptimization()

                        earthquakeStorage.savePermission(true)
                        break
                    }
            }
            ivNotifications.setBackgroundResource(com.earthquakereport.R.drawable.baseline_notifications_off_24.takeIf { isEnable }
                ?: com.earthquakereport.R.drawable.baseline_notifications_active_24)
        }

        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        })
    }

    @SuppressLint("BatteryLife")
    private fun unrestrictedBatteryOptimization() {
        val intent = Intent()
        val service = requireActivity().getSystemService(Context.POWER_SERVICE) as? PowerManager

        if (service != null && !service.isIgnoringBatteryOptimizations(requireActivity().packageName)) {
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.data = Uri.parse("package:${requireActivity().packageName}")

            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        setTextColor(
            earthquakeStorage,
            textViewColorText,
            textViewFontFamily,
            textViewFieldTopColor,
            textSettingsView,
            tvNotification
        )

        setFontStyle(
            earthquakeStorage,
            textViewColorText,
            textViewFontFamily,
            textViewFieldTopColor,
            textSettingsView,
            tvNotification,
        )
        setTopColor(earthquakeStorage, linearLayout)
    }


    private fun colorChooser(isTopBar: Boolean) {
        ColorPickerDialog.Builder(requireContext())
            .setTitle("ColorPicker Dialog")
            .setPreferenceName("MyColorPickerDialog")
            .setPositiveButton("Confirm",
                ColorEnvelopeListener { envelope, fromUser ->
                    if (!isTopBar) {
                        earthquakeStorage.saveTextColor(envelope.color)
                        setTextColor(
                            earthquakeStorage,
                            textViewColorText,
                            textViewFontFamily,
                            textViewFieldTopColor,
                            textSettingsView,
                            tvNotification
                        )
                    } else {
                        earthquakeStorage.saveTopFieldColor(envelope.color)
                        setTopColor(earthquakeStorage, linearLayout)
                    }

                })
            .setNegativeButton("Cancel") { dialogInterface, i -> dialogInterface.dismiss() }
            .attachAlphaSlideBar(true)
            .attachBrightnessSlideBar(true)
            .setBottomSpace(12)
            .show()
    }
}