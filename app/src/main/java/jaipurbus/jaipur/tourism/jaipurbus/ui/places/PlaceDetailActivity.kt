package jaipurbus.jaipur.tourism.jaipurbus.ui.places

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codersworld.jplibs.beans.PlacesBean
import com.codersworld.jplibs.database.DatabaseHelper
import com.codersworld.jplibs.database.DatabaseManager
import com.codersworld.jplibs.database.dao.PlacesDao
import com.codersworld.jplibs.listeners.OnResponse
import com.codersworld.jplibs.rest.UniverSelObjct
import com.codersworld.jplibs.storage.UserSessions
import com.codersworld.jplibs.utils.CommonMethods
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityPlaceDetailBinding
import jaipurbus.jaipur.tourism.jaipurbus.ui.BaseActivity
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments.FragmentPlaceDetail
import jaipurbus.jaipur.tourism.jaipurbus.ui.places.adapters.PlacePagerAdapter
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper


class PlaceDetailActivity : BaseActivity() , OnResponse<UniverSelObjct> ,OnClickListener{
    var mListPlaces :ArrayList<PlacesBean> = ArrayList();
    var mListFragments :ArrayList<FragmentPlaceDetail> = ArrayList()
    var position =0;
    var category_id = "0"
    var place_id = ""
    var mbean : PlacesBean? = null
    lateinit var binding:ActivityPlaceDetailBinding

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
        binding = DataBindingUtil.setContentView(this@PlaceDetailActivity,R.layout.activity_place_detail)
        //setContentView(R.layout.activity_places)
        DatabaseManager.initializeInstance(DatabaseHelper(this@PlaceDetailActivity))
        val database = DatabaseManager.getInstance().openDatabase()
        if (intent.hasExtra("position")){
            position = intent.getIntExtra("position",0) as Int
        }
        if (intent.hasExtra("place")){
            mbean = CommonMethods.getSerializable(intent,"place",PlacesBean::class.java) as PlacesBean
            category_id=mbean!!.category_id
            place_id=mbean!!.id
         }
         initData(0);
        binding.imgBack.setOnClickListener (this)
        binding.txtPrevious.setOnClickListener (this)
        binding.txtNext.setOnClickListener (this)
    }
    fun initData(type:Int){
        //PlaceAdapter
        DatabaseManager.getInstance().executeQuery { database ->
            val dao = PlacesDao(database, this@PlaceDetailActivity)
            mListPlaces = dao.selectAll(category_id,place_id)
        }
        if (!CommonMethods.isValidArrayList(mListPlaces)) {
            if (type == 0) {
                ApiHelper(this@PlaceDetailActivity).fetchPlaces(true, this,category_id)
            }else{

            }
        } else {
            //Log.e("mListPlaces2", mListPlaces.size.toString() + " "+category_id)
            mListFragments = ArrayList()
            for (a in mListPlaces.indices){
                mListFragments.add(FragmentPlaceDetail())
            }
            binding.bottomLayout.visibility=View.GONE
            //changeFragment(position)
            binding.vpPlaces.adapter = PlacePagerAdapter(this,mListPlaces,mListFragments)
            binding.vpPlaces.isUserInputEnabled = false
            changePagerPosition()

        }
    }
fun changePagerPosition(){
    binding.vpPlaces.currentItem = position
    if (mListPlaces.size>1){
        binding.bottomLayout.visibility=View.GONE
        if (position>0){
            binding.txtPrevious.visibility=View.VISIBLE
        }else{
            binding.txtPrevious.visibility=View.GONE
        }
        if (position<mListPlaces.size-1){
            binding.txtNext.visibility=View.VISIBLE
        }else{
            binding.txtNext.visibility=View.GONE
        }

    }

}

    override fun onSuccess(response: UniverSelObjct?) {
        initData(1);
    }

    override fun onError(type: String?, error: String?) {

    }

    override fun onClick(v: View) {
         when(v.id){
             R.id.imgBack->{
                 finish()
             }
             R.id.txtPrevious->{
                 if (position>0){
                     position--
                 }
                 changePagerPosition()
             }
             R.id.txtNext->{
                 if (position<mListPlaces.size-1){
                     position++
                 }
                 changePagerPosition()
             }
         }
    }

}