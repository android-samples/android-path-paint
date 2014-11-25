package com.example.mycanvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}

}

class MyView extends View{
	
	// ディスプレイ情報
	int mWidth;
	int mHeight;
	
	// 描画用オブジェクト
	Path mPath1;
	Paint mPaintRed;
	Paint mPaintBlue;
	
	public MyView(Context context) {
		super(context);
		
		// ディスプレイ情報
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		mWidth = display.getWidth();
		mHeight = display.getHeight();
		
		// 描画用Path
		mPath1 = new Path();
		mPath1.addCircle(
			100, 100, // 座標
			50, // 半径
			Direction.CW // 方向（時計回り）
		);
		mPath1.addCircle(
			100, 100, // 座標
			80, // 半径
			Direction.CW // 方向（時計回り）
		);
		
		// 描画用Paint
		mPaintRed = new Paint();
		mPaintRed.setColor(Color.argb(100, 255, 0, 0));
		mPaintRed.setStrokeWidth(8);
		mPaintRed.setStyle(Paint.Style.STROKE); // 塗りつぶさない
		mPaintBlue = new Paint();
		mPaintBlue.setColor(Color.argb(100, 0, 0, 255));
		mPaintBlue.setStrokeWidth(8);
		mPaintBlue.setStyle(Paint.Style.STROKE); // 塗りつぶさない
	}

	// 自前描画
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// Path描画
		canvas.drawPath(mPath1, mPaintRed);
		
		// ずらしてPath描画
		canvas.save();
		canvas.translate(200, 0);
		canvas.drawPath(mPath1, mPaintBlue);
		canvas.restore();
	}
}
