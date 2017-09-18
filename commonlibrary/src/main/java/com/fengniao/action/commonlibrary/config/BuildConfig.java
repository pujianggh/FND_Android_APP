package com.fengniao.action.commonlibrary.config;

import com.fengniao.action.commonlibrary.utils.FileUtils;

/**
 * 生产环境配置config
 *
 * @author pujiang
 * @date 2017-9-3 19:57
 * @mail 515210530@qq.com
 * @Description:
 */
public final class BuildConfig {
    //主要用于日志打印，是否开启：true->开启日志 生产环境取消打印应该是：false
    public static final boolean DEBUG = true;
    //主要用于日志打印Tag标记
    public static final String TAG_DEBUG = "pj";
    //SharedPreferences保存名字
    public static final String SHARED_PREFERENCE_NAME = "sharepreferences_iData";
    //文件保存的确切位置
    public static final String CACHE_REAL_PATH = FileUtils.getCachePath() + "/fnd_cache";

}
