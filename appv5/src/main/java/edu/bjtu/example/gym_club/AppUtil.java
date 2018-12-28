package edu.bjtu.example.gym_club;

import java.io.File;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;

/**
 * 常用工具
 *
 */
public class AppUtil {
    private static final String LOG_TAG = "AppUtil";

    private static String SDCARD = "";
    public static String getSDCARD(Context context) {
        if (sdCardExist()) {
            SDCARD = Environment.getExternalStorageDirectory() + "/haodun/";
        }else {
            SDCARD = context.getCacheDir().getAbsolutePath();
        }
        return SDCARD;
    }

    public static boolean sdCardExist(){
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        return sdCardExist;
    }
    /**
     * 记录日志
     *
     * @param log
     */
    public static void myLog(String log) {
        if (log == null) {
            return;
        }
        Log.v("haodun", log);
    }

    public static void myLoge(String log){
        if (log == null) {
            return;
        }
        Log.e("haodun", log);
    }

    /**
     * 生成给定位数的随机数字串
     *
     * @param number
     *            位数
     * @return
     */
    public static String randomNumberString(int number) {
        if (number < 1)
            return null;
        String sample = "1234567890";
        Random random = new Random();
        char str[] = sample.toCharArray();
        char ch[] = new char[number];
        for (int i = 0; i < number; i++)
            ch[i] = str[random.nextInt(sample.length())];
        return new String(ch);
    }

    /**
     * MD5摘要
     *
     * @param src
     * @return
     */
    public static String md5Digest(String src) {
        if (src == null) return "";
        String rst = "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(src.getBytes());
            byte[] dig = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < dig.length; i++) {
                int digByte = dig[i] & 0xFF;
                if (digByte <= 0x0F) sb.append("0");
                sb.append(Integer.toHexString(digByte));
            }
            rst = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            rst = src;
        }
        return rst;
    }

    public static double parseDouble(String v) {
        if (CheckUtil.isNull(v) || !CheckUtil.isNum(v)) {
            return -1;
        }
        return Double.parseDouble(v);
    }
    /**
     * string转date
     *
     * @param dateStr
     * @param partten 格式
     * @return
     */
    public static Date parseDateString(String dateStr, String partten) {
        SimpleDateFormat sdf = new SimpleDateFormat(partten, Locale.CHINA);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    /**
     * data 转 string
     *
     * @param date
     * @param partten 格式
     * @return
     */
    public static String parseStringDate(Date date, String partten) {
        SimpleDateFormat sdf = new SimpleDateFormat(partten, Locale.CHINA);
        return sdf.format(date);
    }


    public static JSONObject getJson(String key, Object value){
        return getJson(new String[]{key}, new Object[]{value});
    }

    public static JSONObject getJson(String[] keys, Object... values){
        JSONObject json = new JSONObject();
        try {
            for (int i = 0; i < keys.length; i++) {
                json.put(keys[i], values[i]);
            }
        } catch (Exception e) {
            AppUtil.myLoge(LOG_TAG + e.toString());
        }
        return json;
    }

    public static JSONObject editJson(JSONObject json, String key, Object value) {
        try {
            if (null != json)
                json.put(key, value);
        } catch (Exception e) {
        }
        return json;
    }

    public static JSONObject editJsons(JSONArray jsons, JSONObject json) {
        try {
            if (null != json) jsons.put(json);
        } catch (Exception e) {
        }
        return json;
    }

    public static JSONObject getJsonFromArray(JSONArray array, int length){
        JSONObject json = new JSONObject();
        try {
            json = array.getJSONObject(length);
        } catch (Exception e) {
            AppUtil.myLoge(LOG_TAG + e.toString());
        }
        return json;
    }

    public static int getJsonInt(JSONObject json, String keyName) {
        try {
            if (json==null) {
                return -1;
            }
            return json.getInt(keyName);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return -1;
        }
    }

    public static long getJsonLong(JSONObject json, String keyName) {
        try {
            if (json==null) {
                return -1;
            }
            return json.getLong(keyName);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return -1;
        }
    }

    public static String getJsonString(JSONObject json, String keyName) {
        try {
            if (json==null) {
                return "";
            }
            String s = json.getString(keyName);
            return (s == null || s.equals("") || s.equals("null")) ? "" : s;
        } catch (JSONException e) {
            myLog(e.getMessage());
            return "";
        }
    }

    public static boolean getJsonBool(JSONObject json, String keyName) {
        try {
            return json.getBoolean(keyName);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return false;
        }
    }

    public static double getJsonDouble(JSONObject json, String keyName) {
        try {
            return json.getDouble(keyName);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return -1;
        }
    }

    public static JSONObject getJsonObject(JSONObject json, String keyName) {
        try {
            return json.getJSONObject(keyName);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return new JSONObject();
        }
    }

    public static JSONObject getJsonObject(JSONArray array, int i) {
        try {
            return array.getJSONObject(i);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return new JSONObject();
        }
    }

    public static JSONObject fromString(String val) {
        if (val == null) return null;
        try {
            return new JSONObject(val);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return null;
        }
    }

    public static JSONArray arrayfromString(String val) {
        if (val == null) return null;
        try {
            return new JSONArray(val);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return new JSONArray();
        }
    }
    /**
     * String转json （取非空json）
     *
     * @param val
     * @return
     */
    public static JSONObject fromStringNotNull(String val) {
        if (val == null)
            return new JSONObject();
        try {
            return new JSONObject(val);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return new JSONObject();
        }
    }

    public static JSONArray getJsonArray(JSONObject json, String keyName) {
        try {
            return json.getJSONArray(keyName);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return new JSONArray();
        }
    }

    public static Object getObject(JSONObject json, String keyName) {
        try {
            return json.get(keyName);
        } catch (JSONException e) {
            myLog(e.getMessage());
            return new JSONObject();
        }
    }

    public static SparseArray jsonArrayToSpArray(JSONArray jarray){
        SparseArray ret = new SparseArray();
        if (jarray != null && jarray.length() > 0) {
            for (int i = 0; i < jarray.length(); i++) {
                ret.append(i, jarray.optJSONObject(i));
            }
        }
        return ret;
    }


    /***
     * 检查传入时间是否在今天之前
     *
     * @param myString
     * @param formatstr
     * @return
     */
    public static boolean checkDate(String myString, String formatstr) {
        java.util.Date nowdate = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formatstr, Locale.CHINA);
        String Ndate = sdf.format(nowdate);
        java.util.Date d = null;
        java.util.Date dd = null;
        try {
            d = sdf.parse(myString);
            dd = sdf.parse(Ndate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d.after(dd);
    }

    /**
     * 比较date1是否在date2之后
     *
     * @param date1
     * @param date2
     * @param formatstr
     * @return
     */
    public static boolean checkDate(String date1, String date2, String formatstr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatstr, Locale.CHINA);
        java.util.Date d1 = null;
        java.util.Date d2 = null;
        try {
            d1 = sdf.parse(date1);
            d2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d1.after(d2);
    }

    /**
     * 检测文件是否存在
     *
     * @param name
     * @return 存在返回 true, 否则返回false
     */
    public static boolean checkFile(String name, String path) {
        File file = new File(SDCARD + path);
        if (!file.exists()) {
            file.mkdirs();
            return false;
        }
        File listfFile[] = file.listFiles();
        if (listfFile == null) return false;
        for (File f : listfFile) {
            int index = f.getName().lastIndexOf(".");// 根据.分割文件
            if (index > 0) {
                if (f.getName().substring(0, index).trim().equals(name.trim())) {
                    return true;
                }
            }
            int j = f.getPath().lastIndexOf("/");// 根据/翻个文件
            if (f.getPath().substring(j).equals(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 删除目录下文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        File listfFile[] = file.listFiles();
        if (listfFile == null) return false;
        for (File f : listfFile) {
            File tFile = new File(filePath + "/" + f.getName());
            if (tFile.exists()) tFile.delete();
        }
        return true;
    }

    /**
     * 删除某个文件
     *
     * @param filePath
     * @return
     */
    public static void deleteF(String filePath) {
        File tFile = new File(filePath);
        if (tFile.exists()) tFile.delete();
    }



    /**
     * 日期转化字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String form) {
        SimpleDateFormat format = new SimpleDateFormat(form, Locale.CHINA);
        return format.format(date);
    }

    /**
     * 字符串转化日期
     *
     * @param str
     * @return
     */
    public static Date StringToDate(String str, String form) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(form, Locale.CHINA);
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /***
     * 时间格式转换
     *
     * @param str 时间字符串
     * @param fmt 原格式
     * @param tofmt 要转换的格式
     * @return
     */
    public static String dateStrFormat(String str, String fmt, String tofmt) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(fmt, Locale.CHINA);
        SimpleDateFormat toformat = new SimpleDateFormat(tofmt, Locale.CHINA);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toformat.format(date);
    }



    /**
     * 获取今天后n天日期
     *
     * @param n
     * @return
     */
    public static Date getUnDate(int n) {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH) + n;
        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, d);
        return calendar.getTime();
    }
    /**
     * 获取距离生日天
     * @param bday
     * @param format
     * return 0//今天生日，1七天内生日
     */
    public static int getBrithday(String bday,String format) {
        int i=-1;
        if (bday!=null && bday.length()>8) {
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTime(StringToDate(bday, format));
            int month = birthDay.get(Calendar.MONTH);
            int day=birthDay.get(Calendar.DAY_OF_MONTH);
            Calendar today = Calendar.getInstance();
            int curYear = today.get(Calendar.YEAR);
            int curMonth = today.get(Calendar.MONTH);
            int cruDay=today.get(Calendar.DAY_OF_MONTH);
            today.clear();
            today.set(curYear, curMonth, cruDay);
            Calendar thisBirthDay = Calendar.getInstance();
            thisBirthDay.clear();
            thisBirthDay.set(curYear, month, day);
            int yearDay=365;
            if (curYear%4==0) {
                yearDay=366;
            }
            long d= Math.abs((thisBirthDay.getTimeInMillis()-today.getTimeInMillis())/1000/3600/24);
            if (d==0) {//今天生日
                i=0;
            }else if ((d<=7 && d>0) || ((yearDay-d)<=7 && (yearDay-d)>0)) {//七天内生日
                i=1;
            }else if (d>100 && d<260) {//清除数据时
                i=2;
            }
        }
        return i;
    }
    /**
     * 计算两个时间间隔
     * 返回毫秒
     * @return
     */
    public static long doubleTimeSpace(Date starTime, String endTime,String format) {
        Date endDate = StringToDate(endTime, format);
        long td = starTime.getTime();
        long ed = endDate.getTime();
        long e = 0;
        if (ed > td) {
            e = ed - td;
        }
        return e;
    }

    /**
     * 		//将毫秒数换算成x天x时x分x秒x毫秒
     * @param ms
     * @return
     */
    public static String formatToDHms(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;
        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        //long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
        String strDay = day < 10 ? "0" + day : "" + day;
        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        //String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
        //strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        StringBuffer sBuffer=new StringBuffer();
        if (day>0) {
            sBuffer.append(strDay + "天 ");
        }
        if (hour>0) {
            sBuffer.append(strHour + ":");
        }
        sBuffer.append( strMinute + ":" );
        sBuffer.append( strSecond);
        return sBuffer.toString();//+ " " + strMilliSecond;
    }

    /**
     * 是否需要更新apk
     * @param activity
     * @param version 版本号
     * @return true:需要更新 false反之
     */
    public static boolean isInstallVersionApk(Activity activity, String version) {
        try {
            PackageInfo packInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            if (null != packInfo) {
                String versionName = packInfo.versionName;
                //versionName 给用户看的版本号
                //String versionName = String.valueOf(packInfo.versionName);
                if(!versionName.equals(version)){
                    return true;
                }

            }
        } catch (NameNotFoundException e){
            AppUtil.myLoge("" + e.toString());
        }
        return false;
    }

    /**
     * 加载class
     *
     * @param className
     * @return
     */
    public static Class<?> formName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  业务入口
     */
    public static Fragment getClass(String entranceAdd) {
        Fragment clazz = null;
        if (entranceAdd.indexOf("?") != -1) {
            entranceAdd = entranceAdd.substring(0, entranceAdd.indexOf("?"));
        }



        return clazz;
    }

    // 分解参数
    public static void splitParas(String entranceAddStr, JSONObject paras) {
        String[] array = entranceAddStr.split("=");
        if (array != null && array.length == 2) {
            try {
                paras.put(array[0], array[1]);
            } catch (JSONException e) {
                AppUtil.myLoge(LOG_TAG + e.toString());
            }
        }
    }

    //
    public static List splitString2Array(String sourceStr, String splitChar) {
        List list = new ArrayList();
        try {
            String[] array = sourceStr.split(splitChar);

            for(String s : array){
                if (!CheckUtil.isNull(s)) {
                    list.add(s);
                }
            }
        } catch (Exception e) {
            AppUtil.myLoge(LOG_TAG + e.toString());
        }
        return list;
    }

    // 分解url(参数格式Activity?classId=x&name=y)
    public static SplitResult splitUrl(String entranceAddStr) {
        SplitResult splitResult = null;
        if (entranceAddStr == null)
            return splitResult;
        if (entranceAddStr.indexOf("?") > 0) {
            splitResult = new SplitResult();
            JSONObject paras = new JSONObject();
            splitResult.setActivityName(entranceAddStr.substring(0, entranceAddStr.indexOf("?")));
            // 如果有多个参数
            entranceAddStr = entranceAddStr.substring(entranceAddStr .indexOf("?") + 1);
            if (entranceAddStr.indexOf("&") > 0) {
                String[] array = entranceAddStr.split("&");
                if (array != null && array.length > 0) {
                    for (String str : array) {
                        if (str.indexOf("=") > 0) splitParas(str, paras);
                    }
                }
            } else {
                if (entranceAddStr.indexOf("=") > 0) splitParas(entranceAddStr, paras);
            }
            splitResult.setParas(paras);
        }
        return splitResult;
    }

    public static class SplitResult {
        private String activityName;
        private JSONObject paras;
        public String getActivityName() {
            return activityName;
        }
        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }
        public JSONObject getParas() {
            return paras;
        }
        public void setParas(JSONObject paras) {
            this.paras = paras;
        }
    }

//    /**
//     * 检查service是否运行
//     * @param context
//     * @param classNname
//     * @return
//     */
//    public static boolean isServiceRunning(Context context,String classNname) {
//        List list =getRunService(context);
//        for (RunningServiceInfo service : list) {
//            //System.out.println(service.service.getClassName());
//            if (classNname.equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public static List getRunService(Context context){
//        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        return manager.getRunningServices(Integer.MAX_VALUE);
//    }

    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

}
