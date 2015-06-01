package com.example.ihowhow;

import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.Intent;
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
       // setContentView(R.layout.activity_main);
        

        mCamPreview = new CameraPreview(this);
        setContentView(mCamPreview);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
    	
    	menu.add(0,MENU_TAKE_PICTURE,0,"照相");
    	menu.add(0,MENU_SHOW_PICTURE,0,"顯示照片");
        return true;
    	
    	
    }

    /* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mCamera = Camera.open();
		mCamPreview.set(this, mCamera);
		
		super.onResume();
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
		super.onPause();
	}


	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
		 //获取自定义的绘图视图  
    
		
		switch (item.getItemId()) {
		case MENU_TAKE_PICTURE:
			mCamera.takePicture(camShutterCallback, camRawDataCallback, camJpegCallback);
			break;	
		

 		case MENU_SHOW_PICTURE:
 			//setContentView(R.layout.activity_draw);
 			Intent intent_draw = new Intent();
			intent_draw.setClass(MainActivity.this, DrawActivity.class);
			startActivity(intent_draw); 
			MainActivity.this.finish();
 			break;
		default:
			break;
		}
        return super.onOptionsItemSelected(item);
    }
	
	ShutterCallback camShutterCallback = new ShutterCallback() {
		public void onShutter() {

			// 通知使用者已完成拍照,例如發出一個聲音

			Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT)
			.show();

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
				outStream = new FileOutputStream("sdcard/photo.jpg");
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
