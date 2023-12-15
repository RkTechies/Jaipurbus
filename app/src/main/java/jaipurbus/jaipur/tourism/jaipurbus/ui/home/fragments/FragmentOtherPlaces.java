package jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.codersworld.jplibs.beans.PlacesBean;
import com.codersworld.jplibs.beans.PlacesTypeBean;
import com.codersworld.jplibs.database.DatabaseHelper;
import com.codersworld.jplibs.database.DatabaseManager;
import com.codersworld.jplibs.database.dao.PlaceCategoryDao;
import com.codersworld.jplibs.listeners.OnPageChangeListener;
import com.codersworld.jplibs.listeners.OnPlacesListener;
import com.codersworld.jplibs.listeners.OnResponse;
import com.codersworld.jplibs.listeners.QueryExecutor;
import com.codersworld.jplibs.rest.UniverSelObjct;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.FragmentPlacesBinding;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.FragmentRoutesBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.places.PlacesActivity;
import jaipurbus.jaipur.tourism.jaipurbus.ui.search.adapter.PlacesAdapter;
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper;

public class FragmentOtherPlaces extends Fragment implements OnResponse<UniverSelObjct>, OnPlacesListener {

    @NotNull
    public static final String TAG = FragmentOtherPlaces.class.getSimpleName();

    public FragmentOtherPlaces() {
        //if required
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentPlacesBinding binding;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        new ApiHelper(requireActivity()).setApplicationlanguage(requireActivity(), new UserSessions().getLanguage(requireActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_places, container, false);
        binding = DataBindingUtil.bind(view);
        binding.rvPlaces.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        DatabaseManager.initializeInstance(new DatabaseHelper(requireActivity()));
        initData(0);
        return view;
    }

    ArrayList<PlacesTypeBean> mListCategory = new ArrayList<>();

    //ApiHelper
    public void initData(int type) {
        DatabaseManager.getInstance().executeQuery(new QueryExecutor() {
            @Override
            public void run(SQLiteDatabase database) {
                PlaceCategoryDao dao = new PlaceCategoryDao(database, getActivity());
                mListCategory = dao.selectAll();
            }
        });
        if (!CommonMethods.isValidArrayList(mListCategory)) {
            if (type == 0) {
                new ApiHelper(requireActivity()).fetchCategory(true, this);
            }
        } else {
            binding.rvPlaces.setAdapter(new PlacesAdapter(requireActivity(), mListCategory, this));
        }
    }

    @NotNull
    public static Fragment newInstance() {
        return new FragmentOtherPlaces();
    }

    OnPageChangeListener mListener = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnPageChangeListener) context;
            if (mListener != null) {
                mListener.onPageChange("places");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(UniverSelObjct response) {
        initData(1);
    }

    @Override
    public void onError(String type, String error) {

    }

    @Override
    public void onPlace(PlacesBean mBeanPlace,int position) {

    }

    @Override
    public void onPlaceCategory(PlacesTypeBean mBeanPlace) {
        requireActivity().startActivity(new Intent(requireActivity(), PlacesActivity.class).putExtra("category_id",mBeanPlace.getId()));
    }
}