package com.fengniao.action.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fengniao.action.commonlibrary.config.BuildConfig;

/**
 * 数据持久化保存
 *
 * @author pujiang
 * @date 2017-9-18 18:04
 * @mail 515210530@qq.com
 * @Description:
 */
public class SharedPreUtils {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private static SharedPreUtils sharedPreUtils;

    public static synchronized SharedPreUtils getInstance(Context context) {
        if (sharedPreUtils == null) {
            sharedPreUtils = new SharedPreUtils(context);
        }
        return sharedPreUtils;
    }

    public SharedPreUtils(Context context) {
        sp = context.getSharedPreferences(BuildConfig.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * 保存登录账户
     **/
    public void saveUserName(String value) {
        editor.putString(SharedStr.USERNAME, value);
        editor.commit();
    }

    /**
     * 获取保存的登录账户
     **/
    public String getUserName() {
        return sp.getString(SharedStr.USERNAME, "");
    }

    /**
     * 保存用户id
     **/
    public void saveUserId(String value) {
        editor.putString(SharedStr.USERID, value);
        editor.commit();
    }

    /**
     * 获取用户id
     **/
    public String getUserId() {
        return sp.getString(SharedStr.USERID, "");
    }
}
