package com.example.ihowhow;

import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private static final int MENU_TAKE_PICTURE = Menu.FIRST,MENU_SHOW_PICTURE = Menu.FIRST + 1 ;
	
	private Camera mCamera;
	private CameraPreview mCamPreview ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//        
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        
//        mCamPreview = new CameraPreview(this);
//        setContentView(mCamPreview);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
    	
//    	menu.add(0,MENU_TAKE_PICTURE,0,"照相");
//    	menu.add(0,MENU_SHOW_PICTURE,0,"顯示照片");
//        return true;
    	
    	MenuInflater inflator=new MenuInflater(this);  
        inflator.inflate(R.menu.toolsmenu, menu);  
        return super.onCreateOptionsMenu(menu);  
    }

    /* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
//		mCamera = Camera.open();
//		mCamPreview.set(this, mCamera);
		
		super.onResume();
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
//		mCamera.stopPreview();
//		mCamera.release();
//		mCamera = null;
		super.onPause();
	}


	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }*/
		 //获取自定义的绘图视图  
        DrawView dv=(DrawView)findViewById(R.id.drawView1);  
        dv.paint.setXfermode(null);//取消擦除效果  
        dv.paint.setStrokeWidth(1);//初始化画笔的宽度  
		
		switch (item.getItemId()) {
//		case MENU_TAKE_PICTURE:
//			mCamera.takePicture(camShutterCallback, camRawDataCallback, camJpegCallback);
//			break;

			
		 case R.id.red:  
             dv.paint.setColor(Color.RED);//设置笔的颜色为红色  
             item.setChecked(true);  
             break;  
         case R.id.green:  
             dv.paint.setColor(Color.GREEN);//设置笔的颜色为绿色  
             item.setChecked(true);  
             break;  
         case R.id.blue:  
             dv.paint.setColor(Color.BLUE);//设置笔的颜色为蓝色  
             item.setChecked(true);  
             break;  
         case R.id.width_1:  
             dv.paint.setStrokeWidth(5);//设置笔触的宽度为1像素  
             break;  
         case R.id.width_2:  
             dv.paint.setStrokeWidth(15);//设置笔触的宽度为5像素  
             break;  
         case R.id.width_3:  
             dv.paint.setStrokeWidth(30);//设置笔触的宽度为10像素  
             break;  
         case R.id.clear:  
             dv.clear();//擦除绘画  
             break;  
         case R.id.save:  
             dv.save();//保存绘画  
             break;  
             
		default:
			break;
		}
        return super.onOptionsItemSelected(item);
    }
	
	ShutterCallback camShutterCallback = new ShutterCallback() {
		public void onShutter() {
			// 通知使用者已完成拍照,例如發出一個聲音
		}
	};

	PictureCallback camRawDataCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// 用來接收原始的影像資料
		}
	};

	PictureCallback camJpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// 用來接收壓縮成jpeg格式的影像資料

			FileOutputStream outStream = null;
			try {
				outStream = new FileOutputStream("/sdcard/photo.jpg");
				outStream.write(data);
				outStream.close();
			} catch (IOException e) {
				Toast.makeText(MainActivity.this, "影像檔儲存錯誤！", Toast.LENGTH_SHORT)
					.show();
			}

			mCamera.startPreview();
		}
	};
}
