package object;

import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawText extends DrawObject{

	int x = 0,y=50;
	String text;
	public DrawText(String _text) {
		// TODO Auto-generated constructor stub
		text = _text;
	}

	@Override
	public void draw(Canvas canvas, Paint _paint) {
		// TODO Auto-generated method stub
		canvas.drawText(text, x, y, _paint);
	}

	@Override
	public void movie(int _x, int _y) {
		// TODO Auto-generated method stub
		x=_x;
		y=_y;
	}
	

}
