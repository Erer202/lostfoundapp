package com.er.lostfoundapp.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    // 上下文（所有子类都能直接用，不用重复定义）
    protected BaseActivity mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 把当前Activity赋值给mContext，后续弹吐司、找控件都能用
        mContext = this;
        // 初始化视图（子类必须自己实现，比如设置布局、找控件）
        initView();
        // 初始化数据（子类必须自己实现，比如加载列表、请求接口）
        initData();

    }
    protected abstract void initData();
    protected abstract void initView();

    // 弹窗提示文字（弹窗内容）
    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    //重载：用字符串资源ID弹吐司（比如R.string.login_success）
    protected void showToast(int resId) {
        showToast(getString(resId));
    }
}
