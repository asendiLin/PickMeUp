package com.sendi.pickmeup.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.entity.VehicleOwn;
import com.sendi.pickmeup.me.adapter.MyPagerAdapter;
import com.sendi.pickmeup.me.adapter.VehicleOwnAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizheng on 2018/6/8.
 */

public class VehicleOwnerFragment extends BaseFragment {
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView rv_vehicle_own;
    private List<VehicleOwn> vehicleOwns;
    private VehicleOwnAdapter mAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = View.inflate(mContext, R.layout.vehicle_own_fragment,null);
        rv_vehicle_own = view.findViewById(R.id.rv_vehicle_own);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshVehicleOwn();
            }
        });
        initData();
        return view;
    }

    private void initData() {
        vehicleOwns = new ArrayList<>();
        mAdapter = new VehicleOwnAdapter(mContext,vehicleOwns);
        rv_vehicle_own.setAdapter(mAdapter);
    }

    private void refreshVehicleOwn() {
        mAdapter.notifyDataSetChanged();
    }
}
