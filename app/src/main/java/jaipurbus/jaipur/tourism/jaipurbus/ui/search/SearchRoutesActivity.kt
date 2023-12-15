package jaipurbus.jaipur.tourism.jaipurbus.ui.search

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codersworld.jplibs.beans.BusRoutesBean
import com.codersworld.jplibs.listeners.OnStationSelectListener
import com.codersworld.jplibs.storage.AppHelper
import com.codersworld.jplibs.storage.UserSessions
import com.codersworld.jplibs.utils.CommonMethods
import com.google.android.gms.ads.MobileAds
import com.google.gson.Gson
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivitySearchRoutesBinding
import jaipurbus.jaipur.tourism.jaipurbus.ui.BaseActivity
import jaipurbus.jaipur.tourism.jaipurbus.ui.search.adapter.StationsAdapter
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper


class SearchRoutesActivity : BaseActivity(), OnStationSelectListener {

    lateinit var bindings: ActivitySearchRoutesBinding
    var stations: ArrayList<String> = ArrayList()
    var mAdapter: StationsAdapter? = null
    var type = "0";

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
        this.bindings = DataBindingUtil.setContentView<ViewDataBinding>(this,R.layout.activity_search_routes) as ActivitySearchRoutesBinding
        setSupportActionBar(this.bindings.toolbars)
        window.setSoftInputMode(3)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (intent.hasExtra("type")) {
            type = intent.getStringExtra("type") as String
        }

        MobileAds.initialize(
            this@SearchRoutesActivity
        ) { }




        bindings.rvRoutes.layoutManager = LinearLayoutManager(this@SearchRoutesActivity, RecyclerView.VERTICAL, false)
        var mList: ArrayList<BusRoutesBean> =  AppHelper().getBusRoutes(this@SearchRoutesActivity) as ArrayList<BusRoutesBean>
        for (a in mList.indices) {
            var buses = Gson().fromJson(mList[a].bus, Array<String>::class.java) as Array<String>
            for (b in buses.indices) {
                if (b > 0) {
                    if (b > 0 && !stations.contains(buses[b])) {
                       // Log.e("ISEXIST =>", "no exists : " + buses[b])
                        stations.add(buses[b])
                    } else {
                       // Log.e("ISEXIST =>", "already exists : " + buses[b])
                    }
                }
            }
        }
        stations.sort()
        //Log.e("Final=>", stations.size.toString() + " : " + Gson().toJson(stations))
        if (CommonMethods.isValidArrayList(stations)) {
            bindings.rvRoutes.hasFixedSize()
            mAdapter =
                StationsAdapter(this@SearchRoutesActivity, stations, this@SearchRoutesActivity)
            bindings.rvRoutes.adapter = mAdapter
            //   filter("")

            bindings.etSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (CommonMethods.isValidString(s.toString())) {
                        bindings.imgClear.visibility = View.VISIBLE
                    } else {
                        bindings.imgClear.visibility = View.GONE
                    }
                    filter(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence,start: Int,count: Int,after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }
            })
            bindings.imgClear.setOnClickListener {
                bindings.etSearch.setText("")
                filter("")
            }
        }
    }

    fun filter(s: String) {
        if (mAdapter != null) {
            mAdapter!!.filterData(s.toString())
        }
    }

    fun <T> removeDuplicates(list: ArrayList<T>): ArrayList<T> {
        // Create a new ArrayList
        val newList = ArrayList<T>()
        // Traverse through the first list
        for (element in list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element)
            }
        }
        //Log.e("newList", Gson().toJson(newList))
        // return the new list
        return newList
    }

    fun checkIfStationExists(str: String): Boolean {
        if (CommonMethods.isValidString(str) && CommonMethods.isValidArrayList(stations)) {
            var isTrue = false
            for (a in stations) {
                if (a.lowercase().equals(str.lowercase(), true)) {
                    isTrue = true
                }
            }
            return isTrue
        } else {
            return false
        }
    }

    override fun onStationSelect(strStation: String) {
        if (CommonMethods.isValidString(strStation)) {
            var pref: SharedPreferences = getSharedPreferences("findBus", 0)
            var edit: SharedPreferences.Editor = pref.edit()
            if (type.equals("0", true)) {
                edit.putString("source", strStation)
            } else {
                edit.putString("destination", strStation)
            }
            edit.commit()
            val returnIntent = Intent()
            returnIntent.putExtra("type", type)
            returnIntent.putExtra("station", strStation)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId != android.R.id.home) {
            return super.onOptionsItemSelected(item)
        }
        finish()
        return true
    }

}