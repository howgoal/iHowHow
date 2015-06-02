package object;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class DrawObject {

	public DrawObject() {
		// TODO Auto-generated constructor stub
	}

	public abstract void draw(Canvas canvas,Paint _paint);
	public abstract void movie(int _x,int _y);
}
