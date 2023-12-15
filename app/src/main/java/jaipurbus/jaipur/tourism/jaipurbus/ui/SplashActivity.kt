package jaipurbus.jaipur.tourism.jaipurbus.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.codersworld.jplibs.storage.AppHelper
import com.codersworld.jplibs.storage.UserSessions
import com.codersworld.jplibs.utils.CommonMethods
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.MainActivity
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper
import jaipurbus.jaipur.tourism.jaipurbus.utils.Global

class SplashActivity : AppCompatActivity() {

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
    }

    var txtCopyright: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
        setContentView(R.layout.activity_splash)
        txtCopyright = findViewById(R.id.txtCopyright)
        txtCopyright!!.setText(getString(R.string.lbl_copyright,Global().getVersionName(this@SplashActivity)))
        val handler = Handler()
        AppHelper().clearDB(this@SplashActivity)
        AppHelper().checkDB(this@SplashActivity)
        //ApiHelper(this@SplashActivity).hitApi()
        handler.postDelayed({ callActivityIntent() }, 1500)//1500
    }

    private fun callActivityIntent() {
        var mUserSessions = UserSessions(this@SplashActivity)
        mUserSessions.saveLanguage(this@SplashActivity, "1");
        //CommonMethods.moveWithClear(this, MainActivity::class.java)
        if (UserSessions().getUserInfo(this@SplashActivity) != null) {
            CommonMethods.moveWithClear(this, MainActivity::class.java)
        } else {
            CommonMethods.moveWithClear(this, LoginActivity::class.java)
        }
    }
}