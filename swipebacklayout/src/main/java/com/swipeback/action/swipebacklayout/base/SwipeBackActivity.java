
package com.swipeback.action.swipebacklayout.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.swipeback.action.swipebacklayout.layout.SwipeBackLayout;
import com.swipeback.action.swipebacklayout.util.SwipeBackUtils;

/**
 * SwipeBackActivityBase
 *
 * @author pujiang
 * @date 2017-9-3 21:54
 * @mail 515210530@qq.com
 * @Description:
 */
public class SwipeBackActivity extends Activity implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        SwipeBackUtils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

}
