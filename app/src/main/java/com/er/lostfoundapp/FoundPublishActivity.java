package com.er.lostfoundapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.er.lostfoundapp.base.BaseActivity;
import com.er.lostfoundapp.entity.LostItem;
import com.er.lostfoundapp.utils.SPUtils;

import java.util.Date;
import java.util.UUID;

public class FoundPublishActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    // 原有控件
    private Spinner spFoundScene;
    private Spinner spFoundType;
    private EditText etFoundName;
    private EditText etFoundTime;
    private EditText etFoundFeature;
    private EditText etContact;
    private CheckBox cbShowContact;
    private Button btnPublish;

    // 免打扰相关控件
    private CheckBox cbDoNotDisturb;
    private LinearLayout llVerifyLayout;
    private EditText etVerifyQuestion;
    private EditText etOptionA;
    private EditText etOptionB;
    private EditText etOptionC;

    // 新增：单选按钮组（正确答案选择）
    private RadioGroup rgCorrectAnswer;
    private RadioButton rbAnswerA;
    private RadioButton rbAnswerB;
    private RadioButton rbAnswerC;



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        // 初始化原有控件
        spFoundScene = mContext.findViewById(R.id.sp_found_scene);
        spFoundType = mContext.findViewById(R.id.sp_found_type);
        etFoundName = mContext.findViewById(R.id.et_found_name);
        etFoundTime = mContext.findViewById(R.id.et_found_time);
        etFoundFeature = mContext.findViewById(R.id.et_found_feature);
        etContact = mContext.findViewById(R.id.et_contact);
        cbShowContact = mContext.findViewById(R.id.cb_show_contact);
        btnPublish = mContext.findViewById(R.id.btn_publish);

        // 初始化免打扰控件
        cbDoNotDisturb = mContext.findViewById(R.id.cb_do_not_disturb);
        llVerifyLayout = mContext.findViewById(R.id.ll_verify_layout);
        etVerifyQuestion = mContext.findViewById(R.id.et_verify_question);
        etOptionA = mContext.findViewById(R.id.et_option_a);
        etOptionB = mContext.findViewById(R.id.et_option_b);
        etOptionC = mContext.findViewById(R.id.et_option_c);

        // 初始化单选按钮组（正确答案）
        rgCorrectAnswer = mContext.findViewById(R.id.rg_correct_answer);
        rbAnswerA = mContext.findViewById(R.id.rb_answer_a);
        rbAnswerB = mContext.findViewById(R.id.rb_answer_b);
        rbAnswerC = mContext.findViewById(R.id.rb_answer_c);

        // 设置监听
        btnPublish.setOnClickListener(this);
        cbDoNotDisturb.setOnCheckedChangeListener(this);

    }

    // 免打扰CheckBox勾选监听
    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.cb_do_not_disturb) {
            if (isChecked) {
                llVerifyLayout.setVisibility(View.VISIBLE);
                cbShowContact.setChecked(false);
                cbShowContact.setEnabled(false);
                // 重置单选按钮选中状态（避免残留）
                rgCorrectAnswer.clearCheck();
            } else {
                llVerifyLayout.setVisibility(View.GONE);
                cbShowContact.setEnabled(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_publish) {
            if (validateForm()) {
                LostItem lostItem = assembleFoundItem();
                saveFoundItem(lostItem);
                showToast("拾获信息发布成功！开启免打扰后需答对验证问题才能获取联系方式");
                startActivity(new Intent(mContext, HomeActivity.class));
                finish();
            }
        }
    }

    // 表单验证：调整为检查单选按钮是否选中
    private boolean validateForm() {
        String foundName = etFoundName.getText().toString().trim();
        String foundContact = etContact.getText().toString().trim();
        long itemSceneId = spFoundScene.getSelectedItemId();
        long itemTypeId = spFoundType.getSelectedItemId();
        if(itemTypeId == -1 || itemSceneId == -1){
            showToast("请选择拾取信息");
            return false;
        }
        // 原有核心验证
        if (foundName.isEmpty()) {
            showToast("请输入物品名称");
            return false;
        }
        if (foundContact.isEmpty()) {
            showToast("请输入联系方式");
            return false;
        }

        // 免打扰开启时的验证
        if (cbDoNotDisturb.isChecked()) {
            String verifyQuestion = etVerifyQuestion.getText().toString().trim();
            String optionA = etOptionA.getText().toString().trim();
            String optionB = etOptionB.getText().toString().trim();
            String optionC = etOptionC.getText().toString().trim();

            if (verifyQuestion.isEmpty()) {
                showToast("开启免打扰后，验证问题不能为空");
                return false;
            }
            if (optionA.isEmpty() || optionB.isEmpty() || optionC.isEmpty()) {
                showToast("开启免打扰后，选项A/B/C不能为空");
                return false;
            }

            // 新增：检查是否选中了正确答案（单选按钮）
            int selectedId = rgCorrectAnswer.getCheckedRadioButtonId();
            if (selectedId == -1) {
                showToast("请选择正确答案（A/B/C）");
                return false;
            }
        }

        return true;
    }

    // 组装数据：获取选中的单选按钮文本作为正确答案
    private LostItem assembleFoundItem() {
        LostItem lostItem = new LostItem();
        // 原有字段赋值
        lostItem.setId(UUID.randomUUID().toString());
        lostItem.setUserId("anonymous_" + System.currentTimeMillis());
        lostItem.setLost(false);
        lostItem.setLostScene(spFoundScene.getSelectedItem().toString());
        lostItem.setLostType(spFoundType.getSelectedItem().toString());
        lostItem.setLostName(etFoundName.getText().toString().trim());
        // 新增：存储用户输入的时间字符串
        String userInputTime = etFoundTime.getText().toString().trim();
        lostItem.setLostTimeStr(userInputTime); // 存用户输入的时间
        lostItem.setLostTime(new Date()); // 保留Date字段

        lostItem.setLostFeature(etFoundFeature.getText().toString().trim());
        lostItem.setContact(etContact.getText().toString().trim());
        lostItem.setShowContact(cbShowContact.isChecked() && !cbDoNotDisturb.isChecked());

        lostItem.setImagePath(""); // 图片路径暂空

        // 验证字段赋值
        if (cbDoNotDisturb.isChecked()) {
            lostItem.setVerifyQuestion(etVerifyQuestion.getText().toString().trim());
            lostItem.setOptionA(etOptionA.getText().toString().trim());
            lostItem.setOptionB(etOptionB.getText().toString().trim());
            lostItem.setOptionC(etOptionC.getText().toString().trim());

            // 新增：获取选中的单选按钮文本（A/B/C）作为正确答案
            int selectedId = rgCorrectAnswer.getCheckedRadioButtonId();
            RadioButton selectedRb = mContext.findViewById(selectedId);
            lostItem.setCorrectAnswer(selectedRb.getText().toString()); // 直接获取选中的A/B/C
        } else {
            lostItem.setVerifyQuestion("");
            lostItem.setOptionA("");
            lostItem.setOptionB("");
            lostItem.setOptionC("");
            lostItem.setCorrectAnswer("");
        }

        lostItem.setImagePath("");
        return lostItem;
    }

    // 本地暂存
    private void saveFoundItem(LostItem lostItem) {
        SPUtils.addLostItem(mContext, lostItem);
    }

}
