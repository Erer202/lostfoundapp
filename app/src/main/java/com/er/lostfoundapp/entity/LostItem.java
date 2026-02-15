package com.er.lostfoundapp.entity;

import java.util.Date;

public class LostItem {

    // 核心字段
    private String id;                 // 信息唯一ID（自动生成，用于区分不同信息）
    private String userId;             // 发布者ID（账号/匿名标识）
    private boolean isLost;            // true=丢失物品，false=拾获物品
    private String lostScene;              // 场景（图书馆X楼/食堂X层等，预设选项）
    private String lostType;               // 物品类型（校园卡/充电宝等，预设选项）
    private String lostName;               // 物品名称
    private Date lostTime;                 // 丢失/拾获时间
    private String lostFeature;            // 核心特征
    private String contact;            // 联系方式（微信/QQ，可选展示）
    private boolean showContact;       // 是否展示联系方式
    private String imagePath;          // 图片路径（本地/云端，仅基础压缩）

    // 认领验证相关
    private String verifyQuestion;     // 验证问题（发布方设置）
    private String optionA;            // 选项A
    private String optionB;            // 选项B
    private String optionC;            // 选项C
    private String correctAnswer;      // 正确答案（A/B/C）


    // 新增：用户输入的时间字符串（比如“2026-02-14 14:30”）
    private String lostTimeStr;

    // 新增Getter
    public String getLostTimeStr() {
        return lostTimeStr;
    }

    // 新增Setter
    public void setLostTimeStr(String lostTimeStr) {
        this.lostTimeStr = lostTimeStr;
    }

    // 空构造
    public LostItem() {}

    // Getter & Setter（删除campus相关所有方法）
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    public String getLostScene() {
        return lostScene;
    }

    public void setLostScene(String lostScene) {
        this.lostScene = lostScene;
    }

    public String getLostType() {
        return lostType;
    }

    public void setLostType(String lostType) {
        this.lostType = lostType;
    }

    public String getLostName() {
        return lostName;
    }

    public void setLostName(String lostName) {
        this.lostName = lostName;
    }

    public Date getLostTime() {
        return lostTime;
    }

    public void setLostTime(Date lostTime) {
        this.lostTime = lostTime;
    }

    public String getLostFeature() {
        return lostFeature;
    }

    public void setLostFeature(String lostFeature) {
        this.lostFeature = lostFeature;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isShowContact() {
        return showContact;
    }

    public void setShowContact(boolean showContact) {
        this.showContact = showContact;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVerifyQuestion() {
        return verifyQuestion;
    }

    public void setVerifyQuestion(String verifyQuestion) {
        this.verifyQuestion = verifyQuestion;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
