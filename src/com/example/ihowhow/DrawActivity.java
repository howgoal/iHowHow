package com.example.ihowhow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DrawActivity extends Activity {

	private String textString = "";
	DrawView dv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);
		dv = (DrawView) findViewById(R.id.drawView1);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);
		inflator.inflate(R.menu.toolsmenu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	private void openOptionsDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("請輸入文字");
		LayoutInflater inflater = getLayoutInflater();
		final View v = (View) inflater.inflate(R.layout.activity_text,
				(ViewGroup) findViewById(R.id.dialog));
		dialog.setView(v);
		dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialoginterface, int i) {
				dv.text_moveable = true;
				EditText editText = (EditText) (v.findViewById(R.id.text));
				textString = editText.getText().toString();
				dv.drawText(textString);
				dv.invalidate();
			}
		});
		dialog.show();
	};

	public boolean onOptionsItemSelected(MenuItem item) {
		dv.paint.setXfermode(null);// 取消擦除效果
		dv.paint.setStrokeWidth(7);// 初始畫筆寬度

		switch (item.getItemId()) {
		case R.id.red:
			dv.paint.setColor(Color.RED);// 設定畫筆顏色
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
			dv.paint.setStrokeWidth(5);// 設定畫筆寬度
			break;
		case R.id.width_2:
			dv.paint.setStrokeWidth(15);
			break;
		case R.id.width_3:
			dv.paint.setStrokeWidth(30);
			break;
		case R.id.text:
			openOptionsDialog();

			break;
		case R.id.picture:
			dv.picture_moveable = true;
			dv.drawPicture();
			dv.invalidate();
			break;
		case R.id.save:
			dv.save();// 儲存畫布
			break;
		}
		return super.onOptionsItemSelected(item);

	}
}
