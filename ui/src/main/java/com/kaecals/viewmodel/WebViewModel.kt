package com.kaecals.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

class WebViewModel(application: Application) : AndroidViewModel(application) {
    private var webViewRef: WeakReference<WebView>? = null

    @SuppressLint("SetJavaScriptEnabled")
    fun getWebView(): WebView {
        var webView = webViewRef?.get()
        if (webView == null) {
            webView = WebView(getApplication()).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.cacheMode = android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        loadUrl(
                            """
                            javascript:(function() {
                                var selectedContent = document.querySelector('.tw-flex-1 .tw-flex-1').innerHTML;
                                document.body.innerHTML = selectedContent;
                                document.body.style.margin = '0';
                                document.body.style.padding = '0';
                                document.body.style.overflow = 'hidden';
                            })()
                            """
                        )
                    }
                }
            }
            webViewRef = WeakReference(webView)
        }
        return webView
    }

    fun loadUrl(url: String) {
        getWebView().loadUrl(url)
    }

    override fun onCleared() {
        webViewRef?.get()?.destroy()
        webViewRef = null
    }
}