package jaipurbus.jaipur.tourism.jaipurbus.ui.places.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView 
import com.bumptech.glide.Glide
import com.codersworld.jplibs.loopingviewpager.LoopingPagerAdapter
import com.codersworld.jplibs.utils.CommonMethods
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.ui.gallery.GalleryActivity
import kotlin.collections.ArrayList

class ImagesAdapter(
    var mContext: Context,
    itemList: ArrayList<String>,
    isInfinite: Boolean
) : LoopingPagerAdapter<String>(itemList, isInfinite) {

    override fun getItemViewType(listPosition: Int): Int {
        return VIEW_TYPE_NORMAL
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(container.context)
            .inflate(R.layout.item_images, container, false)
    }

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val rlContainer = convertView.findViewById<TextView>(R.id.rlContainer) as RelativeLayout
        val imgIcon = convertView.findViewById<TextView>(R.id.imgBanner) as ImageView
        CommonMethods.setBackground(mContext, itemList!![listPosition], rlContainer)
        Glide.with(mContext)
            .load(itemList!![listPosition])
            .placeholder(R.drawable.app_icon)
            .error(R.drawable.app_icon)
            .into(imgIcon)
        imgIcon.setOnClickListener {
            var mImages: ArrayList<String> = ArrayList()
            for (i in itemList!!.indices) {
                var mBn = itemList!![i]
                mImages.add(mBn)
            }
            if (CommonMethods.isValidArrayList(mImages)) {
                mContext.startActivity(Intent(mContext, GalleryActivity::class.java).putExtra("mData",mImages).putExtra("position", listPosition))
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_NORMAL = 100
        private const val VIEW_TYPE_SPECIAL = 101
    }
}