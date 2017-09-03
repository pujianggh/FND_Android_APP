package com.fengniao.action.app;

import android.support.multidex.MultiDexApplication;
import com.fengniao.action.net.DownloadManager;
import com.fengniao.action.okhttputils.OkHttpCall;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 应用初始配置
 *
 * @author pujiang
 * @date 2017-9-3 17:00
 * @mail 515210530@qq.com
 * @Description:
 */
public class FNDApplication extends MultiDexApplication{
    private static FNDApplication mInstance;
    private OkHttpCall mOkHttpCall;
    private DownloadManager mDownloadMgr;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        //持久化存储cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        //log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //自定义OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)               //设置开启cookie
                .addInterceptor(logging)            //设置开启log
                .build();
        mOkHttpCall = new OkHttpCall(okHttpClient); //默认mOkHttpCall = new OkHttpCall();
        mDownloadMgr = (DownloadManager) new DownloadManager.Builder()
                .myOkHttp(mOkHttpCall)
                .maxDownloadIngNum(5)               //设置最大同时下载数量（不设置默认5）
                .saveProgressBytes(50 * 1204)       //设置每50kb触发一次saveProgress保存进度(不能在onProgress每次都保存,过于频繁)不设置默认50kb
                .build();
        mDownloadMgr.resumeTasks();       //恢复本地所有未完成的任务
        //mDownloadMgr.pauseAllTask();    //暂停所有任务
        //mDownloadMgr.addTask();         //添加任务
        //mDownloadMgr.deleteTask();      //删除任务
    }

    public static synchronized FNDApplication getInstance() {
        return mInstance;
    }

    public OkHttpCall getOkHttpCall() {
        return mOkHttpCall;
    }

    public DownloadManager getDownloadManager() {
        return mDownloadMgr;
    }

}
