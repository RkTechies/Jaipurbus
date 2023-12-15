package jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.codersworld.jplibs.listeners.OnConfirmListener;
import com.codersworld.jplibs.listeners.OnPageChangeListener;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.FragmentRoutesBinding;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.FragmentSettingsBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.AboutUsActivity;
import jaipurbus.jaipur.tourism.jaipurbus.ui.SupportActivity;
import jaipurbus.jaipur.tourism.jaipurbus.ui.WebViewActivity;
import jaipurbus.jaipur.tourism.jaipurbus.ui.dialogs.LanguageDialog;
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.MainActivity;
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper;
import jaipurbus.jaipur.tourism.jaipurbus.utils.Global;

public class FragmentSettings extends Fragment implements View.OnClickListener , OnConfirmListener {

    @NotNull
    public static final String TAG = FragmentSettings.class.getSimpleName();

    public FragmentSettings() {
        //if required
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSettingsBinding binding;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        new ApiHelper(requireActivity()).setApplicationlanguage(requireActivity(), new UserSessions().getLanguage(requireActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        binding = DataBindingUtil.bind(view);
        binding.rlAbout.setOnClickListener(this);
        binding.rlContactUs.setOnClickListener(this);
        binding.rlPrivacy.setOnClickListener(this);
        binding.rlShare.setOnClickListener(this);
        binding.rlLanguage.setOnClickListener(this);
        String lang = new UserSessions().getLanguage(requireActivity());
        binding.txtLang.setText((lang.equalsIgnoreCase("1"))?getString(R.string.lbl_english):getString(R.string.lbl_hindi));
        binding.txtVersion.setText(getString(R.string.lbl_version,new Global().getVersionName(requireActivity())));

        return view;
    }

    @NotNull
    public static Fragment newInstance() {
        return new FragmentSettings();
    }

    OnPageChangeListener mListener = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnPageChangeListener) context;
            if (mListener != null) {
                mListener.onPageChange("settings");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == binding.rlLanguage) {
            LanguageDialog customDialog = new LanguageDialog(requireActivity(),getString(R.string.msg_exit_confirm),getString(R.string.lbl_no),this);
            customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            customDialog.show();

            //CommonMethods.moveToNext(requireActivity(), AboutUsActivity.class);
        } else   if (v == binding.rlAbout) {
            CommonMethods.moveToNext(requireActivity(), AboutUsActivity.class);
        } else if (v == binding.rlContactUs) {
            CommonMethods.moveToNext(requireActivity(), SupportActivity.class);
        } else if (v == binding.rlPrivacy) {
            startActivity(new Intent(requireActivity(), WebViewActivity.class).putExtra("type", 1));
        } else if (v == binding.rlShare) {
            try {
                Uri uri = null;
                try {
                    uri = Uri.parse(MediaStore.Images.Media.insertImage(requireActivity().getContentResolver(), BitmapFactory.decodeResource(getResources(), R.drawable.app_icon), (String) null, (String) null));
                } catch (Exception unused) {
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                if (uri != null) {
                    intent.putExtra("android.intent.extra.STREAM", uri);
                }
                intent.setAction("android.intent.action.SEND");
                //"Still Asking For Bus Routes and places in Jaipur City? Now No More. Download 'JaipurBus & Tourism' https://play.google.com/store/apps/details?id=" +
                intent.putExtra("android.intent.extra.TEXT", getString(R.string.msg_share,requireActivity().getPackageName()));
                startActivity(Intent.createChooser(intent, getString(R.string.lbl_share_via)));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onConfirm(Boolean isTrue) {
        startActivity(new Intent(requireActivity(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        requireActivity().finishAffinity();
    }
}