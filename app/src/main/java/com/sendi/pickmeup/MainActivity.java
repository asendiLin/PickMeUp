package com.sendi.pickmeup;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sendi.pickmeup.base.BaseActivity;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.find.FindFragment;
import com.sendi.pickmeup.me.fragment.MeFragment;
import com.sendi.pickmeup.publish.PublishFragment;

import java.util.ArrayList;

/**
 * Created by lizheng on 2018/6/2.
 */

public class MainActivity extends BaseActivity {
    private FrameLayout frameLayout;
    private RadioGroup rg_main;
    private TextView txt_title;
    private ArrayList<BaseFragment> fragments;
    private int position =0;
    private BaseFragment tempFragment; // 缓存的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        rg_main = findViewById(R.id.rg_main);
        frameLayout = findViewById(R.id.framelayout);
        txt_title = findViewById(R.id.txt_title);
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(FindFragment.create());
        fragments.add(PublishFragment.create());
        fragments.add(new MeFragment());

        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_find:
                        position = 0;
                        txt_title.setText(getString(R.string.str_find));
                        break;
                    case R.id.rb_pulish:
                        position = 1;
                        txt_title.setText(getString(R.string.str_publish));
                        break;
                    case R.id.rb_me:
                        position = 2;
                        txt_title.setText(getString(R.string.str_me));
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);

                switchFragment(tempFragment,baseFragment);
            }
        });
        rg_main.check(R.id.rb_find);

    }

    /**
     * 获取指定位置fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if(fragments!=null&&fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(BaseFragment fromFragment, BaseFragment nextFragment) {
        if(tempFragment!= nextFragment){

            tempFragment = nextFragment;
            if(nextFragment !=null){
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                if(!nextFragment.isAdded()){
                    if(fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.framelayout,nextFragment).commit();
                }else{
                    if(fromFragment !=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}
