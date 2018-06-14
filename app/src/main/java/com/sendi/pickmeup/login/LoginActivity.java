package com.sendi.pickmeup.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sendi.pickmeup.MainActivity;
import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseActivity;
import com.sendi.pickmeup.entity.User;
import com.sendi.pickmeup.listener.ResultListener;
import com.sendi.pickmeup.network.Network;
import com.sendi.pickmeup.register.RegisterActivity;
import com.sendi.pickmeup.utils.ActivityController;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private EditText edit_login_username;
    private EditText edit_login_password;
    private Button btn_login;
    private TextView txt_register;
    private String username;
    private String password;
    private AlertDialog.Builder dialog;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edit_login_username = findViewById(R.id.edit_login_username);
        edit_login_password = findViewById(R.id.edit_login_password);
        btn_login = findViewById(R.id.btn_login);
        txt_register = findViewById(R.id.txt_register);
        btn_login.setOnClickListener(this);
        txt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                clickLogin();
                break;
            case R.id.txt_register:
                enterRegister();
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到注册页面
     */

    private void enterRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    /**
     * 点击登陆按钮
     */

    private void clickLogin() {
        username = edit_login_username.getText().toString();
        password = edit_login_password.getText().toString();
        if (checkInput(username, password)) {
            final HashMap<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("psw", password);
            Network.executePost("http://192.168.1.110:8081/pickmeup/Login/userLogin", map, new ResultListener<User>() {

                @Override
                public void onSuccess(User data) {
                    Toast.makeText(LoginActivity.this,"登陆成功(^-^)",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    ActivityController.removeActivity(LoginActivity.this);

                }

                @Override
                public void onFail(Throwable throwable) {
                    Toast.makeText(LoginActivity.this,"网络请求失败(>_<)",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCodeError(String msg) {
                    dialog = new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("登陆失败")
                            .setMessage(msg)
                            .setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    dialog.show();
                }
            },User.class);
        }
    }

    /**
     * 检查输入
     * @param username
     * @param password
     * @return
     */
    private boolean checkInput(String username, String password) {
        if(username == null||username.trim().equals("")){
            Toast.makeText(this,"账号不能为空！",Toast.LENGTH_SHORT).show();
        }else{
            if(password==null||password.trim().equals("")){
                Toast.makeText(this,"密码不能为空！",Toast.LENGTH_SHORT).show();
            }else{
                return true;
            }
        }
        return false;
    }
}
