package jaipurbus.jaipur.tourism.jaipurbus.ui

import android.content.res.Configuration
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codersworld.jplibs.storage.UserSessions
import com.codersworld.jplibs.utils.Tags
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityWebviewBinding
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper

class WebViewActivity : BaseActivity() {
    lateinit var binding: ActivityWebviewBinding
    var mType = 0
    var mUrl = Tags.BASE_URL


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
        binding = DataBindingUtil.setContentView(this@WebViewActivity, R.layout.activity_webview)
        binding.imgBack.setOnClickListener {
            finish()
        }
        if (intent.hasExtra("type")) {
            mType = intent.getIntExtra("type", 0) as Int;
        } else {
            finish()
            return
        }
        if (mType==1){
            mUrl +=Tags.JB_PRIVACY_POLICY+UserSessions(this@WebViewActivity).getLanguage(this@WebViewActivity)
        }
        binding.webview.setWebViewClient(object : WebViewClient() {
            override fun onReceivedError(webView: WebView, i: Int, str: String, str2: String) {}
            override fun onReceivedSslError(
                webView: WebView,
                sslErrorHandler: SslErrorHandler,
                sslError: SslError
            ) {
            }

            override fun onPageFinished(webView: WebView, str: String) {
                super.onPageFinished(webView, str)
            }

            override fun shouldOverrideUrlLoading(webView: WebView, str: String): Boolean {
                //  Log.d("shouldOverrideUrlLoa", str);
                return super.shouldOverrideUrlLoading(webView, str)
            }
        })
        binding.webview.getSettings().setJavaScriptEnabled(true)
        binding.webview.loadUrl(mUrl)

    }
}