package com.hunter.fastandroid.utils;

import android.content.Context;
import android.content.Intent;

import com.hunter.fastandroid.ui.activity.ValidPhoneActivity;

import java.math.BigDecimal;

import cn.zhiao.baselib.custom.dialog.CommonDialog;
import cn.zhiao.baselib.utils.CommonUtil;
import cn.zhiao.baselib.utils.SharedPrefrecesUtils;

/**
 * Created by ymn on 2017/4/4.
 */
public class CommonUtils {

    /**
     *
     * 等额本息还款【利息多】
     * @param totalMoney 贷款总额
     * @param monRate  贷款商业利率
     * @param month  贷款年限
     */
    public static double getInterest(double totalMoney,double monRate,int month){

        double monInterest=totalMoney*monRate*Math.pow((1+monRate),month)/(Math.pow((1+monRate),month)-1);
        BigDecimal b   =   new   BigDecimal(monInterest);
        monInterest   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("月供本息和："+monInterest);
        return monInterest;

    }


    /**
     *判断是否登录
     * @return
     */
    public static boolean getLoginState(Context context) {
        //String token = SharedPreferences.getString(context, "token");
        String token = "token";
        if (CommonUtil.isExistValue(token)) {
            return true;
        }
        return false;
    }

    /**
     * 跳转到登录界面
     */
    public static void gotoLogin(final Context context) {
        CommonDialog dialog = new CommonDialog(context, "提示");
        dialog.show();
        dialog.setmessage("您还末登录，去登录？");
        dialog.setOkListener(new CommonDialog.OKListener() {
            @Override
            public void positiveAction() {//确定
//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.putExtra("isOtherFrom",true);
//                context.startActivity(intent);
            }
            @Override
            public void negativeAction() {

            }
        });
    }
    /**
     *判断是否绑定过手机号码
     * @return
     */
    public static boolean getBindingPhone(Context context) {
        if (CommonUtil.isExistValue(SharedPrefrecesUtils.getStrFromSharedPrefrences( "username",context))) {
            return true;
        }
        return false;
    }

    /**
     * 跳转到绑定手机号码界面
     */
    public static void gotoValidPhone(final Context context) {
        CommonDialog dialog = new CommonDialog(context, "提示");
        dialog.show();
        dialog.setmessage("您还末绑定手机号码，去绑定？");
        dialog.setOkListener(new CommonDialog.OKListener() {
            @Override
            public void positiveAction() {//确定
                Intent intent = new Intent(context, ValidPhoneActivity.class);
                intent.putExtra("vaildPhoneFlag", true);
                intent.putExtra("isOtherFrom", true);
                context.startActivity(intent);
            }

            @Override
            public void negativeAction() {

            }
        });
    }

}
