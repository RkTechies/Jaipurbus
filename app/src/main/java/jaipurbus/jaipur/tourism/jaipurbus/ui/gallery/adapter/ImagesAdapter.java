package jaipurbus.jaipur.tourism.jaipurbus.ui.gallery.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

 
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codersworld.jplibs.customviews.TouchImageView;
import com.codersworld.jplibs.utils.CommonMethods;

import java.util.ArrayList;

import jaipurbus.jaipur.tourism.jaipurbus.R;

public class ImagesAdapter extends PagerAdapter {
    /* access modifiers changed from: private */
    public Activity _activity;
    private ArrayList<String> _imagePaths = new ArrayList<>();
    private LayoutInflater inflater;

    public ImagesAdapter(Activity activity, ArrayList<String> imagePaths) {
        this._activity = activity;
        this._imagePaths = imagePaths;
    }

    public int getCount() {
        return this._imagePaths.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) this._activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.inflater = layoutInflater;
        View viewLayout = layoutInflater.inflate(R.layout.layout_fullscreen_image, container, false);
        CommonMethods.setBackground(_activity,_imagePaths.get(position), viewLayout.findViewById(R.id.rlContainer));

        Glide.with(this._activity).load(this._imagePaths.get(position)).apply(new RequestOptions().placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_gallery)).into((ImageView) (TouchImageView) viewLayout.findViewById(R.id.imgDisplay));
        ((ViewPager) container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
