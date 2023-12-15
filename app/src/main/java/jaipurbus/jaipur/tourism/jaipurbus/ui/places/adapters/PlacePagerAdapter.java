package jaipurbus.jaipur.tourism.jaipurbus.ui.places.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.codersworld.jplibs.beans.PlacesBean;
import com.codersworld.jplibs.utils.CommonMethods;

import java.util.ArrayList;

import jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments.FragmentPlaceDetail;

public class PlacePagerAdapter extends FragmentStateAdapter {
    ArrayList<PlacesBean> mListPlaces=new ArrayList<>();
    ArrayList<FragmentPlaceDetail> mFragments = new ArrayList<>();
    public PlacePagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<PlacesBean> mBeanPlaces, ArrayList<FragmentPlaceDetail> mFragments) {
        super(fragmentActivity);
        this.mListPlaces=mBeanPlaces;
        this.mFragments=mFragments;
    }

    public PlacePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public PlacePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        FragmentPlaceDetail fragment = mFragments.get(position);
        Bundle args = new Bundle();
        args.putSerializable("place", mListPlaces.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return (CommonMethods.isValidArrayList(mListPlaces))?mListPlaces.size():0;
    }
}
