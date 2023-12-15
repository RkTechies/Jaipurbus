package jaipurbus.jaipur.tourism.jaipurbus.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.codersworld.jplibs.beans.StateBean;
import java.util.ArrayList;
import jaipurbus.jaipur.tourism.jaipurbus.R;

public class StateAdapter extends ArrayAdapter<StateBean> {
    ArrayList<StateBean> mListState;
    Context context;

    public StateAdapter(Context context2, ArrayList<StateBean> mListState2) {
        super(context2, 0, mListState2);
        this.context = context2;
        this.mListState = mListState2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_country, parent, false);
        }
        TextView textViewName = (TextView) convertView.findViewById(R.id.text1);
        StateBean currentItem = (StateBean) getItem(position);
        if (currentItem != null) {
            textViewName.setText(currentItem.getName());
        }
        return convertView;
    }
}
