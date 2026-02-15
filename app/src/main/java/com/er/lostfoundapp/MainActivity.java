package com.er.lostfoundapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.er.lostfoundapp.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnLogin;         // 账号密码登录
    private Button btnVisitor;       // 游客登录
    private Button btnAnonymousPublish;  // 匿名发布

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        // 1. 设置当前页面的布局（绑定activity_main.xml）
        setContentView(R.layout.activity_main);

        // 2. 查找布局中的控件（用mContext确保上下文正确）
        btnLogin = mContext.findViewById(R.id.btn_login);
        btnVisitor = mContext.findViewById(R.id.btn_visitor);
        btnAnonymousPublish = mContext.findViewById(R.id.btn_anonymous_publish);

        // 3. 设置按钮点击事件
        setClickListener();
        
    }

    private void setClickListener() {
        btnLogin.setOnClickListener(this);
        btnVisitor.setOnClickListener(this);
        btnAnonymousPublish.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login) {
            showToast(R.string.login_btn_tip);
        } else if (id == R.id.btn_visitor) {
            showToast(R.string.visitor_login_success);

        } else if (id == R.id.btn_anonymous_publish) {
            showToast(R.string.anonymous_publish_tip);
        }
    }
}