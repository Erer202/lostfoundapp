package com.er.lostfoundapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.er.lostfoundapp.adapter.LostItemAdapter;
import com.er.lostfoundapp.base.BaseActivity;
import com.er.lostfoundapp.entity.LostItem;
import com.er.lostfoundapp.utils.SPUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    // 顶部控件
    private ImageView ivUserCenter;   // 左上角个人中心
    private TextView tvFilter;        // 右上角筛选

    // 中间区域
    private FrameLayout flDefaultContent; // 默认内容区
    private LinearLayout llPublishLayout; // 发布分栏区
    private Button btnILost;          // 我是丢失者
    private Button btnIFound;         // 我是拾取者

    // 底部导航
    private BottomNavigationView bottomNav;

    // 新增：列表控件
    private RecyclerView rvLostItemList;
    // 列表适配器
    private LostItemAdapter mAdapter;
    // 数据列表
    private List<LostItem> mLostItemList;

    @Override
    protected void initData() {
        // 初始化默认显示首页内容
        showDefaultContent();
    }

    @Override
    protected void initView() {
        //  绑定布局
        setContentView(R.layout.activity_home);

        // 初始化顶部控件
        ivUserCenter = mContext.findViewById(R.id.iv_user_center);
        tvFilter = mContext.findViewById(R.id.tv_filter);

        // 初始化中间区域
        llPublishLayout = mContext.findViewById(R.id.ll_publish_layout);
        btnILost = mContext.findViewById(R.id.btn_i_lost);
        btnIFound = mContext.findViewById(R.id.btn_i_found);

        // 初始化底部导航
        bottomNav = mContext.findViewById(R.id.bottom_nav);

        // 设置点击事件
        ivUserCenter.setOnClickListener(this);
        tvFilter.setOnClickListener(this);
        btnILost.setOnClickListener(this);
        btnIFound.setOnClickListener(this);

        // 初始化列表控件
        rvLostItemList = findViewById(R.id.rv_lost_item_list);
        // 设置线性布局管理器（垂直列表）
        rvLostItemList.setLayoutManager(new LinearLayoutManager(mContext));
        // 加载数据
        loadLostItemList();

        // 6. 底部导航选中监听
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_lost) {
                // 点击“丢”：显示失物列表（暂提示），隐藏发布分栏
                showDefaultContent();
                showToast("切换到失物列表");
                return true;
            } else if (itemId == R.id.nav_publish) {
                // 点击“发布”：显示发布分栏，隐藏默认内容
                showPublishLayout();
                return true;
            } else if (itemId == R.id.nav_find) {
                // 点击“捡”：显示拾物列表（暂提示），隐藏发布分栏
                showDefaultContent();
                showToast("切换到拾物列表");
                return true;
            }// 筛选按钮（后续实现）
            else if (itemId == R.id.tv_filter) {
                showToast("筛选功能开发中...");
            }
            return false;
        });
    }

    /**
     * 加载所有发布记录列表
     */
    private void loadLostItemList() {
        // 从SP读取列表
        mLostItemList = SPUtils.getLostItemList(mContext);
        // 创建适配器
        mAdapter = new LostItemAdapter(mContext, mLostItemList);
        // 设置适配器
        rvLostItemList.setAdapter(mAdapter);
    }



    private void showPublishLayout() {
        flDefaultContent.setVisibility(View.GONE);
        llPublishLayout.setVisibility(View.VISIBLE);
    }

    private void showDefaultContent() {
        flDefaultContent.setVisibility(View.VISIBLE);
        llPublishLayout.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_user_center) {
            // 左上角个人中心：跳转到个人中心页面（后续开发）
            showToast("即将进入个人中心");
        } else if (id == R.id.tv_filter) {
            // 右上角筛选：弹出筛选弹窗（场景/物品类型，后续开发）
            showToast("即将弹出筛选面板（丢失地点/物品类型）");
        } else if (id == R.id.btn_i_lost) {
            // 我是丢失者：跳转到丢失物品发布页面
            showToast("即将进入丢失物品发布页面");
            // 隐藏发布分栏，回到默认内容
            showDefaultContent();
        } else if (id == R.id.btn_i_found) {
            // 我是拾取者：跳转到拾获物品发布页面
            showToast("即将进入拾获物品发布页面");
            // 隐藏发布分栏，回到默认内容
            showDefaultContent();
        }else if (id == R.id.btn_i_lost) {
            // 跳转到丢失物品发布页面
            Intent intent = new Intent(mContext, LostPublishActivity.class);
            startActivity(intent);
            // 隐藏发布分栏，回到默认内容
            showDefaultContent();
        }
    }
}
