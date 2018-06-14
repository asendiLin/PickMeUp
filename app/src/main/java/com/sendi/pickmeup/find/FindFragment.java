package com.sendi.pickmeup.find;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.entity.Journey;
import com.sendi.pickmeup.listener.ResultListener;
import com.sendi.pickmeup.network.Network;
import com.sendi.pickmeup.utils.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sendi on 2018/6/7.
 */

public class FindFragment extends BaseFragment implements IFindFragment,
        SwipeRefreshLayout.OnRefreshListener{


    private static final String TAG = "FindFragment";
    private RecyclerView mRecyclerView;
    private FindAdapter mFindAdapter;
    private List<Journey> mJourneyList = new ArrayList<>();//行程列表
    private SwipeRefreshLayout mSwipeRefreshLayout;
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
        mSwipeRefreshLayout=view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorThem);
        mSwipeRefreshLayout.setProgressViewOffset(true, 0, 10);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.find_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mViewStub = view.findViewById(R.id.view_stub_error);
        mFindAdapter = new FindAdapter(mContext, mJourneyList);
        mRecyclerView.setAdapter(mFindAdapter);

        mFindAdapter.setOnItemClickListener(new FindAdapter.OnItemClickListener() {
            @Override
            public void onClick(final Journey journey) {
                //toDo:jiedan
                final Dialog dialog = new Dialog(mContext);
                View takeDialogView = View.inflate(mContext, R.layout.take_order_dialog, null);
                dialog.setContentView(takeDialogView,
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Window window = dialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
                lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                dialog.onWindowAttributesChanged(lp);
                dialog.show();


                final EditText editName = takeDialogView.findViewById(R.id.edit_name);
                final EditText editPhone = takeDialogView.findViewById(R.id.edit_phone_number);
                final EditText editPay = takeDialogView.findViewById(R.id.edit_pay_number);
                takeDialogView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editName.getText().toString().trim();
                        String phoneNumber = editPhone.getText().toString().trim();
                        String payNumber = editPay.getText().toString().trim();
                        String j_id = journey.getJ_id();
                        Network.executeGet("http://192.168.1.110:8081/pickmeup/Discovery/TakeOrder?"+
                                "u_id="+UserManager.getInstance().getUserInfo(mContext).getUser_id()+
                                "&c_phone="+phoneNumber+"&c_name="+name+"&j_id="+j_id+
                                "&pay_number="+payNumber, new ResultListener<String>() {
                            @Override
                            public void onSuccess(String data) {
                                dialog.dismiss();
//                                Log.d(TAG, "onSuccess: "+data);
                            }

                            @Override
                            public void onFail(Throwable throwable) {

                            }

                            @Override
                            public void onCodeError(String msg) {

                            }
                        }, String.class);

                    }
                });

                takeDialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
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
                if (!data.equals("null")) {
                    Gson gson = new Gson();
                    List<Journey> journeyList = gson.fromJson(data, new TypeToken<List<Journey>>() {
                    }.getType());

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

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        Network.getResponseJsonData("http://192.168.1.110:8081/pickmeup/Discovery/findAll", new ResultListener<String>() {
            @Override
            public void onSuccess(String data) {
                if (!data.equals("null")) {
                    Gson gson = new Gson();
                    List<Journey> journeyList = gson.fromJson(data, new TypeToken<List<Journey>>() {
                    }.getType());
                    showOrderList(journeyList);
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFail(Throwable throwable) {
                mSwipeRefreshLayout.setRefreshing(false);
                loadOrderFail(throwable.getMessage());
            }

            @Override
            public void onCodeError(String msg) {
                mSwipeRefreshLayout.setRefreshing(false);
                loadOrderFail(msg);
            }
        });
    }
}
