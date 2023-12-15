package jaipurbus.jaipur.tourism.jaipurbus.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codersworld.jplibs.beans.PlacesBean
import com.codersworld.jplibs.beans.PlacesTypeBean
import com.codersworld.jplibs.database.DatabaseHelper
import com.codersworld.jplibs.database.DatabaseManager
import com.codersworld.jplibs.database.dao.PlacesDao
import com.codersworld.jplibs.listeners.OnPlacesListener
import com.codersworld.jplibs.listeners.OnResponse
import com.codersworld.jplibs.rest.UniverSelObjct
import com.codersworld.jplibs.storage.UserSessions
import com.codersworld.jplibs.utils.CommonMethods
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityAboutUsBinding
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityPlacesBinding
import jaipurbus.jaipur.tourism.jaipurbus.ui.places.adapters.PlaceAdapter
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper

class AboutUsActivity : BaseActivity()  {
     lateinit var binding:ActivityAboutUsBinding

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
        binding = DataBindingUtil.setContentView(this@AboutUsActivity,R.layout.activity_about_us)
       // CommonMethods.setTextWithHtml(getString(R.string.msg_about_us),binding.txtMsg)
        binding.imgBack.setOnClickListener {
            finish()
        }
    }
}