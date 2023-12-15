package jaipurbus.jaipur.tourism.jaipurbus.ui.home.result;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.codersworld.jplibs.beans.ResultRouteBean;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityBusRouteDetailBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.BaseActivity;


public class ResultRouteActivity extends BaseActivity {
    ActivityBusRouteDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ResultRouteActivity.this, R.layout.activity_bus_route_detail);
        setSupportActionBar(binding.toolbars);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int no = 1;

        int busType1, busType2;
        String bus1[], bus2[];
        bus1 = getIntent().getExtras().getStringArray("bus1");
        bus2 = getIntent().getExtras().getStringArray("bus2");
        busType1 = getIntent().getExtras().getInt("busType1");
        busType2 = getIntent().getExtras().getInt("busType2");
        int imgBus1, imgBus2;

        if (busType1 == 2) {
            imgBus1 = R.drawable.ic_minibus;
        } else if (busType1 == 1)
            imgBus1 = R.drawable.ic_lowfloor;
        else
            imgBus1 = R.drawable.ic_metro;

        binding.imageView7.setImageResource(imgBus1);

        if (busType2 == 2)
            imgBus2 = R.drawable.ic_minibus;
        else if (busType2 == 1)
            imgBus2 = R.drawable.ic_lowfloor;
        else
            imgBus2 = R.drawable.ic_metro;

        binding.sourceBusName.setText(bus1[1]);
        binding.sourceBusNo.setText(bus1[0]);
        binding.sourceBusStops.setText((bus1.length - 2) + "");
        // imageView.setImageResource(imgBus1);
        int a, b;
        a = bus1.length - 1;
        if (bus2 != null)
            b = bus2.length - 2;
        else
            b = 0;
        ResultRouteBean iteam_data[] = new ResultRouteBean[a + b - 1];
        //iteam_data[0]=new Iteam(null, null,null, bus1[1], null,false,false,no++,0);
        for (int i = 0; i < bus1.length - 3; i++) {
            iteam_data[i] = new ResultRouteBean(null, null, null, bus1[i + 2], null, false, false, no++, 0);
        }
        if (bus2 == null) {
            iteam_data[bus1.length - 3] = new ResultRouteBean(null, null, null, null, bus1[bus1.length - 1], false, true, no++, 0);
        } else {
            iteam_data[bus1.length - 3] = new ResultRouteBean(bus2[1], "" + (bus2.length - 2), " " + bus2[0] + " ", null, null, true, false, no++, imgBus2);
            for (int j = 0; j < bus2.length - 3; j++) {
                iteam_data[((bus1.length - 2) + j)] = new ResultRouteBean(null, null, null, bus2[j + 1 + 1], null, false, false, no++, 0);
            }
            iteam_data[bus1.length + bus2.length - 5] = new ResultRouteBean(null, null, null, null, bus2[bus2.length - 1], false, true, no++, 0);
        }

        ResultRouteAdapter adpater = new ResultRouteAdapter(this, R.layout.item_result_route, iteam_data);
        binding.listView1.setDivider(null);
        binding.listView1.setDividerHeight(0);
        binding.listView1.setAdapter(adpater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() != android.R.id.home) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }
}
