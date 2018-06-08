package com.sendi.pickmeup.find;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.entity.JourneyList;
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
    private List<JourneyList.Journey> mJourneyList = new ArrayList<>();//行程列表

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
    public void showOrderList(List<JourneyList.Journey> journeyList) {

        if (errorView != null)
            errorView.setVisibility(View.GONE);
        if (mRecyclerView.getVisibility() != View.VISIBLE)
            mRecyclerView.setVisibility(View.VISIBLE);

        mJourneyList.clear();
        mJourneyList.addAll(journeyList);
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
        mViewStub = view.findViewById(R.id.view_stub_error);
        mFindAdapter = new FindAdapter(mContext, mJourneyList);
        mRecyclerView.setAdapter(mFindAdapter);

        mFindAdapter.setOnItemClickListener(new FindAdapter.OnItemClickListener() {
            @Override
            public void onClick(JourneyList.Journey journey) {
                Network.executePost("url", null, new ResultListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        takeOrderSuccess();
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        takeOrderFail("操作出错");
                        Log.e(TAG, "onFail: ", throwable.getCause());
                    }

                    @Override
                    public void onCodeError(String msg) {
                        takeOrderFail(msg);
                    }
                },String.class);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Network.getResponseJsonData("", new ResultListener<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onFail(Throwable throwable) {

            }

            @Override
            public void onCodeError(String msg) {

            }
        });
    }
}
