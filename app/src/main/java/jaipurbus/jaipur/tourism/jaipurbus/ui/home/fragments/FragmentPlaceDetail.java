package jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.codersworld.jplibs.beans.PlacesBean;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.FragmentPlaceDetailBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.places.adapters.ImagesAdapter;
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper;
import jaipurbus.jaipur.tourism.jaipurbus.utils.Global;

public class FragmentPlaceDetail extends Fragment {
    PlacesBean mBeanPlace = null;

    @NotNull
    public static final String TAG = FragmentPlaceDetail.class.getSimpleName();

    public FragmentPlaceDetail() {
        //if required
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBeanPlace = (PlacesBean) getArguments().getSerializable("place");
        }
    }

    FragmentPlaceDetailBinding binding;
    public void loadAds(AdView mView){
        AdRequest adRequest = new AdRequest.Builder().build();
        mView.loadAd(adRequest);

    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        new ApiHelper(requireActivity()).setApplicationlanguage(requireActivity(), new UserSessions().getLanguage(requireActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_place_detail, container, false);
        binding = DataBindingUtil.bind(view);
        binding.dotsIndicator.setVisibility(View.GONE);
        MobileAds.initialize(requireActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        loadAds(binding.adView);
        loadAds(binding.adView1);
        loadAds(binding.adView2);

        if (mBeanPlace != null) {
            //Log.e("mBeanPlace", new Gson().toJson(mBeanPlace));
            if (CommonMethods.isValidArrayList(mBeanPlace.getGallery()) && mBeanPlace.getGallery().size() > 1) {
                binding.dotsIndicator.setVisibility(View.VISIBLE);
            }
            ImagesAdapter mImagesAdapter = new ImagesAdapter(requireActivity(), mBeanPlace.getGallery(), true);
            binding.viewpager.setAdapter(mImagesAdapter);
            binding.viewpager.setCurrentItem(0);
            binding.viewpager.resumeAutoScroll();
            new Global().setUpIndicator(requireActivity(), binding.dotsIndicator, binding.viewpager, 1);
            CommonMethods.setText(mBeanPlace.getTitle()+((mBeanPlace.getCategory_id().equalsIgnoreCase("2"))?"("+getString(R.string.lbl_star, mBeanPlace.getStar())+")":""), binding.txtTitle);
            CommonMethods.setTextWithHtml(mBeanPlace.getDescription(), binding.txtDescription);
            CommonMethods.setText(getString(R.string.lbl_phone, mBeanPlace.getPhone()), binding.txtPhone);
            CommonMethods.setText(getString(R.string.lbl_email, mBeanPlace.getEmail()), binding.txtEmail);
            CommonMethods.setText(getString(R.string.lbl_website, mBeanPlace.getWebsite()), binding.txtWebsite);
            CommonMethods.setText( mBeanPlace.getAddress(), binding.txtAddress);
            setHideShowText(mBeanPlace.getPhone(), binding.txtPhone);
            setHideShowText(mBeanPlace.getEmail(), binding.txtEmail);
            setHideShowText(mBeanPlace.getWebsite(), binding.txtWebsite);
            setHideShowText(mBeanPlace.getPhone()+mBeanPlace.getEmail()+mBeanPlace.getWebsite(), binding.llContact);
            setHideShowText(mBeanPlace.getAddress() , binding.llAddress);
            binding.cardDirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uri = "http://maps.google.com/maps?daddr=" + mBeanPlace.getLat() + "," + mBeanPlace.getLng();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            });
        }
        return view;
    }

    public void setHideShowText(String strText, View mView) {
        if (CommonMethods.isValidString(strText)){
            mView.setVisibility(View.VISIBLE);
        }else{
            mView.setVisibility(View.GONE);
        }
    }

    @NotNull
    public static Fragment newInstance() {
        return new FragmentPlaceDetail();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }
}