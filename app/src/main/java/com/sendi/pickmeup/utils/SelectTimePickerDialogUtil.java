package com.sendi.pickmeup.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import com.sendi.pickmeup.R;

import java.util.Calendar;

/**
 * Created by sendi on 2018/6/6.
 */

public class SelectTimePickerDialogUtil implements NumberPicker.OnValueChangeListener,
        View.OnClickListener {

    private NumberPicker hourPicker;
    private NumberPicker minutePicker;
    private int mHour;
    private int mMinute;
    private String showSelectedTimeStr;
    private Context mContext;
    private Dialog mDialog;
    private OnSelectedTimeListener mListener;

    private SelectTimePickerDialogUtil(Builder builder) {
        this.mContext = builder.context;
        this.mListener = builder.mListener;
    }

    /**
     * 初始化时间选择器
     */
    private void initPicker() {
        Calendar calendar = Calendar.getInstance();

        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

//时
        hourPicker.setOnValueChangedListener(this);
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);
        hourPicker.setValue(hours-1);
        mHour = hours;

//分钟
        minutePicker.setOnValueChangedListener(this);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setValue(minute-1);
        mMinute=minute;
    }

    public void showSelectDialog() {
        View selectView = View.inflate(mContext, R.layout.time_selector_layout, null);
        selectView.findViewById(R.id.tv_confirm).setOnClickListener(this);
        selectView.findViewById(R.id.tv_cancel).setOnClickListener(this);
        hourPicker = selectView.findViewById(R.id.snp_hour);
        minutePicker = selectView.findViewById(R.id.snp_minute);
        //滑动时屏蔽键盘
        hourPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        minutePicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        initPicker();

        mDialog = new Dialog(mContext, R.style.dialog_bottom_filter);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(selectView,
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Window window = mDialog.getWindow();
//        window.setWindowAnimations(R.style);
        WindowManager.LayoutParams wl = window.getAttributes();

        wl.y = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        wl.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        wl.height = RelativeLayout.LayoutParams.WRAP_CONTENT;

        mDialog.onWindowAttributesChanged(wl);
        mDialog.setCanceledOnTouchOutside(true);

        mDialog.show();

        onDateChanged();
    }

    /**
     * 数据改变
     */
    private void onDateChanged() {
        showSelectedTimeStr = mHour + ":" + mMinute;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                if (mListener != null)
                    mListener.onSelectedTime(showSelectedTimeStr);
                mDialog.dismiss();
                break;
            case R.id.tv_cancel:
                mDialog.dismiss();
                break;
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId()) {
            case R.id.snp_hour:
                mHour = newVal;
                break;
            case R.id.snp_minute:
                mMinute = newVal;
                break;
        }
        onDateChanged();
    }

    public interface OnSelectedTimeListener {
        void onSelectedTime(String showSelectedTimeStr);
    }

    public static class Builder {
        private final Context context;
        private OnSelectedTimeListener mListener;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setOnSelectedTimeListener(OnSelectedTimeListener listener) {
            this.mListener = listener;
            return this;
        }

        public SelectTimePickerDialogUtil build() {
            return new SelectTimePickerDialogUtil(this);
        }
    }
}
