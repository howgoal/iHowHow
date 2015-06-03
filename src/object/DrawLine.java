package object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.Log;

public class DrawLine extends DrawObject {
	Path path;
	Paint paint;
	public DrawLine(Path _path) {
		// TODO Auto-generated constructor stub
		path = _path;
		paint = new Paint(Paint.DITHER_FLAG);// Paint.DITHER_FLAG防抖动的
		paint.setColor(Color.RED); // 初始畫筆為紅色
		// 設定畫筆風格
		paint.setStyle(Paint.Style.STROKE);// 設定填充方式為描邊
		paint.setStrokeJoin(Paint.Join.ROUND);// 設定筆刷轉彎處的連接風格
		paint.setStrokeCap(Paint.Cap.ROUND);// 設定筆刷的圖形樣式
		
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawPath(path, paint);
		
	}

	@Override
	public void movie(int _x, int _y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPaint(Paint _paint) {
		// TODO Auto-generated method stub
		Log.v("setPaintColor",String.valueOf(_paint.getColor()));
		
		paint.setColor(_paint.getColor());
		
		paint.setStrokeWidth(_paint.getStrokeWidth());
		
	}

}
