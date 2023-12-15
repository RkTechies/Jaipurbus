package jaipurbus.jaipur.tourism.jaipurbus.ui.gallery;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.codersworld.jplibs.storage.UserSessions;

import java.util.ArrayList;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityGalleryBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.BaseActivity;
import jaipurbus.jaipur.tourism.jaipurbus.ui.gallery.adapter.ImagesAdapter;
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper;

public class GalleryActivity extends BaseActivity {
    ImagesAdapter adapter;
    ActivityGalleryBinding binding;
    Activity mActivity;
    ArrayList<String> mListImages = new ArrayList<>();

    public void onBackClick(View view) {
        onBackPressed();
    }


    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        new ApiHelper(this).setApplicationlanguage(this, new UserSessions().getLanguage(this));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityGalleryBinding) DataBindingUtil.setContentView(this, R.layout.activity_gallery);
        this.mActivity = this;
        if (getIntent().hasExtra("mData")) {
            this.mListImages = (ArrayList) getIntent().getSerializableExtra("mData");
        }
        int position = getIntent().getIntExtra("position", 0);
        this.adapter = new ImagesAdapter(this, this.mListImages);
        this.binding.viewPager.setAdapter(this.adapter);
        this.binding.viewPager.setCurrentItem(position);
    }
}
