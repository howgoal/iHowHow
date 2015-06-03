package com.example.ihowhow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import object.DrawLine;
import object.DrawObject;
import object.DrawPicture;
import object.DrawText;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
	private int view_width = 0;// 螢幕的寬度
	private int view_height = 0;// 螢幕的高度
	private float preX;// 起始點的X座標
	private float preY;// 起始點的Y座標
	private Path path;// 路徑
	public Paint paint;// 畫筆

	
	Bitmap cacheBitmap = null;// 定義一個的暫存圖片，把此圖片當作緩衝區
	Canvas cacheCanvas = null;// 定義cacheBitmap為Canvas對象

	private ArrayList<Path> paths = new ArrayList<Path>();
	private ArrayList<Path> undonePaths = new ArrayList<Path>();

	private ArrayList<DrawObject> drawObjects = new ArrayList<DrawObject>();
	private DrawObject drawThings;
	boolean picture_moveable = false;
	int pictureX = 0;
	int pictureY = 0;

	boolean text_moveable = false;
	int textX = 0;
	int textY = 50;
	String textString = "";
	int mode = 0;
	
	
	
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		view_width = context.getResources().getDisplayMetrics().widthPixels;// 讀取螢幕寬度
		view_height = context.getResources().getDisplayMetrics().heightPixels;// 讀取螢幕高度
		// 創立一個與該View相同大小的緩衝區
		cacheBitmap = Bitmap.createBitmap(view_width, view_height,
				Config.ARGB_8888);
		cacheCanvas = new Canvas();// 創立一個新的畫布
		
		path = new Path();
		// 在cacheCanvas上繪製cacheBitmap

		cacheCanvas.setBitmap(cacheBitmap);
		paint = new Paint(Paint.DITHER_FLAG);// Paint.DITHER_FLAG防抖动的
		paint.setColor(Color.RED); // 初始畫筆為紅色
		// 設定畫筆風格
		paint.setStyle(Paint.Style.STROKE);// 設定填充方式為描邊
		paint.setStrokeJoin(Paint.Join.ROUND);// 設定筆刷轉彎處的連接風格
		paint.setStrokeCap(Paint.Cap.ROUND);// 設定筆刷的圖形樣式
		paint.setStrokeWidth(7);// 設定默認筆刷寬度為7像素
		// paint.setAntiAlias(true);// 設定抗鋸齒效果
		// paint.setDither(true);// 使用抖動效果
		background();

		Log.v("!!", "!!");

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint bmpPaint = new Paint();
		bmpPaint.setTextSize(200);
		bmpPaint.setColor(Color.WHITE);

		canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint);// 繪製cacheBitmap
		canvas.drawPath(path, paint);// 繪製路徑
		// Draw Picture

		if (mode ==1 ) {
			Bitmap bitmap = null; // Bitmap對象
			bitmap = ((BitmapDrawable) getResources().getDrawable(
					R.drawable.hahah)).getBitmap();
			canvas.drawBitmap(bitmap, pictureX, pictureY, null); // 繪製圖像
		}

		if (mode == 2) {
			Paint tpaint = new Paint();
			tpaint.setTextSize(50);// 設定字體大小
			tpaint.setColor(Color.LTGRAY);// 設定字體顏色
			canvas.drawText(textString, textX, textY, tpaint);
		}
		canvas.save(Canvas.ALL_SAVE_FLAG);// 儲存canvas的狀態
		canvas.restore();

	}

	public void background() {
		Paint bmpPaint = new Paint();
		bmpPaint.setTextSize(200);
		bmpPaint.setColor(Color.WHITE);
		cacheCanvas.drawColor(Color.BLUE);
		result();
	}

	protected void result() {
		File root = Environment.getExternalStorageDirectory();
		Bitmap bMap = BitmapFactory.decodeFile(root + "/photo.jpg");

		Paint bmpPaint = new Paint();
		cacheCanvas.drawBitmap(resize(bMap), 0, view_height / 7, bmpPaint);// 繪製cacheBitmap
	}

	private static Bitmap resize(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postScale(0.18f, 0.2f);
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (mode == 0) {
			float x = event.getX();
			float y = event.getY();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				path.moveTo(x, y);

				preX = x;
				preY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				float dx = Math.abs(x - preX);
				float dy = Math.abs(y - preY);
				if (dx > 5 || dy > 5) {
					path.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
					preX = x;
					preY = y;
				}
				break;
			case MotionEvent.ACTION_UP:
				path.lineTo(preX, preY);

				cacheCanvas.drawPath(path, paint);// 繪製路徑
				drawThings = new DrawLine(path);
				drawThings.setPaint(paint);
				drawObjects.add(drawThings);
				paths.add(path);
				Log.i("###", String.valueOf(paths.size()));
				path = new Path();

				path.reset();
				break;
			}
			invalidate();
		}

		else if (mode == 1) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:

				pictureX = x;
				pictureY = y;

				break;
			case MotionEvent.ACTION_MOVE:
				float dx = Math.abs(x - preX);
				float dy = Math.abs(y - preY);
				if (dx > 5 || dy > 5) {
					// path.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
					pictureX = x;
					pictureY = y;
				}

				break;
			case MotionEvent.ACTION_UP:

				mode = 0;
				drawThings.movie(x,y);
				drawThings.setPaint(paint);
				drawThings.draw(cacheCanvas);
				picture_moveable = false;
				pictureX = 0;
				pictureY = 0;
				break;
			}
			invalidate();
		} else if (mode == 2) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:

				textX = x;
				textY = y;

				break;
			case MotionEvent.ACTION_MOVE:
				float dx = Math.abs(x - preX);
				float dy = Math.abs(y - preY);
				if (dx > 5 || dy > 5) {
					// path.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
					textX = x;
					textY = y;
				}

				break;
			case MotionEvent.ACTION_UP:
				Paint tpaint = new Paint();
				tpaint.setTextSize(50);// 設定字體大小
				tpaint.setColor(Color.LTGRAY);// 設定字體顏色
				drawThings.setPaint(tpaint);
				drawThings.movie(x,y);
				drawThings.draw(cacheCanvas);
				mode = 0;
				text_moveable = false;
				textX = 0;
				textY = 50;
				break;
			}
			invalidate();
		}

		return true;
	}

	public void onClickUndo() {
		if (drawObjects.size() > 0) {
			drawObjects.remove(drawObjects.size()-1);
			background();
			for (int i = 0; i < drawObjects.size(); i++) {
				drawObjects.get(i).draw(cacheCanvas);
				invalidate();
			}
			invalidate();

		} else {

		}
		// toast the user
	}

	public void clear() {
		// cacheCanvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
		invalidate();
		background();
		// 設定圖形重疊時的處理方式
		// paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		// 設定畫筆寬度
		// paint.setStrokeWidth(50);
	}

	public void drawPicture() {
		if (picture_moveable) {
			mode = 1;
			drawThings = new DrawPicture(R.drawable.hahah,getContext());
			drawThings.movie(pictureX, pictureY);
			drawObjects.add(drawThings);
			
		}
	}

	public void drawText(String text) {
		if (text_moveable) {
			mode = 2;
			textString = text;
			drawThings = new DrawText(text);
			drawThings.movie(textX, textY);
			drawThings.setPaint(paint);
			drawObjects.add(drawThings);
		}
	}

	public void save() {
		try {
			saveBitmap("myPitcture");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void saveBitmap(String fileName) throws IOException {
		File file = new File(getSDPath() + "/" + fileName + ".JPEG");
		Log.v(getSDPath(), "SD");
		// file.setWritable(true);
		file.createNewFile();
		FileOutputStream fileOS = new FileOutputStream(file);
		cacheBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOS);
		fileOS.flush();
		fileOS.close();

	}

	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判斷SD卡是否存在
		if (sdCardExist) // 如果SD卡存在，則讀取根目錄
		{
			sdDir = Environment.getExternalStorageDirectory();
		}
		return sdDir.toString();

	}

}
