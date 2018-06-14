package com.sendi.pickmeup.me.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.entity.UserEntity;
import com.sendi.pickmeup.me.FeedbackActivity;
import com.sendi.pickmeup.me.JourneyActivity;
import com.sendi.pickmeup.utils.UserManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lizheng on 2018/6/7.
 */

public class MeFragment extends BaseFragment {
    private CircleImageView image_me;
    private TextView txt_name;
    private Button btn_journey;
    private Button btn_feedback;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = View.inflate(mContext, R.layout.me_fragment,null);
        image_me = view.findViewById(R.id.image_me);
        txt_name = view.findViewById(R.id.txt_name);
        btn_journey = view.findViewById(R.id.btn_journey);
        btn_feedback = view.findViewById(R.id.btn_feedback);
        btn_journey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentJourney = new Intent(mContext, JourneyActivity.class);
                startActivity(intentJourney);
            }
        });
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFeedback = new Intent(mContext, FeedbackActivity.class);
                startActivity(intentFeedback);
            }
        });
        initData();
        return view;
    }
    private void initData() {
        UserEntity userEntity = UserManager.getInstance().getUserInfo(mContext);
        txt_name.setText(userEntity.getUsername());
    }
}
