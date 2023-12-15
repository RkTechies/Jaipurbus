package jaipurbus.jaipur.tourism.jaipurbus.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.codersworld.jplibs.listeners.OnConfirmListener;
import com.codersworld.jplibs.utils.CommonMethods;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.CustomDialogBinding;

/*
import com.beautybebo.databinding.CustomDialogBinding;
import com.beautybebo.libs.interfaces.OnConfirmListener;
import com.beautybebo.utils.CommonMethods;
*/

public class CustomDialog extends Dialog implements View.OnClickListener {
    public Context activity;
    CustomDialogBinding binding;
    String strNo = "";
    String strText = "";
    Boolean isCancelable = true;
    OnConfirmListener mListener;

    public CustomDialog(Context activity, String strText) {
        super(activity);
        this.activity = activity;
        this.strText = strText;
        isCancelable = true;
    }

    public CustomDialog(Context activity, String strText, String strNo, OnConfirmListener mListener) {
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
        View view = View.inflate(getContext(), R.layout.custom_dialog, null);
        setContentView(view);
        setCancelable(isCancelable);
        setCanceledOnTouchOutside(isCancelable);
        binding = DataBindingUtil.bind(view);
        if (strText != null && !strText.isEmpty()) {
            CommonMethods.textWithHtml(binding.txtTitle, strText);
        } else {
            CommonMethods.textWithHtml(binding.txtTitle, activity.getResources().getString(R.string.app_name));
        }
        if (strNo != null && !strNo.isEmpty()) {
            binding.btnNegative.setText(strNo);
            binding.btnPositive.setText(activity.getResources().getString(R.string.lbl_yes));
            binding.btnNegative.setVisibility(View.VISIBLE);
        } else {
            binding.btnNegative.setVisibility(View.GONE);
        }
        binding.btnNegative.setOnClickListener(this);
        binding.btnPositive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPositive) {
            if (mListener != null) {
                mListener.onConfirm(true);
                dismiss();
            } else {
                dismiss();
            }
        } else if (v.getId() == R.id.btnNegative) {
            if (mListener != null) {
                mListener.onConfirm(false);
                dismiss();
            } else {
                dismiss();
            }
        }
    }
}