package com.example.ihowhow;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DrawActivity extends Activity{

	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator=new MenuInflater(this);  
        inflator.inflate(R.menu.toolsmenu, menu); 
        
        return super.onCreateOptionsMenu(menu);  
	}
	
	
	 public boolean onOptionsItemSelected(MenuItem item) {
		 
		 DrawView dv=(DrawView)findViewById(R.id.drawView1);  
	     dv.paint.setXfermode(null);//取消擦除效果
	     dv.paint.setStrokeWidth(7);//初始畫筆寬度
	     
	     switch (item.getItemId()) {
	     case R.id.red:  
             dv.paint.setColor(Color.RED);//設定畫筆顏色
             item.setChecked(true);  
             break;  
         case R.id.green:  
             dv.paint.setColor(Color.GREEN);
             item.setChecked(true);  
             break;  
         case R.id.blue:  
             dv.paint.setColor(Color.BLUE);  
             item.setChecked(true);  
             break;  
         case R.id.width_1:  
             dv.paint.setStrokeWidth(5);//設定畫筆寬度
             break;  
         case R.id.width_2:  
             dv.paint.setStrokeWidth(15);
             break;  
         case R.id.width_3:  
             dv.paint.setStrokeWidth(30);
             break;  
         case R.id.clear:  
             dv.clear();//清除畫布
             break;  
         case R.id.save:  
             dv.save();//儲存畫布
             break;  
	     }
	     return super.onOptionsItemSelected(item);
		 
	 }
}
