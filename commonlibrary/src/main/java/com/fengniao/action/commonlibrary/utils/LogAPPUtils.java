package com.fengniao.action.commonlibrary.utils;

import android.util.Log;
import com.fengniao.action.commonlibrary.config.BuildConfig;

/**
 * 日志项目打印工具
 *
 * @author pujiang
 * @date 2017-9-3 19:55
 * @mail 515210530@qq.com
 * @Description:
 */
public class LogAPPUtils {
    public static boolean isDebug = BuildConfig.DEBUG;        // 调试状态
    public static String isTagDebug = BuildConfig.TAG_DEBUG;  // 调试Tag

    /**
     * 调试信息
     *
     * @param msg
     */
    public static void e(String msg) {
        if (isDebug) {
            Log.d(isTagDebug, "" + msg);
        }
    }

    /**
     * 调试信息
     *
     * @param msg
     */
    public static void d(String msg) {
        if (isDebug) {
            Log.d(isTagDebug, "" + msg);
        }
    }

    /**
     * 调试信息
     *
     * @param msg
     */
    public static void i(String msg) {
        if (isDebug) {
            Log.i(isTagDebug, "" + msg);
        }
    }

    /**
     * 调试信息
     *
     * @param msg
     */
    public static void i(int msg) {
        if (isDebug) {
            Log.i(isTagDebug, "" + msg);
        }
    }

    /**
     * 调试信息
     *
     * @param msg
     */
    public static void i(long msg) {
        if (isDebug) {
            Log.i(isTagDebug, "" + msg);
        }
    }

    /**
     * 调试信息
     *
     * @param msg
     */
    public static void i(boolean msg) {
        if (isDebug) {
            Log.i(isTagDebug, "" + msg);
        }
    }

}
