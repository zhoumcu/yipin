package cn.zhiao.baselib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPrefrece工具类
 * Created by ZengXiaoPing on 2015/8/24.
 */
public class SharedPrefrecesUtils {

    public static final String SPFILENAME = "cheyupinShopping";

    //保存String类型
    public static void saveStrToSharedPrefrences(String key, String value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPFILENAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //获取String类型
    public static String getStrFromSharedPrefrences(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPFILENAME, Context.MODE_APPEND);
        return sharedPreferences.getString(key, null);
    }

    //保存Boolean类型
    public static void saveBooleanToSharedPrefrences(String key, boolean value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPFILENAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    //获取Boolean类型
    public static Boolean getBooleanFromSharedPrefrences(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPFILENAME, Context.MODE_APPEND);
        return sharedPreferences.getBoolean(key, false);
    }

    //保存int类型
    public static void saveIntToSharedPrefrences(String key, int value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPFILENAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //获取int类型
    public static int getIntFromSharedPrefrences(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPFILENAME, Context.MODE_APPEND);
        return sharedPreferences.getInt(key, -1);
    }

    //清理保存数据
    public static void clearStrToSharedPrefrences(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPFILENAME, Context.MODE_APPEND);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.commit();
    }

//    //清理Boolean类型
//    public static void clearBooleanToSharedPrefrences(String key, Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_APPEND);
//        SharedPreferences.Editor edit = sharedPreferences.edit();
//        edit.clear();
//    }
//
//    //清理Int类型
//    public static void clearIntToSharedPrefrences(String key, Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_APPEND);
//        SharedPreferences.Editor edit = sharedPreferences.edit();
//        edit.clear();
//    }

}
