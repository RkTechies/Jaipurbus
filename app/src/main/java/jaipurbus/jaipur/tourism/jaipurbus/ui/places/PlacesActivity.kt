package jaipurbus.jaipur.tourism.jaipurbus.ui.places

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityPlacesBinding
import jaipurbus.jaipur.tourism.jaipurbus.ui.BaseActivity
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments.FragmentFindBus
import jaipurbus.jaipur.tourism.jaipurbus.ui.places.adapters.PlaceAdapter
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper

class PlacesActivity : BaseActivity() , OnResponse<UniverSelObjct>, OnPlacesListener {
    var mListPlaces :ArrayList<PlacesBean> = ArrayList();
    var category_id ="0"
    lateinit var binding:ActivityPlacesBinding

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
        binding = DataBindingUtil.setContentView(this@PlacesActivity,R.layout.activity_places)
        //setContentView(R.layout.activity_places)
        DatabaseManager.initializeInstance(DatabaseHelper(this@PlacesActivity))
        val database = DatabaseManager.getInstance().openDatabase()
        MobileAds.initialize(this@PlacesActivity) { }
        if (intent.hasExtra("category_id")){
            category_id = intent.getStringExtra("category_id") as String
        }
        binding.rvPlaces.layoutManager = LinearLayoutManager(this@PlacesActivity,RecyclerView.VERTICAL,false)
        initData(0);
        binding.imgBack.setOnClickListener {
            finish()
        }
    }
    fun initData(type:Int){
        //PlaceAdapter
        DatabaseManager.getInstance().executeQuery { database ->
            val dao = PlacesDao(database, this@PlacesActivity)
            mListPlaces = dao.selectAll(category_id,"")
        }
        if (!CommonMethods.isValidArrayList(mListPlaces)) {
            binding.rvPlaces.visibility=View.VISIBLE
            binding.imgCommingSoon.visibility=View.GONE
            if (type == 0) {
                ApiHelper(this@PlacesActivity).fetchPlaces(true, this,category_id)
            }else{
                binding.rvPlaces.visibility=View.GONE
                binding.imgCommingSoon.visibility=View.VISIBLE
                Glide.with(this).load(R.drawable.comming_soon).into(binding.imgCommingSoon);
            }
        } else {
            binding.rvPlaces.setAdapter(PlaceAdapter(this@PlacesActivity, mListPlaces, this))
        }
    }

    override fun onPlaceCategory(mBeanPlace: PlacesTypeBean?) {

    }

    override fun onResume() {
        super.onResume()
        loadInterstitialAds()

    }
    private var mInterstitialAd: InterstitialAd? = null
    fun loadInterstitialAds() {
        mInterstitialAd = null;
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this@PlacesActivity,
            getString(R.string.admob_unit_id_intersitial),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    mInterstitialAd = interstitialAd
                    Log.i(FragmentFindBus.TAG, "onAdLoaded")
                    //  adCallBack();
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    // Handle the error
                    Log.d(FragmentFindBus.TAG, loadAdError.toString())
                    mInterstitialAd = null
                }
            })
    }
    fun moveToNext(mBeanPlace: PlacesBean?,position:Int) {
        startActivity(Intent(this@PlacesActivity,PlaceDetailActivity::class.java).putExtra("place",mBeanPlace).putExtra("position",position))
    }
    var counter = 0

    fun adCallBack(mBeanPlace: PlacesBean?,position:Int) {
        if (mBeanPlace != null ) {
            if (mInterstitialAd != null) {
                mInterstitialAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d(FragmentFindBus.TAG, "Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Set the ad reference to null so you don't show the ad a second time.
                        Log.d(FragmentFindBus.TAG, "Ad dismissed fullscreen content.")
                        mInterstitialAd = null
                        moveToNext(mBeanPlace, position)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        // Called when ad fails to show.
                        Log.e(FragmentFindBus.TAG, "Ad failed to show fullscreen content.")
                        mInterstitialAd = null
                        moveToNext(mBeanPlace, position)
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d(FragmentFindBus.TAG, "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(FragmentFindBus.TAG, "Ad showed fullscreen content.")
                    }
                }
                counter++
                if (counter % 2 == 0) {
                    //Is even
                    moveToNext(mBeanPlace, position)
                } else {
                    mInterstitialAd!!.show(this@PlacesActivity)
                    //Is odd
                }
                //moveToNext(mBeanPlace, position)
            } else {
                moveToNext(mBeanPlace, position)
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
        } else {
            CommonMethods.errorToast(this@PlacesActivity, getString(R.string.error_no_result))
        }
    }

    override fun onPlace(mBeanPlace: PlacesBean?,position:Int) {
        adCallBack(mBeanPlace,position)
        //Log.e("mListPlaces1", mListPlaces.size.toString() + "")
    }

    override fun onSuccess(response: UniverSelObjct?) {
        initData(1);
    }

    override fun onError(type: String?, error: String?) {

    }

}