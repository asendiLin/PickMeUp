package com.sendi.pickmeup.find;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.entity.Journey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/7.
 */

public class FindFragment extends BaseFragment implements IFindFragment {


    private RecyclerView mRecyclerView;
    private List<Journey> mJourneyList=new ArrayList<>();//行程列表

    @Override
    public void showOrderList(List<Journey> journeyList) {
        mJourneyList.clear();
        mJourneyList.addAll(journeyList);
    }

    @Override
    public void takeOrderSuccess() {

    }

    @Override
    public void takeOrderFail() {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        View view=View.inflate(mContext, R.layout.find_fragment,null);
        mRecyclerView=view.findViewById(R.id.find_recycler_view);
        return view;
    }
}
