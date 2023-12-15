package jaipurbus.jaipur.tourism.jaipurbus.ui.home

import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.codersworld.jplibs.beans.CommonBean
import com.codersworld.jplibs.listeners.OnConfirmListener
import com.codersworld.jplibs.listeners.OnPageChangeListener
import com.codersworld.jplibs.listeners.OnResponse
import com.codersworld.jplibs.rest.ApiCall
import com.codersworld.jplibs.rest.UniverSelObjct
import com.codersworld.jplibs.storage.UserSessions
import com.codersworld.jplibs.utils.CommonMethods
import com.codersworld.jplibs.utils.Tags
import com.google.android.gms.ads.interstitial.InterstitialAd
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityMainBinding
import jaipurbus.jaipur.tourism.jaipurbus.ui.BaseActivity
import jaipurbus.jaipur.tourism.jaipurbus.ui.dialogs.CustomDialog
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.extension.JBNavigationPosition
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.extension.active
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.extension.createFragment
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.extension.findNavigationPositionById
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.extension.getTag
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.extension.switchFragment
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper


class MainActivity : BaseActivity(), OnPageChangeListener, OnConfirmListener,
    OnResponse<UniverSelObjct> {
    lateinit var binding: ActivityMainBinding
    private var navPosition: JBNavigationPosition = JBNavigationPosition.SEARCHBUS

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
        this.binding = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_main
        ) as ActivityMainBinding
        binding.bottomNavigation.apply {
            // Set a default position
            active(navPosition.position) // Extension function
            // Set a listener for handling selection events on bottom navigation items
            setOnNavigationItemSelectedListener { item ->
                navPosition = findNavigationPositionById(item.itemId)
                switchFragment(navPosition)
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.navSearchBus
        val prefs: SharedPreferences =getSharedPreferences("JB_DEVICE_ID", 0)
        var device_id  = prefs.getString("device_id", "").toString()
        var tkn = UserSessions().getAccessToken(this@MainActivity)
        //Log.e("device_id",device_id+" => "+tkn)
        if (!CommonMethods.isValidString(tkn) && CommonMethods.isValidString(device_id)){
            ApiCall(this@MainActivity).saveFCM(this,false,device_id)
        }
    }

    private fun switchFragment(navPosition: JBNavigationPosition): Boolean {
        return findFragment(navPosition).let {
            supportFragmentManager.switchFragment(it, navPosition.getTag()) // Extension function
        }
    }

    private fun findFragment(position: JBNavigationPosition): Fragment {
        return supportFragmentManager.findFragmentByTag(position.getTag())
            ?: position.createFragment()
    }

    override fun onPageChange(mPage: String) {
            makePageChange(mPage)
    }

    fun makePageChange(mPage: String?) {
        binding.llHeader.visibility = View.VISIBLE
        try {
            when (mPage) {
                "search" -> {
                    binding.llHeader.visibility = View.GONE
                    binding.bottomNavigation.selectedItemId = R.id.navSearchBus
                }

                "routes" -> {
                    binding.bottomNavigation.selectedItemId = R.id.navRoutes
                }

                "places" -> {
                    binding.bottomNavigation.selectedItemId = R.id.navPlaces
                }

                "settings" -> {
                    binding.bottomNavigation.selectedItemId = R.id.navSettings
                }
            }
        } catch (ex1: Exception) {
            makePageChange(mPage)
        }
    }

    override fun onBackPressed() {
        if (!navPosition.getTag().equals("FragmentFindBus", true)) {
            binding.bottomNavigation.selectedItemId = R.id.navSearchBus
            //switchFragment(BBNavigationPosition.HOME)
        } else {
            val customDialog = CustomDialog(
                this@MainActivity,
                resources.getString(R.string.msg_exit_confirm),
                resources.getString(R.string.lbl_no),
                this
            )
            customDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customDialog.show()
        }
    }

    override fun onConfirm(isTrue: Boolean) {
        if (isTrue) {
            finishAffinity()
        }
    }

    override fun onSuccess(response: UniverSelObjct) {
        when (response.methodname) {
            Tags.JB_API_SAVE_TOKEN -> {
                var mCommonBean = response.response as CommonBean
                if (mCommonBean != null && mCommonBean.status == 1) {
                    UserSessions().saveAccessToken(this@MainActivity, "1")
                }
            }
        }
    }

    override fun onError(type: String?, error: String?) {

    }

}