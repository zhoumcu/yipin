package cn.zhiao.baselib.utils;

/**
 * author：Administrator on 2017/3/30 17:28
 * company: xxxx
 * email：1032324589@qq.com
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.DisplayMetrics;
import android.util.Log;

import cn.zhiao.baselib.custom.dialog.CommonDialog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtil {


    public static final String LOCATION_ADDRESS="";
    public static  final String PRO_SHI="shiming";
    public static  final String PRO_SHENGFENG="shengfeng";
    public static  final String PKINDIVCUST="pkIndivcust";
    public static  final String DARFEN_MOBILE="darfen_mobile";
    public static final String DARFEN_TAO="dar_tao";
    public static final String DARFEN_JING="dar_jing";


    public static String getCallurl(String l,String key){
        return  "http://restapi.amap.com/v3/geocode/regeo?key="+key+"&location="+l+"&poitype=&radius=&extensions=base&batch=false&roadlevel=1";
    }
    /**
     * check the email address is valid or not.
     *
     * @param email pass email id in string
     * @return true when its valid otherwise false
     */
    public static boolean isEmailIdValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    /**
     *          * 将px值转换为sp值，保证文字大小不变
     *          * 
     *          * @param pxValue
     *          * @param fontScale
     *          *            （DisplayMetrics类中属性scaledDensity）
     *          * @return
     *          
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * use for getting device height
     *
     * @param mContext
     * @return height of your device
     */
    public static int getDeviceHeight(Context mContext) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    /**
     * use for getting device width
     *
     * @param mContext
     * @return width of your device
     */
    public static int getDeviceWidth(Context mContext) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    /**
     * get the version of the application
     *
     * @param mContext
     * @return version code.
     */
    public static int getAppVersionCode(Context mContext) {
        PackageInfo pInfo = null;
        try {
            pInfo = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo.versionCode;
    }

    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(
                    "com.caimi.darlingwallet", 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**判断网络*/
    public static boolean isNetworkConnect(Context context) {
        boolean ret = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * 比较versionName true则需要更新
     *
     * @param localVersion
     * @param serverVersion
     * @return
     */
    public static boolean compareVersionName(String localVersion,
                                             String serverVersion) {
        if (serverVersion.equals("")) {
            return false;
        }

        String[] local = localVersion.split(Pattern.quote("."));
        String[] server = serverVersion.split(Pattern.quote("."));
        int localVersionFirst = Integer.parseInt(local[0]);
        int localVersionSecond = Integer.parseInt(local[1]);
        int localVersionThird = Integer.parseInt(local[2]);
        int serverVersionFirst = Integer.parseInt(server[0]);
        int serverVersionSecond = Integer.parseInt(server[1]);
        int serverVersionThird = Integer.parseInt(server[2]);
        if (serverVersionFirst > localVersionFirst
                || (serverVersionFirst == localVersionFirst && serverVersionSecond > localVersionSecond)
                || (serverVersionFirst == localVersionFirst
                && serverVersionSecond == localVersionSecond && serverVersionThird > localVersionThird)) {
            return true;
        }
        return false;

    }

    /*
     * 格式化收益格式
     *
     * @param value：收益
     *
     * @return
     */
    public static String formatWithDigital(String value) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(Double.parseDouble(value));
    }

    /**
     * 格式化收益格式
     *
     * @param value ：收益
     * @return
     */
    public static String formatWithString(String value) {
        if (value.equals("+0.00")) {
            return value;
        }
        if (value.equals("+0.0")) {
            return "+0.00";
        }
        if (value.equals("+0")) {
            return "+0.00";
        }
        String prefix = value.substring(0, 1);
        value = value.substring(1);
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return prefix + df.format(Double.parseDouble(value));
    }

    /**
     * 判断是否为空
     *
     * @param value
     * @return
     */
    public static boolean isExistValue(String value) {
        if (value != null && !"".equals(value) && !"null".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 获得str的md5值
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        String md5String = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            md5String = buf.toString();
            Log.e("debug","result32: " + buf.toString());// 32位的加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5String;
    }

    /**
     * dip to px
     *
     * @param value
     * @return
     */
    public static int dipTopx(int value) {
        float m = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(value * m);
    }


    /**
     * 参数 is_removable为false时得到的是内置sd卡路径，为true则为外置sd卡路径
     *
     * @param mContext
     * @param is_removale
     * @return
     */
    private static String getStoragePathFromRemovale(Context mContext, boolean is_removale) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            System.out.println("----length---" + length);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断手机是否有SD卡。
     *
     * @return 有SD卡返回true，没有返回false。
     */
    public static boolean hasSDCard() {

        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }


    public static String getTFCardPath() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String line;
            String mount = new String();
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line.contains("secure")) continue;
                if (line.contains("asec")) continue;

                if (line.contains("fat")) {
                    String columns[] = line.split(" ");
                    if (columns != null && columns.length > 1) {
                        mount = mount.concat("*" + columns[1] + "\n");
                    }
                } else if (line.contains("fuse")) {
                    String columns[] = line.split(" ");
                    if (columns != null && columns.length > 1) {
                        mount = mount.concat(columns[1] + "\n");
                    }
                }
            }
            return mount;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }


    public static String getIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        // if (!inetAddress.isLoopbackAddress() && inetAddress
                        // instanceof Inet6Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期成 ****年**月**日
     * @param datetime 要格式化的日期
     * @return
     */
    public static String DateFormat(String datetime) {
        String[] s = datetime.split("-");
        return  s[0]+"年"+s[1]+"月"+s[2]+"日";
    }

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

}