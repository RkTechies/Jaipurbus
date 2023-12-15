package jaipurbus.jaipur.tourism.jaipurbus.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;

import androidx.databinding.DataBindingUtil;

import com.codersworld.jplibs.listeners.OnConfirmListener;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.CustomDialogBinding;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.LanguageDialogBinding;
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper;

public class LanguageDialog extends Dialog implements View.OnClickListener {
    public Context activity;
    LanguageDialogBinding binding;
    String strNo = "";
    String strText = "";
    Boolean isCancelable = true;
    OnConfirmListener mListener;

    public LanguageDialog(Context activity, String strText) {
        super(activity);
        this.activity = activity;
        this.strText = strText;
        isCancelable = true;
    }

    public LanguageDialog(Context activity, String strText, String strNo, OnConfirmListener mListener) {
        super(activity);
        this.activity = activity;
        this.strText = strText;
        this.strNo = strNo;
        this.mListener = mListener;
        isCancelable = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(getContext(), R.layout.language_dialog, null);
        setContentView(view);
        setCancelable(isCancelable);
        setCanceledOnTouchOutside(isCancelable);
        binding = DataBindingUtil.bind(view);
        binding.btnPositive.setOnClickListener(this);
        String lang = new UserSessions().getLanguage(activity);
        if (lang.equalsIgnoreCase("1")){
            binding.rbEnglish.setChecked(true);
        }else{
            binding.rbHindi.setChecked(true);
        }
        binding.rgLang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rbHindi){
                    binding.txtTitle.setText(activity.getResources().getString(R.string.msg_change_language_hi));
                }else{
                    binding.txtTitle.setText(activity.getResources().getString(R.string.msg_change_language_en));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPositive) {
            if (mListener != null) {
                String strLangId = (binding.rgLang.getCheckedRadioButtonId() == R.id.rbEnglish) ? "1" : "2";
                new UserSessions(activity).saveLanguage(activity, strLangId);
                new ApiHelper().setApplicationlanguage(activity, strLangId);
                mListener.onConfirm(true);
                dismiss();
            } else {
                dismiss();
            }
        }
    }
}