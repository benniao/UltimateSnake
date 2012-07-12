package com.stayhungry.nieyajie.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.stayhungry.libuyi.logic.R;
import com.stayhungry.snake.logic.Snack_703Activity;

public class LevelActivity extends Activity {
	ImageView level1;
	GestureDetector myGestureDetector;
	Animation anim, reverse;
	ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		final int touchEventId1 = -9983761;
		final int touchEventId2 = -9983768;

		level1 = (ImageView) findViewById(R.id.level1);
		scrollView = (ScrollView) findViewById(R.id.scrolllevel);
		scrollView.setOnTouchListener(new OnTouchListener() {
			Handler handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					if (msg.what == touchEventId1) {
						Toast.makeText(LevelActivity.this, "scroll begin", 100)
								.show();
					}
					if (msg.what == touchEventId2) {
						Toast.makeText(LevelActivity.this, "scroll stop", 100)
								.show();
					}
				}

			};

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					handler.sendMessageDelayed(
							handler.obtainMessage(touchEventId1, v), 1);
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					handler.sendMessageDelayed(
							handler.obtainMessage(touchEventId2, v), 1);
				}
				return false;
			}

		});
		level1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LevelActivity.this,
						Snack_703Activity.class);
				startActivity(intent);
			}
		});
	}

}
