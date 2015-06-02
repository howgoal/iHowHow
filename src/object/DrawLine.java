package object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;

public class DrawLine extends DrawObject {
	Path path;
	public DrawLine(Path _path) {
		// TODO Auto-generated constructor stub
		path = _path;
	}

	@Override
	public void draw(Canvas canvas,Paint _paint) {
		// TODO Auto-generated method stub
		canvas.drawPath(path, _paint);
	}

	@Override
	public void movie(int _x, int _y) {
		// TODO Auto-generated method stub
		
	}

}
