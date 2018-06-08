package com.sendi.pickmeup.me.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;

/**
 * Created by lizheng on 2018/6/8.
 */

public class PassengerFragment extends BaseFragment implements View.OnClickListener{
    private TextView txt_vehicle_name;
    private TextView txt_vehicle_phone;
    private TextView txt_start_time;
    private TextView txt_start_place;
    private TextView txt_end_place;
    private TextView txt_price;
    private TextView txt_statue;
    private Button btn_cancel;
    private Button btn_finish;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = View.inflate(mContext, R.layout.passenger_fragment,null);
        txt_vehicle_name = view.findViewById(R.id.txt_vehicle_name);
        txt_vehicle_phone = view.findViewById(R.id.txt_vehicle_phone);
        txt_start_time = view.findViewById(R.id.txt_start_time);
        txt_start_place = view.findViewById(R.id.txt_start_place);
        txt_end_place = view.findViewById(R.id.txt_end_place);
        txt_price = view.findViewById(R.id.txt_price);
        txt_statue = view.findViewById(R.id.txt_statue);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_finish = view.findViewById(R.id.btn_finish);

        initData();
        return view;
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                clickCancel();
                break;
            case R.id.btn_finish:
                break;
                default:
                    break;
        }
    }

    private void clickCancel() {

    }
}
