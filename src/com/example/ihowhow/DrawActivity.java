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
	     dv.paint.setStrokeWidth(1);//初始化画笔的宽度  
	     
	     switch (item.getItemId()) {
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
	     }
	     return super.onOptionsItemSelected(item);
		 
	 }
}
