package com.sc.basictable.Functions;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * class description
 * 功能类
 * @author
 * @date
 */
public class BaseFunction {

    /**
     * 根据字体大小，计算字体宽度
     * @param str
     * @param textSize
     * @return
     */
    public static int stringWidthWithSize(String str,int textSize){
        if(str == null)
            return 0;
        if(str.length()==0)
            return 0;
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setTextSize(textSize);
        Rect rect = new Rect();
        p.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

}
