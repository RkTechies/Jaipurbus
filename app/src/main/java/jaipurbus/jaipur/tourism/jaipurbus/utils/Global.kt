package jaipurbus.jaipur.tourism.jaipurbus.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.codersworld.jplibs.loopingviewpager.LoopingViewPager
import com.codersworld.jplibs.loopingviewpager.indicator.CustomShapePagerIndicator
import jaipurbus.jaipur.tourism.jaipurbus.R
import java.lang.Exception


public class Global {
    var mContext: Context? = null

    fun Global() {}

    fun Global(ctx: Context?) {
        mContext = ctx
    }

    fun Global(ctx: Activity?) {
        mContext = ctx
    }

    fun getVersionName(mActivity: Activity): String {
        try {
            val manager = mActivity.packageManager
            val info = manager.getPackageInfo(mActivity.packageName, PackageManager.GET_ACTIVITIES)
            return info.versionName
        }catch (ex:Exception){
            ex.printStackTrace()
        return "1.8"
        }
    }

    fun setUpIndicator(
        ctx: Context,
        dotsIndicator: CustomShapePagerIndicator,
        viewpager: LoopingViewPager,
        type: Int
    ) {
        //Custom bind indicator
        dotsIndicator.highlighterViewDelegate = {
            val highlighter = View(ctx)
            highlighter.layoutParams = FrameLayout.LayoutParams(10.dp(), 10.dp())
            highlighter.setBackgroundResource(R.drawable.selected_circle_bg)
            highlighter
        }
        dotsIndicator.unselectedViewDelegate = {
            val unselected = View(ctx)
            unselected.layoutParams = LinearLayout.LayoutParams(10.dp(), 10.dp())
            unselected.setBackgroundResource(R.drawable.unselected_circle_bg)
            unselected
        }
        viewpager.onIndicatorProgress = { selectingPosition, progress ->
            dotsIndicator.onPageScrolled(
                selectingPosition,
                progress
            )
        }
        dotsIndicator.updateIndicatorCounts(viewpager.indicatorCount)

    }
}