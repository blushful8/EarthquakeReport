package com.earthquakereport.presentation

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class WebViewBuilder(private val anyFragment: Fragment, private val parent: ViewGroup) {
    private var newWebView = null as WebView?
    fun createNewWebView(url: String): WebView {
        val webView = WebView(anyFragment.requireContext())
        webView.webViewParam()

        parent.addView(webView)
        webView.loadUrl(url)
        newWebView = webView

        return webView
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebView.webViewParam() {
        layoutParams = ViewGroup.LayoutParams(-1, -1)
        webViewClient = WebViewClient()
        webViewClient = WebViewClient()
        settings.javaScriptEnabled = true

        getSwipeLayout()?.also {
            it.isEnabled = false
        }
    }

    private fun getSwipeLayout(): SwipeRefreshLayout? {
        return try {
            (parent.parent as SwipeRefreshLayout)
        } catch (_: Exception) {
            null
        }
    }

    fun customBackPressedListener() {
        anyFragment.requireActivity().onBackPressedDispatcher.addCallback(object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (newWebView?.canGoBack() == true)
                    newWebView?.goBack()
                else {
                    if (newWebView?.visibility == ViewGroup.VISIBLE) {
                        newWebView?.visibility = ViewGroup.INVISIBLE
                        (newWebView?.parent as ViewGroup).removeView(newWebView)
                        newWebView?.destroy()

                        getSwipeLayout()?.also {
                            it.isEnabled = true
                        }
                    } else {
                        if (anyFragment is EarthquakeMainFragment)
                            anyFragment.requireActivity().finish()
                        else
                            anyFragment.findNavController().popBackStack()

                    }
                }
            }

        })
    }
}