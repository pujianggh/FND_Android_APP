package com.fengniao.action.okhttputils.body;

import com.fengniao.action.okhttputils.response.DownloadResponseHandler;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 重写重写responsebody
 * 设置下载进度监听
 *
 * @author pujiang
 * @date 2017-9-3 16:00
 * @mail 515210530@qq.com
 * @Description:
 */
public class ResponseProgressBody extends ResponseBody {
    private ResponseBody mResponseBody;
    private DownloadResponseHandler mDownloadResponseHandler;
    private BufferedSource bufferedSource;

    public ResponseProgressBody(ResponseBody responseBody, DownloadResponseHandler downloadResponseHandler) {
        this.mResponseBody = responseBody;
        this.mDownloadResponseHandler = downloadResponseHandler;
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {

        return new ForwardingSource(source) {

            long totalBytesRead;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                //这个的进度应该是读取response每次内容的进度，在写文件进度之前 所以暂时以写完文件的进度为准
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += ((bytesRead != -1) ? bytesRead : 0);
                //修改：添加进度
                if (mDownloadResponseHandler != null) {
                    mDownloadResponseHandler.onProgress(totalBytesRead, mResponseBody.contentLength());
                }
                return bytesRead;
            }
        };
    }
}
