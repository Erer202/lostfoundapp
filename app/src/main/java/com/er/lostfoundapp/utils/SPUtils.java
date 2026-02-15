package com.er.lostfoundapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.er.lostfoundapp.entity.LostItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SPUtils {

    // SP文件名（和之前一致）
    private static final String SP_NAME = "lost_found";
    // 存储列表的key
    private static final String KEY_LOST_ITEM_LIST = "lost_item_list";
    // Gson实例（序列化/反序列化）
    private static final Gson GSON = new Gson();
    // 列表类型（Gson反序列化需要）
    private static final Type LIST_TYPE = new TypeToken<ArrayList<LostItem>>() {}.getType();

    //获取SP实例
    private static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    // 新增一条发布记录到列表
    public static void addLostItem(Context context, LostItem lostItem) {
        // 1. 先读取已有的列表
        List<LostItem> list = getLostItemList(context);
        // 2. 添加新记录到列表头部（最新发布的在最前面）
        list.add(0, lostItem);
        // 3. 把新列表存回SP
        getSP(context).edit()
                .putString(KEY_LOST_ITEM_LIST, GSON.toJson(list))
                .apply();
    }

    // 读取所有发布记录列表
    public static List<LostItem> getLostItemList(Context context) {
        // 1. 从SP读取JSON字符串
        String json = getSP(context).getString(KEY_LOST_ITEM_LIST, "");
        // 2. 反序列化为列表（为空则返回空列表）
        List<LostItem> list = GSON.fromJson(json, LIST_TYPE);
        return list == null ? new ArrayList<>() : list;
    }

    // 清空所有发布记录
    public static void clearLostItemList(Context context) {
        getSP(context).edit()
                .remove(KEY_LOST_ITEM_LIST)
                .apply();
    }

}
