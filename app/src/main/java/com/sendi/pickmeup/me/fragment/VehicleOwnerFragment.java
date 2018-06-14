package com.sendi.pickmeup.me.fragment;

import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.entity.Passenger;
import com.sendi.pickmeup.entity.UserEntity;
import com.sendi.pickmeup.entity.VehicleOwn;
import com.sendi.pickmeup.listener.ResultListener;
import com.sendi.pickmeup.me.adapter.MyPagerAdapter;
import com.sendi.pickmeup.me.adapter.VehicleOwnAdapter;
import com.sendi.pickmeup.network.Network;
import com.sendi.pickmeup.utils.UserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by lizheng on 2018/6/8.
 */

public class VehicleOwnerFragment extends BaseFragment {
    private RecyclerView rv_vehicle_own;
    private List<Passenger> vehicleOwns;
    private VehicleOwnAdapter mAdapter;
    private TextView txt_error;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = View.inflate(mContext, R.layout.vehicle_own_fragment, null);
        rv_vehicle_own = view.findViewById(R.id.rv_vehicle_own);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rv_vehicle_own.setLayoutManager(layoutManager);
        txt_error = view.findViewById(R.id.txt_error);
        initData();
        return view;
    }

    private void initData() {
        vehicleOwns = new ArrayList<>();
        mAdapter = new VehicleOwnAdapter(mContext, vehicleOwns);
        rv_vehicle_own.setAdapter(mAdapter);


        UserEntity userEntity = UserManager.getInstance().getUserInfo(mContext);
        String u_id = userEntity.getUser_id();
        final HashMap<String, String> map = new HashMap<>();
        map.put("u_id", u_id);
        Network.getResponseJsonData("http://192.168.1.110:8081/pickmeup/user/driverOrder?u_id="+u_id,new ResultListener<String>() {
            @Override
            public void onSuccess(String data) {
                if(data.equals("null")) {
                    rv_vehicle_own.setVisibility(View.GONE);
                    txt_error.setVisibility(View.VISIBLE);
                }else{
                    Gson gson = new Gson();
                    List<Passenger> passengerList = gson.fromJson(data, new TypeToken<List<Passenger>>() {
                    }.getType());
                    vehicleOwns.addAll(passengerList);
                    mAdapter.notifyDataSetChanged();
                    rv_vehicle_own.setVisibility(View.VISIBLE);
                    txt_error.setVisibility(View.GONE);
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
        });

    }
}
