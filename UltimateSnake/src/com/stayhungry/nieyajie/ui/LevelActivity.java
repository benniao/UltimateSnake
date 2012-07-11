package com.stayhungry.nieyajie.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.stayhungry.libuyi.logic.R;
import com.stayhungry.snake.logic.Snack_703Activity;

public class LevelActivity extends Activity implements OnGestureListener {
	ImageView level1;
	GestureDetector myGestureDetector;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VEOCITY = 250;
	Animation anim, reverse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		// 手势检测器
		myGestureDetector = new GestureDetector(this);
		level1 = (ImageView) findViewById(R.id.level1);
		// 加载动画资源
		anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		anim.setFillAfter(true);
		reverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
		reverse.setFillAfter(true);
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

	private void OnClickListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return myGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		// TODO Auto-generated method stub
		if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityY) > SWIPE_THRESHOLD_VEOCITY) {
			level1.setAnimation(anim);
			level1.startAnimation(anim);

		}
		if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityY) > SWIPE_THRESHOLD_VEOCITY) {
			level1.setAnimation(reverse);
			level1.startAnimation(reverse);

		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		// Toast.makeText(LevelActivity.this, "onLongPress", 100).show();

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		// Toast.makeText(LevelActivity.this, "onScroll", 100).show();

		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// Toast.makeText(LevelActivity.this, "onShowPress", 100).show();

		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		// Toast.makeText(LevelActivity.this, "onSingleTapUp", 100).show();

		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
