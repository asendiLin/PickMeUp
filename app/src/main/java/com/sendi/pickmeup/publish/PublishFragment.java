package com.sendi.pickmeup.publish;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseEntity;
import com.sendi.pickmeup.base.BaseFragment;
import com.sendi.pickmeup.config.Config;
import com.sendi.pickmeup.entity.UserEntity;
import com.sendi.pickmeup.listener.ResultListener;
import com.sendi.pickmeup.network.Network;
import com.sendi.pickmeup.utils.SelectTimePickerDialogUtil;
import com.sendi.pickmeup.utils.UserManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sendi on 2018/6/7.
 */

public class PublishFragment extends BaseFragment implements IPublishFragment {

    private static final int MESSAGE_ONSUCCESS = 0;

    private static final int MESSAGE_ONFAIL = 1;
    private static final int MESSAGE_ONCODEERROR = 2;
    private EditText editName;
    private EditText editNumber;
    private TextView txtStartTime;
    private EditText editStartPlace;
    private EditText editEndPlace;
    private EditText editPrice;
    private Button btnPublish;
    private static PublishFragment instance;



    @SuppressLint("ValidFragment")
    private PublishFragment() {
    }

    public static PublishFragment create() {
        if (instance == null)
            instance = new PublishFragment();
        return instance;
    }

    @Override
    public void publishSuccess(String msg) {

    }

    @Override
    public void publishFail(String msg) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = View.inflate(mContext, R.layout.publish_fragment, null);
        editName = view.findViewById(R.id.edit_name);
        editNumber = view.findViewById(R.id.edit_number);
        txtStartTime = view.findViewById(R.id.txt_start_time);
        editStartPlace = view.findViewById(R.id.edit_start_place);
        editEndPlace = view.findViewById(R.id.edit_end_place);
        editPrice = view.findViewById(R.id.edit_price);
        btnPublish = view.findViewById(R.id.btn_publish);

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editName.getText().toString().trim();
                String number=editNumber.getText().toString().trim();
                String startTime=txtStartTime.getText().toString().trim();
                String startPlace=editStartPlace.getText().toString().trim();
                String endPlace=editEndPlace.getText().toString().trim();
                String price=editPrice.getText().toString().trim();
                UserEntity userEntity = UserManager.getInstance().getUserInfo(mContext);
                String u_id = userEntity.getUser_id();
                Map<String,String> map=new HashMap<>();

                map.put("start_time",startTime);
                map.put("from_place",startPlace);
                map.put("to_place",endPlace);
                map.put("price",price);
                map.put("u_name",name);
                map.put("u_id",u_id);
                map.put("phone",number);


                Network.executeGet("http://192.168.1.110:8081/pickmeup/journey/addJourney?start_time="+startTime+"&from_place="+startPlace+"&to_place="+endPlace+"&price="+price+"&u_name="+name+"&u_id="+u_id+"&phone="+number,
                        new ResultListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(mContext,"发布成功",Toast.LENGTH_SHORT).show();
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
        });

        txtStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toDo:select time
                SelectTimePickerDialogUtil pickerDialogUtil = new SelectTimePickerDialogUtil
                        .Builder(getActivity())
                        .setOnSelectedTimeListener(new SelectTimePickerDialogUtil.OnSelectedTimeListener() {
                            @Override
                            public void onSelectedTime(String showSelectedTimeStr) {
                                txtStartTime.setText(showSelectedTimeStr);

                            }
                        }).build();
                pickerDialogUtil.showSelectDialog();
            }
        });
        return view;
    }
}
