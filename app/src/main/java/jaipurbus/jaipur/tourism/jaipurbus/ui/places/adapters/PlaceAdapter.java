package jaipurbus.jaipur.tourism.jaipurbus.ui.places.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.codersworld.jplibs.beans.PlacesBean;
import com.codersworld.jplibs.beans.PlacesTypeBean;
import com.codersworld.jplibs.listeners.OnPlacesListener;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ItemPlaceBinding;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ItemPlacesBinding;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    Context ctx;
    static OnPlacesListener mListener;
     public static ArrayList<PlacesBean> mListPlaces = new ArrayList<>();

    public PlaceAdapter(Context ctx, ArrayList<PlacesBean> mListPlaces, OnPlacesListener mListener) {
        this.ctx = ctx;
        this.mListPlaces = mListPlaces;
         this.mListener = mListener;
      }

    public static void callListener(PlacesBean mBeanPlace,int position){
        //Log.e("mListPlaces",mListPlaces.size()+"");
        if(mListener !=null ){
            mListener.onPlace(mBeanPlace,position);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_place, viewGroup, false);
        return new ViewHolder(v);
    }
    public void loadAds(AdView mView){
        AdRequest adRequest = new AdRequest.Builder().build();
        mView.loadAd(adRequest);

    }
int bindPos=0;
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        CommonMethods.setText(mListPlaces.get(i).getTitle(), viewHolder.binding.txtTitle);
         CommonMethods.loadImageWithCenterCrop1(ctx,mListPlaces.get(i).getImage(),viewHolder.binding.imgIcon );
        bindPos = bindPos+1;
        viewHolder.binding.adView1.setVisibility(View.GONE);
         if (bindPos==4){
             bindPos=0;
             viewHolder.binding.adView1.setVisibility(View.VISIBLE);
             loadAds(viewHolder.binding.adView1);
         }
    }

    @Override
    public int getItemCount() {
        return mListPlaces != null ? mListPlaces.size() : 0;
    }
     public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemPlaceBinding binding;
         public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.clMain.setOnClickListener(this);
         }

        @Override
        public void onClick(View view) {
            callListener(mListPlaces.get(getAdapterPosition()),getAdapterPosition());
        }
    }
}
