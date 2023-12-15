package jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.codersworld.jplibs.listeners.OnPageChangeListener;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.FragmentFindBusBinding;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.FragmentRoutesBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.search.DirectionsActrivity;
import jaipurbus.jaipur.tourism.jaipurbus.ui.search.SearchRoutesActivity;
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper;

public class FragmentRoutes extends Fragment {

    @NotNull
    public static final String TAG = FragmentRoutes.class.getSimpleName();

    public FragmentRoutes() {
        //if required
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentRoutesBinding binding;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        new ApiHelper(requireActivity()).setApplicationlanguage(requireActivity(), new UserSessions().getLanguage(requireActivity()));
    }

    public void changeImage(int mImage){
        binding.img.setImageResource(mImage);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_routes, container, false);
        binding = DataBindingUtil.bind(view);
        changeImage(R.drawable.img_jctsl_list);
        binding.tlRoutes.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    changeImage(R.drawable.img_jctsl_list);
                }else{
                    changeImage(R.drawable.img_jctsl_map);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
         return view;
    }

    @NotNull
    public static Fragment newInstance() {
        return new FragmentRoutes();
    }

    OnPageChangeListener mListener = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (OnPageChangeListener) context;
            if (mListener != null) {
                mListener.onPageChange("routes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}