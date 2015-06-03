package object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawText extends DrawObject{

	int x = 0,y=50;
	String text;
	Paint paint;
	public DrawText(String _text) {
		// TODO Auto-generated constructor stub
		paint = new Paint(Paint.DITHER_FLAG);// Paint.DITHER_FLAG防抖动的
		paint.setColor(Color.RED); // 初始畫筆為紅色
		// 設定畫筆風格
		paint.setStyle(Paint.Style.STROKE);// 設定填充方式為描邊
		paint.setStrokeJoin(Paint.Join.ROUND);// 設定筆刷轉彎處的連接風格
		paint.setStrokeCap(Paint.Cap.ROUND);// 設定筆刷的圖形樣式
		
		text = _text;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawText(text, x, y,paint);
	}

	@Override
	public void movie(int _x, int _y) {
		// TODO Auto-generated method stub
		x=_x;
		y=_y;
	}

	@Override
	public void setPaint(Paint _paint) {
		// TODO Auto-generated method stub
		paint.setTextSize(_paint.getTextSize());
		paint.setColor(_paint.getColor());
		paint.setStrokeWidth(_paint.getStrokeWidth());
	}
	

}
