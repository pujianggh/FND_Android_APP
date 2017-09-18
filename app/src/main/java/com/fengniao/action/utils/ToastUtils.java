package com.fengniao.action.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fengniao.action.R;

/**
 * 自定义提示
 *
 * @author pujiang
 * @date 2017-9-4 19:50
 * @mail 515210530@qq.com
 * @Description:
 */
public class ToastUtils {

    /**
     * 提示
     * @param context
     * @param text
     */
    public static void topToast(Context context,String text,int time){
        // 创建
        Toast toast = new Toast(context);
        // 找到toast布局的位置
        View layout = View.inflate(context, R.layout.layout_toast_tip,null);
        TextView tv_tip = (TextView)layout.findViewById(R.id.tv_tip);
        tv_tip.setText(text);
        // 设置toast文本，把设置好的布局传进来
        toast.setView(layout);
        // 设置土司显示在屏幕的位置
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        // 获取屏幕高度
        int height = display.getHeight();
        //toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.TOP,0,70);
        toast.setGravity(Gravity.TOP, 0, height / 4);
        toast.setDuration(time);
        // 显示土司
        toast.show();
    }
}
