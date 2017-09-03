package com.fengniao.action.okhttputils.response;

import com.fengniao.action.okhttputils.util.LogOkHttpUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * json类型的回调接口
 *
 * @author pujiang
 * @date 2017-9-3 16:00
 * @mail 515210530@qq.com
 * @Description:
 */
public abstract class JsonResponseHandler implements IResponseHandler {

    @Override
    public final void onSuccess(final Response response) {
        ResponseBody responseBody = response.body();
        String responseBodyStr = "";

        try {
            responseBodyStr = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
            LogOkHttpUtils.d("onResponse fail read response body");

            com.fengniao.action.okhttputils.OkHttpCall.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(response.code(), "fail read response body");
                }
            });
            return;
        } finally {
            responseBody.close();
        }

        final String finalResponseBodyStr = responseBodyStr;

        try {
            final Object result = new JSONTokener(finalResponseBodyStr).nextValue();
            if(result instanceof JSONObject) {
                com.fengniao.action.okhttputils.OkHttpCall.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(response.code(), (JSONObject) result);
                        onSuccess(response.code(), result.toString(),response.header("Example-Header-Key"));
                    }
                });
            } else if(result instanceof JSONArray) {
                com.fengniao.action.okhttputils.OkHttpCall.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(response.code(), (JSONArray) result);
                    }
                });
            } else {
                LogOkHttpUtils.d("onResponse fail parse jsonobject, body=" + finalResponseBodyStr);
                com.fengniao.action.okhttputils.OkHttpCall.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onFailure(response.code(), "fail parse jsonobject, body=" + finalResponseBodyStr);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
            LogOkHttpUtils.d("onResponse fail parse jsonobject, body=" + finalResponseBodyStr);
            com.fengniao.action.okhttputils.OkHttpCall.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(response.code(), "fail parse jsonobject, body=" + finalResponseBodyStr);
                }
            });
        }
    }

    public void onSuccess(int statusCode, JSONObject response) {
        LogOkHttpUtils.d("onSuccess(int statusCode, JSONObject response) was not overriden, but callback was received");
    }

    public void onSuccess(int statusCode, JSONArray response) {
        LogOkHttpUtils.d("onSuccess(int statusCode, JSONArray response) was not overriden, but callback was received");
    }

    public void onSuccess(int statusCode, String response, String Header) {
    }

    @Override
    public void onProgress(long currentBytes, long totalBytes) {
    }
}