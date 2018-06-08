package com.sendi.pickmeup.me;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseActivity;
import com.sendi.pickmeup.me.adapter.MyPagerAdapter;
import com.sendi.pickmeup.me.fragment.PassengerFragment;
import com.sendi.pickmeup.me.fragment.VehicleOwnerFragment;
import com.sendi.pickmeup.utils.ActivityController;

import java.util.ArrayList;

public class JourneyActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);

        initView();
        initData();
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
        btn_back = findViewById(R.id.btn_back);

        setButtonBack();
        initViewPager();
    }

    private void initData() {

    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new VehicleOwnerFragment());
        fragments.add(new PassengerFragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(myPagerAdapter);
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(getString(R.string.str_vehicle_owne));
        mTabLayout.getTabAt(1).setText(getString(R.string.str_passenger));

    }

    private void setButtonBack() {
        btn_back.setVisibility(View.VISIBLE);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                ActivityController.removeActivity(JourneyActivity.this);
            }
        });
    }

}
