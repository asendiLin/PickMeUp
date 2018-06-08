package com.sendi.pickmeup.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseActivity;
import com.sendi.pickmeup.listener.ResultListener;
import com.sendi.pickmeup.login.LoginActivity;
import com.sendi.pickmeup.network.Netowrk;
import com.sendi.pickmeup.utils.ActivityController;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText edit_register_username;
    private EditText edit_register_password;
    private EditText edit_register_phone;
    private Button btn_register;
    private String username;
    private String password;
    private String phone;
    private AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    private void initView() {
        edit_register_username = findViewById(R.id.edit_register_username);
        edit_register_password = findViewById(R.id.edit_register_password);
        edit_register_phone = findViewById(R.id.edit_register_phone);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                clickRegister();
                break;
            default:
                break;
        }
    }

    /**
     * 点击注册按钮
     */

    private void clickRegister() {
        username = edit_register_username.getText().toString();
        password = edit_register_password.getText().toString();
        phone = edit_register_phone.getText().toString();
        if(checkInput(username,password,phone)){
            HashMap<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("psw", password);
            map.put("phoneNumber", phone);
            Netowrk.executePost("http://192.168.1.109:8081/pickmeup/Login/userRegister?", map, new ResultListener<String>() {

                @Override
                public void onSuccess(String data) {
                    Toast.makeText(RegisterActivity.this,"注册成功((^-^)",Toast.LENGTH_SHORT).show();
                    finish();
                    ActivityController.removeActivity(RegisterActivity.this);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFail(Throwable throwable) {
                    Toast.makeText(RegisterActivity.this,"网络请求失败(>_<)",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCodeError(String msg) {
                    dialog = new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("注册失败")
                            .setMessage(msg)
                            .setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    dialog.show();
                }
            },String.class);
        }

    }

    /**
     * 检查输入
     * @param username
     * @param password
     * @return
     */
    private boolean checkInput(String username, String password,String phone) {
        if(username == null||username.trim().equals("")){
            Toast.makeText(this,"账号不能为空！",Toast.LENGTH_SHORT).show();
        }else{
            if(password==null||password.trim().equals("")){
                Toast.makeText(this,"密码不能为空！",Toast.LENGTH_SHORT).show();
            }else {
                if (phone == null || phone.trim().equals("")) {
                    Toast.makeText(this, "手机号码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
