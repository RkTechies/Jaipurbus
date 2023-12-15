package jaipurbus.jaipur.tourism.jaipurbus.ui.search.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.codersworld.jplibs.listeners.OnStationSelectListener;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import java.util.ArrayList;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ItemStationsBinding;


public class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.ViewHolder> {
    Context ctx;
    static OnStationSelectListener mListener;
    public static ArrayList<String> mListStations = new ArrayList<>();
    public static ArrayList<String> mListStationsFiltered = new ArrayList<>();

    public StationsAdapter(Context ctx, ArrayList<String> mListStations, OnStationSelectListener mListener) {
        this.ctx = ctx;
        this.mListStationsFiltered = mListStations;
        this.mListStations.addAll(this.mListStationsFiltered);
        this.mListener = mListener;
        //Log.e("Finalsda1=>", mListStationsFiltered.size()+" 0 ");

        ArrayList<ArrayList<String>> mListData = new ArrayList();
        ArrayList<String> mListData1 = new ArrayList();
        //stations = removeDuplicates(stations)
        for (int a = 0; a < mListStationsFiltered.size(); a++) {
            if (mListData1.size() == 100 || a == (mListStationsFiltered.size() - 1)) {
                mListData.add(mListData1);
                mListData1 = new ArrayList();
            }
            mListData1.add(mListStationsFiltered.get(a));
        }
        for (int a = 0; a < mListData.size(); a++) {
            //Log.e("Finalsda=>", new Gson().toJson(mListData.get(a)));
        }

    }

    public static void callListener(String strStation) {
        if (mListener != null) {
            mListener.onStationSelect(strStation);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_stations, viewGroup, false);
        return new ViewHolder(v);
    }

    int counter = 0;

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        CommonMethods.setTextWithNA(mListStations.get(i), viewHolder.binding.txtTitle);
        viewHolder.binding.adView1.setVisibility(View.GONE);
        counter++;
        if (counter==4){
            viewHolder.binding.adView1.setVisibility(View.VISIBLE);
            loadAds(viewHolder.binding.adView1);
            counter=0;
        }
    }
    public void loadAds(AdView mView){
        AdRequest adRequest = new AdRequest.Builder().build();
        mView.loadAd(adRequest);

    }
    @Override
    public int getItemCount() {
        return mListStations != null ? mListStations.size() : 0;
    }

    public void filterData(String strKey) {
        strKey = strKey.toLowerCase();
        mListStations = new ArrayList<>();
        if (CommonMethods.isValidString(strKey)) {
            for (String station : mListStationsFiltered) {
                if (station.toLowerCase().contains(strKey)) {
                    mListStations.add(station);
                }
            }
        } else {
            mListStations.addAll(mListStationsFiltered);
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemStationsBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.txtTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            callListener(mListStations.get(getAdapterPosition()));
        }
    }
}
