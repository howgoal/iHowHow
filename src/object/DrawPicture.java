package object;

import com.example.ihowhow.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

public class DrawPicture extends DrawObject {
	Bitmap bitmap = null;
	int x = 0;
	int y = 0;
	public DrawPicture(int _id,Context context) {
		// TODO Auto-generated constructor stub
		bitmap = ((BitmapDrawable)context.getResources().getDrawable(
				R.drawable.hahah)).getBitmap();
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(bitmap, x, y, null); // 繪製圖像
	}

	@Override
	public void movie(int _x, int _y) {
		// TODO Auto-generated method stub
		x = _x;
		y = _y;
	}


	@Override
	public void setPaint(Paint _paint) {
		// TODO Auto-generated method stub
		
	}

}
