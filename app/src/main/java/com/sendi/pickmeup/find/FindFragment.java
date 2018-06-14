package com.sendi.pickmeup.find;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.entity.Journey;
import com.sendi.pickmeup.listener.ResultListener;
import com.sendi.pickmeup.network.Network;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sendi on 2018/6/7.
 */

public class FindFragment extends BaseFragment implements IFindFragment {


    private static final String TAG = "FindFragment";
    private RecyclerView mRecyclerView;
    private FindAdapter mFindAdapter;
    private List<Journey> mJourneyList = new ArrayList<>();//行程列表

    private static FindFragment instance;

    private ViewStub mViewStub;
    private View errorView;

    @SuppressLint("ValidFragment")
    private FindFragment() {
    }

    public static FindFragment create() {
        if (instance == null) {
            instance = new FindFragment();
        }
        return instance;
    }

    @Override
    public void showOrderList(List<Journey> journeyList) {

        if (errorView != null)
            errorView.setVisibility(View.GONE);
        if (mRecyclerView.getVisibility() != View.VISIBLE)
            mRecyclerView.setVisibility(View.VISIBLE);
        Log.d(TAG, "showOrderList: "+journeyList);
        mJourneyList.clear();
        mJourneyList.addAll(journeyList);
        Log.d(TAG, "showOrderList: "+mJourneyList);
        mFindAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadOrderFail(String msg) {
        if (errorView != null) {
            errorView.setVisibility(View.VISIBLE);
        } else {
            errorView = mViewStub.inflate();
            TextView errorTextView = errorView.findViewById(R.id.txt_error_msg);
            errorTextView.setText(msg);
            errorView.findViewById(R.id.btn_reload).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //toDo:重新加载
                }
            });
        }
        if (mRecyclerView != null)
            mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void takeOrderSuccess() {

    }

    @Override
    public void takeOrderFail(String msg) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        View view = View.inflate(mContext, R.layout.find_fragment, null);
        mRecyclerView = view.findViewById(R.id.find_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mViewStub = view.findViewById(R.id.view_stub_error);
        mFindAdapter = new FindAdapter(mContext, mJourneyList);
        mRecyclerView.setAdapter(mFindAdapter);

        mFindAdapter.setOnItemClickListener(new FindAdapter.OnItemClickListener() {
            @Override
            public void onClick(Journey journey) {
              //toDo:jiedan
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Network.getResponseJsonData("http://192.168.1.110:8081/pickmeup/Discovery/findAll", new ResultListener<String>() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "onSuccess: data==="+data);
                if (!data.equals("null")) {
                    Gson gson = new Gson();
                    List<Journey> journeyList = gson.fromJson(data, new TypeToken<List<Journey>>() {
                    }.getType());

                    Log.d(TAG, "onSuccess: "+journeyList);
                    showOrderList(journeyList);
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                loadOrderFail(throwable.getMessage());
            }

            @Override
            public void onCodeError(String msg) {
                loadOrderFail(msg);
            }
        });
    }
}
