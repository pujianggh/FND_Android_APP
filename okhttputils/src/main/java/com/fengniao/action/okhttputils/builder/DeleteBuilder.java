package com.fengniao.action.okhttputils.builder;

import com.fengniao.action.okhttputils.callback.OKHttpCallback;
import com.fengniao.action.okhttputils.response.IResponseHandler;
import com.fengniao.action.okhttputils.util.LogOkHttpUtils;
import okhttp3.Request;

/**
 * 删除DeleteBuilder
 *
 * @author pujiang
 * @date 2017-9-3 16:00
 * @mail 515210530@qq.com
 * @Description:
 */
public class DeleteBuilder extends OkHttpRequestBuilder<DeleteBuilder> {

    public DeleteBuilder(com.fengniao.action.okhttputils.OkHttpCall okHttpCall) {
        super(okHttpCall);
    }

    @Override
    public void enqueue(final IResponseHandler responseHandler) {
        try {
            if (mUrl == null || mUrl.length() == 0) {
                throw new IllegalArgumentException("url can not be null !");
            }

            Request.Builder builder = new Request.Builder().url(mUrl).delete();
            appendHeaders(builder, mHeaders);

            if (mTag != null) {
                builder.tag(mTag);
            }

            Request request = builder.build();

            mMyOkHttp.getOkHttpClient()
                    .newCall(request)
                    .enqueue(new OKHttpCallback(responseHandler));
        } catch (Exception e) {
            LogOkHttpUtils.d("Delete enqueue error:" + e.getMessage());
            responseHandler.onFailure(0, e.getMessage());
        }
    }
}

