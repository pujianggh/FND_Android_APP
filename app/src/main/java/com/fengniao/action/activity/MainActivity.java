package com.fengniao.action.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fengniao.action.R;
import com.fengniao.action.app.FNDApplication;
import com.fengniao.action.commonlibrary.utils.ChannelInfoUitls;
import com.fengniao.action.okhttputils.OkHttpCall;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        Log.d("pj","MainActivity---->");

        OkHttpCall mOkHttpCall = FNDApplication.getInstance().getOkHttpCall();

        ChannelInfoUitls.getChannelNameApplication(this,"appkey");
    }
}
