package com.swipeback.action.swipebacklayout.base;


import com.swipeback.action.swipebacklayout.layout.SwipeBackLayout;

/**
 * SwipeBackActivityBase
 *
 * @author pujiang
 * @date 2017-9-3 21:54
 * @mail 515210530@qq.com
 * @Description:
 */
public interface SwipeBackActivityBase {
    /**
     * @return the SwipeBackLayout associated with this activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    public abstract void scrollToFinishActivity();

}
