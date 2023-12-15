package jaipurbus.jaipur.tourism.jaipurbus.ui.home.result;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codersworld.jplibs.beans.ResultRouteBean;
import com.google.gson.Gson;

import jaipurbus.jaipur.tourism.jaipurbus.R;


public class ResultRouteAdapter extends ArrayAdapter<ResultRouteBean> {


    Context context;

    int layoutResourceId;
    ResultRouteBean data[] = null;
    int pos=1;
    int chno=0;
    public ResultRouteAdapter(Context context, int layoutResourceId, ResultRouteBean[] data) {
        super(context, layoutResourceId,data);
        //Log.e("datadata",new Gson().toJson(data));
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public long getItemId(int position) {
        pos=position;
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ResultRouteBeanHolder holder = null;
        if(view == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId,parent,false);//layoutResourceId=R.id.list
            holder = new ResultRouteBeanHolder();
            holder.stop1 = (TextView)view.findViewById(R.id.txt_stop11);

            holder.noStops = (TextView)view.findViewById(R.id.textView4);
            holder.noBus = (TextView)view.findViewById(R.id.txt_bus_noo);
            holder.stop = (TextView)view.findViewById(R.id.txt_stop);
            holder.endStop = (TextView)view.findViewById(R.id.txt_stop_end);
            holder.head = (RelativeLayout)view.findViewById(R.id.layout_haed);
            holder.middle = (RelativeLayout)view.findViewById(R.id.layout_middle);
            holder.bottom = (RelativeLayout)view.findViewById(R.id.layout_bottom);
            holder.imgBus2 = (ImageView)view.findViewById(R.id.imgBus2);

            holder.no2=(TextView)view.findViewById(R.id.textView3);

            view.setTag(holder);
        }else{
            holder = (ResultRouteBeanHolder)view.getTag();
        }
        ResultRouteBean ResultRouteBean = data[position];

        holder.stop1.setText(ResultRouteBean.stop1);

        holder.stop.setText(ResultRouteBean.stop);
        holder.endStop.setText(ResultRouteBean.endStop);
        holder.noStops.setText(ResultRouteBean.noStops);
        holder.noBus.setText(ResultRouteBean.noBus);
        if(ResultRouteBean.imgBus2!=0) {
            holder.imgBus2.setImageResource(ResultRouteBean.imgBus2);
        }
        //Log.e("BUSES",ResultRouteBean.no+" => "+position);
        if(ResultRouteBean.no<10){
            //   holder.no1.setText("0" + ResultRouteBean.no);
            holder.no2.setText("0" + ResultRouteBean.no);
            //  holder.no3.setText("0" + ResultRouteBean.no);

        }else{
            // holder.no1.setText("" + ResultRouteBean.no);
            holder.no2.setText("" + ResultRouteBean.no);
            // holder.no3.setText(""+ResultRouteBean.no);

        }

        if(ResultRouteBean.source==true){
            holder.middle.setVisibility(View.GONE);
            holder.bottom.setVisibility(View.GONE);
            holder.head.setVisibility(View.VISIBLE);

        }
        else {
            if (ResultRouteBean.destination == true) {
                holder.bottom.setVisibility(View.VISIBLE);
                holder.head.setVisibility(View.GONE);
                holder.middle.setVisibility(View.GONE);
            }
            else{
                holder.head.setVisibility(View.GONE);
                holder.middle.setVisibility(View.VISIBLE);
                holder.bottom.setVisibility(View.GONE);
            }
        }


        return view;
    }
    static class ResultRouteBeanHolder{
        TextView stop1,noStops,noBus,stop,endStop,no1,no2,stop11;
        // LinearLayout head;
        ImageView imgBus2;
        RelativeLayout middle,bottom,head;



    }
}
