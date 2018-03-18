package com.example.walk01;

import android.content.Context;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.*;

public class Circle extends ImageView {
    private Paint paint ;
    
    public Circle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    public Circle(Context context, AttributeSet attrs) {  
        this(context, attrs,0);  
    }  
    public Circle(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle); 
        paint = new Paint();
        
    }  
    /**
     * ����Բ��ͼƬ
     */
    @Override  
    protected void onDraw(Canvas canvas) {  
  
        Drawable drawable = getDrawable();  
        if (null != drawable) {  
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();  
            Bitmap b = getCircleBitmap(bitmap, 14);  
            final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());  
            final Rect rectDest = new Rect(0,0,getWidth(),getHeight());
            paint.reset();  
            canvas.drawBitmap(b, rectSrc, rectDest, paint);  
  
        } else {  
            super.onDraw(canvas);  
        }  
    }  
  
    /**
     * ��ȡԲ��ͼƬ����
     * @param bitmap
     * @param pixels
     * @return Bitmap
     */
    private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {  
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),  
                bitmap.getHeight(), Config.ARGB_8888);  
        Canvas canvas = new Canvas(output);  
          
        final int color = 0xff424242;
       
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
        paint.setAntiAlias(true);  
        canvas.drawARGB(0, 0, 0, 0);  
        paint.setColor(color);  
        int x = bitmap.getWidth(); 
        
        canvas.drawCircle(x / 2, x / 2, x / 2, paint);  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        canvas.drawBitmap(bitmap, rect, rect, paint);  
        return output;  
        
        
    }  
}
