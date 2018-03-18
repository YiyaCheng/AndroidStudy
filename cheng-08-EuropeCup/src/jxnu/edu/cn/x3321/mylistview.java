package jxnu.edu.cn.x3321;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;

//public class mylistview extends ListView {
public class mylistview extends GridView {
    public mylistview(Context context) {
        // TODO Auto-generated method stub
        super(context);
    }

    public mylistview(Context context, AttributeSet attrs) {
        // TODO Auto-generated method stub
        super(context, attrs);
    }

    public mylistview(Context context, AttributeSet attrs, int defStyle) {
        // TODO Auto-generated method stub
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
