package com.sendi.pickmeup.me.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.entity.Passenger;
import com.sendi.pickmeup.entity.UserEntity;
import com.sendi.pickmeup.listener.ResultListener;
import com.sendi.pickmeup.network.Network;
import com.sendi.pickmeup.utils.UserManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
    private String j_id;
    private LinearLayout ll_passenger;
    private TextView txt_error;
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
        ll_passenger = view.findViewById(R.id.ll_passenger);
        txt_error = view.findViewById(R.id.txt_error);
        ll_passenger.setVisibility(View.GONE);
        txt_error.setVisibility(View.GONE);
        btn_cancel.setOnClickListener(this);
        btn_finish.setOnClickListener(this);
        initData();
        return view;
    }

    private void initData() {
        UserEntity userEntity = UserManager.getInstance().getUserInfo(mContext);
        String u_id = userEntity.getUser_id();
        final HashMap<String, String> map = new HashMap<>();
        map.put("u_id",u_id);
        Network.executePost("http://192.168.1.110:8081/pickmeup/user/userOrder", map, new ResultListener<Passenger>() {
            @Override
            public void onSuccess(Passenger data) {
                j_id = data.getJ_id();
                if(j_id == null){
                    txt_error.setVisibility(View.VISIBLE);
                    ll_passenger.setVisibility(View.GONE);
                }else{
                    ll_passenger.setVisibility(View.VISIBLE);
                    txt_error.setVisibility(View.GONE);
                    txt_vehicle_name.setText(data.getU_name());
                    txt_vehicle_phone.setText(data.getU_phone());
                    txt_start_time.setText(strToDate(data.getStart_time()));
                    txt_start_place.setText(data.getFrom_place());
                    txt_end_place.setText(data.getTo_place());
                    txt_price.setText(data.getPrice());
                    txt_statue.setText(data.getStatus());
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                Toast.makeText(mContext,"网络请求失败(>_<)",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeError(String msg) {
                Toast.makeText(mContext,""+msg,Toast.LENGTH_SHORT).show();
            }
        },Passenger.class);

    }

//    private String getNowDate(Date date) {
//         SimpleDateFormat formatter = new SimpleDateFormat("yyyy HH:mm:ss");
//        String dateString = formatter.format(date);
//        return dateString;
//    }
//    public static String dateToStrLong(Date dateDate) {
//           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//           String dateString = formatter.format(dateDate);
//           return dateString;
//          }
public static String strToDate(Long ldate){
    Date date = new Date(ldate);
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    String strDate = formatter.format(date);
    return strDate;
}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                clickCancel();
                break;
            case R.id.btn_finish:
                clickFinish();
                break;
                default:
                    break;
        }
    }
    private void clickFinish() {
//        final HashMap<String, String> map = new HashMap<>();
//        map.put("j_id",j_id);
        Network.executeGet("http://192.168.1.110:8081/pickmeup/user/finishOrder?j_id="+j_id, new ResultListener<String>() {

            @Override
            public void onSuccess(String data) {
                Toast.makeText(mContext,"完成订单",Toast.LENGTH_SHORT).show();
                ll_passenger.setVisibility(View.GONE);
                txt_error.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(Throwable throwable) {

            }

            @Override
            public void onCodeError(String msg) {
//                Toast.makeText(mContext,""+msg,Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext,"不可以完成订单",Toast.LENGTH_SHORT).show();
            }
        },String.class);
    }


    private void clickCancel() {
        final HashMap<String, String> map = new HashMap<>();
        map.put("j_id",j_id);
        Network.executePost("http://192.168.1.110:8081/pickmeup/user/userDeleteOrder", map, new ResultListener<String>() {

            @Override
            public void onSuccess(String data) {
                Toast.makeText(mContext,"取消订单成功",Toast.LENGTH_SHORT).show();
                ll_passenger.setVisibility(View.GONE);
                txt_error.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(Throwable throwable) {

            }

            @Override
            public void onCodeError(String msg) {
                Toast.makeText(mContext,""+msg,Toast.LENGTH_SHORT).show();
            }
        },String.class);
    }
}
