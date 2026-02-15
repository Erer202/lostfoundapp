package com.er.lostfoundapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.er.lostfoundapp.base.BaseActivity;
import com.er.lostfoundapp.entity.LostItem;
import com.er.lostfoundapp.utils.SPUtils;

import java.util.Date;
import java.util.UUID;


public class LostPublishActivity extends BaseActivity implements View.OnClickListener {

    // 表单控件
    private Spinner spLostScene;
    private Spinner spLostType;
    private EditText etLostName;
    private EditText etLostTime;
    private EditText etLostFeature;
    private EditText etContact;
    private CheckBox cbShowContact;
    private Button btnPublish;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        // 绑定布局
        setContentView(R.layout.activity_publish_lost);

        // 初始化控件
        spLostScene = mContext.findViewById(R.id.sp_lost_scene);
        spLostType = mContext.findViewById(R.id.sp_lost_type);
        etLostName = mContext.findViewById(R.id.et_lost_name);
        etLostTime = mContext.findViewById(R.id.et_lost_time);
        etLostFeature = mContext.findViewById(R.id.et_lost_feature);
        etContact = mContext.findViewById(R.id.et_contact);
        cbShowContact = mContext.findViewById(R.id.cb_show_contact);
        btnPublish = mContext.findViewById(R.id.btn_publish);

        // 设置点击事件
        btnPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_publish) {
            // 表单验证
            if (validateForm()) {
                // 组装LostItem数据
                LostItem lostItem = assembleLostItem();
                // 本地暂存（后续可扩展上传到服务器）
                saveLostItem(lostItem);
                // 提示发布成功，返回Home页面
                showToast("丢失信息发布成功！");
                startActivity(new Intent(mContext, HomeActivity.class));
                finish();
            }
        }
    }

    // 表单验证：核心字段非空校验
    private boolean validateForm() {
        String lostName = etLostName.getText().toString().trim();
        String lostContact = etContact.getText().toString().trim();
        long itemSceneId = spLostScene.getSelectedItemId();
        long itemTypeId = spLostType.getSelectedItemId();
        if(itemTypeId == -1 || itemSceneId == -1){
            showToast("请选择丢失信息");
            return false;
        }
        if (lostName.isEmpty()) {
            showToast("请输入物品名称");
            return false;
        }
        if (lostContact.isEmpty()) {
            showToast("请输入联系方式");
            return false;
        }
        return true;
    }

    // 组装LostItem实体类数据
    private LostItem assembleLostItem() {
        LostItem lostItem = new LostItem();
        // 自动生成唯一ID
        lostItem.setId(UUID.randomUUID().toString());
        // 发布者ID：游客/匿名标识（后续登录后替换为真实ID）
        lostItem.setUserId("anonymous_" + System.currentTimeMillis());
        // 标记为丢失物品
        lostItem.setLost(true);
        // 场景/类型（取Spinner选中值）
        lostItem.setLostScene(spLostScene.getSelectedItem().toString());
        lostItem.setLostType(spLostType.getSelectedItem().toString());
        // 物品名称/时间/特征
        lostItem.setLostName(etLostName.getText().toString().trim());
        // 新增：存储用户输入的时间字符串（替代原来的new Date()）
        String userInputTime = etLostTime.getText().toString().trim();
        lostItem.setLostTimeStr(userInputTime); // 存用户输入的时间
        lostItem.setLostTime(new Date()); // 保留Date字段，后续可兼容

        lostItem.setLostFeature(etLostFeature.getText().toString().trim());
        // 联系方式
        lostItem.setContact(etContact.getText().toString().trim());
        lostItem.setShowContact(cbShowContact.isChecked());
        // 图片路径
        lostItem.setImagePath(""); // 图片路径暂空，后续扩展上传
        // 图片路径/验证问题：后续扩展
        lostItem.setImagePath("");
        lostItem.setVerifyQuestion("");
        lostItem.setOptionA("");
        lostItem.setOptionB("");
        lostItem.setOptionC("");
        lostItem.setCorrectAnswer("");

        return lostItem;
    }

    // 本地暂存发布信息（简化版：用SharedPreferences，后续可扩展数据库）
    private void saveLostItem(LostItem lostItem) {
        SPUtils.addLostItem(mContext, lostItem);
    }

}
