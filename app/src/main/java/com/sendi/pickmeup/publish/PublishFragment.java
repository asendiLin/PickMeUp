package com.sendi.pickmeup.publish;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.utils.SelectTimePickerDialogUtil;

/**
 * Created by sendi on 2018/6/7.
 */

public class PublishFragment extends BaseFragment implements IPublishFragment {


    private EditText editName;
    private EditText editNumber;
    private TextView txtStartTime;
    private EditText editStartPlace;
    private EditText editEndPlace;
    private EditText editPrice;
    private Button btnPublish;
    private static PublishFragment instance;

    @SuppressLint("ValidFragment")
    private PublishFragment() {
    }

    public static PublishFragment create() {
        if (instance == null)
            instance = new PublishFragment();
        return instance;
    }

    @Override
    public void publishSuccess(String msg) {

    }

    @Override
    public void publishFail(String msg) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = View.inflate(mContext, R.layout.publish_fragment, null);
        editName = view.findViewById(R.id.edit_name);
        editNumber = view.findViewById(R.id.edit_number);
        txtStartTime = view.findViewById(R.id.txt_start_time);
        editStartPlace = view.findViewById(R.id.edit_start_place);
        editEndPlace = view.findViewById(R.id.edit_end_place);
        editPrice = view.findViewById(R.id.edit_price);
        btnPublish = view.findViewById(R.id.btn_publish);

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo:post info
            }
        });

        txtStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toDo:select time
                SelectTimePickerDialogUtil pickerDialogUtil = new SelectTimePickerDialogUtil
                        .Builder(getActivity())
                        .setOnSelectedTimeListener(new SelectTimePickerDialogUtil.OnSelectedTimeListener() {
                            @Override
                            public void onSelectedTime(String showSelectedTimeStr) {
                                txtStartTime.setText(showSelectedTimeStr);
                            }
                        }).build();
                pickerDialogUtil.showSelectDialog();
            }
        });
        return view;
    }
}
