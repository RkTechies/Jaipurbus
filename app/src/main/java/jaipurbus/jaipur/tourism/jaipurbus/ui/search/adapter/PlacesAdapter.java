package jaipurbus.jaipur.tourism.jaipurbus.ui.search.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.codersworld.jplibs.beans.PlacesTypeBean;
import com.codersworld.jplibs.listeners.OnPlacesListener;
import com.codersworld.jplibs.listeners.OnStationSelectListener;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.gson.Gson;

import java.util.ArrayList;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ItemPlacesBinding;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ItemStationsBinding;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {
    Context ctx;
    static OnPlacesListener mListener;
     public static ArrayList<PlacesTypeBean> mListPlaces = new ArrayList<>();

    public PlacesAdapter(Context ctx, ArrayList<PlacesTypeBean> mListPlaces, OnPlacesListener mListener) {
        this.ctx = ctx;
        this.mListPlaces = mListPlaces;
         this.mListener = mListener;
      }

    public static void callListener(PlacesTypeBean mBeanPlace){
        if(mListener !=null ){
            mListener.onPlaceCategory(mBeanPlace);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_places, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        CommonMethods.setText(mListPlaces.get(i).getTitle(), viewHolder.binding.txtTitle);
        viewHolder.binding.clMain.setCardBackgroundColor(Color.parseColor(mListPlaces.get(i).getBackground_color()));
        CommonMethods.loadImage(ctx,mListPlaces.get(i).getImage(),viewHolder.binding.imgIcon );
    }

    @Override
    public int getItemCount() {
        return mListPlaces != null ? mListPlaces.size() : 0;
    }
     public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemPlacesBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.clMain.setOnClickListener(this);
         }

        @Override
        public void onClick(View view) {
            callListener(mListPlaces.get(getAdapterPosition()));
        }
    }
}
