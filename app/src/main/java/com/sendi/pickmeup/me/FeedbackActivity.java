package com.sendi.pickmeup.me;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sendi.pickmeup.R;
import com.sendi.pickmeup.base.BaseActivity;
import com.sendi.pickmeup.listener.ResultListener;
import com.sendi.pickmeup.login.LoginActivity;
import com.sendi.pickmeup.network.Netowrk;
import com.sendi.pickmeup.utils.ActivityController;

import java.util.HashMap;

public class FeedbackActivity extends BaseActivity {
    private ImageButton btn_back;
    private EditText edit_feedback;
    private Button btn_post;
    private String feedback;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        edit_feedback = findViewById(R.id.edit_feedback);
        btn_post = findViewById(R.id.btn_post);

        postFeedback();

        setButtonBack();

    }

    private void postFeedback() {
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback = edit_feedback.getText().toString();
                if (checkInput(feedback)) {

                    HashMap<String, String> map = new HashMap<>();
                    map.put("feedback", feedback);
                    Netowrk.executePost("", map, new ResultListener<String>() {

                        @Override
                        public void onSuccess(String data) {
                            Toast.makeText(FeedbackActivity.this, "反馈成功((^-^)", Toast.LENGTH_SHORT).show();
                            edit_feedback.setText("");
                        }

                        @Override
                        public void onFail(Throwable throwable) {
                            Toast.makeText(FeedbackActivity.this,"网络请求失败(>_<)",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeError(String msg) {
                            dialog = new AlertDialog.Builder(FeedbackActivity.this)
                                    .setTitle("反馈失败")
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
        });
    }

    private boolean checkInput(String feedback) {
        if(feedback == null || feedback.trim().equals("")){
            Toast.makeText(this,"内容不能为空！",Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }

    private void setButtonBack() {
        btn_back.setVisibility(View.VISIBLE);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                ActivityController.removeActivity(FeedbackActivity.this);
            }
        });
    }

}
