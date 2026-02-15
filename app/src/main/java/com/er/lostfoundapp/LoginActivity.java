package com.er.lostfoundapp;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.er.lostfoundapp.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    // 声明控件（删除原btnRegister，新增tvRegisterTip）
    private EditText etPhone;     // 手机号输入框
    private EditText etPassword;  // 密码输入框
    private Button btnLogin;      // 登录按钮
    private TextView tvRegisterTip; // 注册小字提醒

    // 初始化数据
    @Override
    protected void initData() {

    }

    // 初始化视图
    @Override
    protected void initView() {
        // 1. 绑定登录页面布局
        setContentView(R.layout.activity_login);

        // 2. 查找控件（更新控件列表）
        etPhone = mContext.findViewById(R.id.et_phone);
        etPassword = mContext.findViewById(R.id.et_password);
        btnLogin = mContext.findViewById(R.id.btn_login);
        tvRegisterTip = mContext.findViewById(R.id.tv_register_tip); // 新增小字提醒控件

        // 3. 设置点击事件（给登录按钮、注册小字加点击）
        btnLogin.setOnClickListener(this);
        tvRegisterTip.setOnClickListener(this); // 注册小字点击事件
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login) {
            // 登录
            login();
        } else if (id == R.id.tv_register_tip) {
            // 点击注册小字提醒：提示+跳转（后续替换为真实注册页面）
            showToast("即将进入账号注册页面");
            // 示例：跳转到RegisterActivity（后续创建注册页面时启用）
            // Intent intent = new Intent(mContext, RegisterActivity.class);
            // startActivity(intent);
        }
    }

    private void login() {
        // 获取输入内容（去除首尾空格）
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        //  基础验证
        if (phone.isEmpty()) {
            showToast("请输入手机号");
            return;
        }
        if (phone.length() != 11) {
            showToast("请输入正确的11位手机号");
            return;
        }
        if (password.isEmpty()) {
            showToast("请输入密码");
            return;
        }

        // 3. 验证通过：模拟登录成功
        showToast("登录成功！即将进入首页");
        finish();
    }

}
