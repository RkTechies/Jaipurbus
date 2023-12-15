package jaipurbus.jaipur.tourism.jaipurbus.ui.search.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codersworld.jplibs.beans.BusDirectionBean;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import jaipurbus.jaipur.tourism.jaipurbus.R;


public class ItemAdapter extends ArrayAdapter<BusDirectionBean> {

    Context context;
    int layoutResourceId;
    BusDirectionBean data[] = null;

    public ItemAdapter(Context context, int layoutResourceId, BusDirectionBean[] data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public void loadAds(AdView mView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        mView.loadAd(adRequest);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        IteamHolder holder = null;
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);//layoutResourceId=R.id.list
            holder = new IteamHolder();
            holder.adView1 = (AdView) view.findViewById(R.id.adView1);
            holder.imgIcon1 = (ImageView) view.findViewById(R.id.imgRoot1);
            holder.root1 = (TextView) view.findViewById(R.id.txtRoot1);
            holder.arrow = (ImageView) view.findViewById(R.id.arrow);
            holder.imgIcon2 = (ImageView) view.findViewById(R.id.imgRoot2);
            holder.root2 = (TextView) view.findViewById(R.id.txtRoot2);
            holder.km = (TextView) view.findViewById(R.id.txt_KM);
            holder.stops = (TextView) view.findViewById(R.id.txt_stop);
            holder.seedetail = (ImageView) view.findViewById(R.id.seedetail);
            view.setTag(holder);
        } else {
            holder = (IteamHolder) view.getTag();
        }
        BusDirectionBean iteam = data[position];
        if (iteam.icon1 == 9) {
            holder.imgIcon1.setVisibility(View.GONE);
            holder.seedetail.setVisibility(View.GONE);
        } else {
            holder.seedetail.setVisibility(View.VISIBLE);
            holder.imgIcon1.setVisibility(View.VISIBLE);
            holder.imgIcon1.setImageResource(iteam.icon1);
        }

        holder.root1.setText(iteam.root1);
        if (iteam.arrow == 9) {
            holder.arrow.setVisibility(View.GONE);
        }else {
            holder.arrow.setVisibility(View.VISIBLE);
            holder.arrow.setImageResource(iteam.arrow);
        }
        if (iteam.icon2 == 9) {
            holder.imgIcon2.setVisibility(View.GONE);
        }else {
            holder.imgIcon2.setVisibility(View.VISIBLE);
            holder.imgIcon2.setImageResource(iteam.icon2);
        }
        holder.root2.setText(iteam.root2);
        holder.stops.setText(iteam.stops);
        holder.km.setText(iteam.km);
        counter++;
        if (counter == 4) {
            holder.adView1.setVisibility(View.VISIBLE);
            loadAds(holder.adView1);
            counter = 0;
        }
        return view;
    }

    int counter = 0;

    static class IteamHolder {
        ImageView imgIcon1, arrow, imgIcon2, seedetail;
        TextView root1, root2, stops, km;
        AdView adView1;

    }
}
