package com.stayhungry.nieyajie.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.stayhungry.libuyi.logic.R;

public class UltimateSnakeActivity extends Activity implements OnClickListener {
	ImageButton newGameBn;
	ImageButton achievementBn;
	ImageButton optionBn;
	ImageButton exitBn;
	ImageButton musicBn;
	ImageButton soundBn;
	private static int soundbnState = 0;
	private static int musicbnState = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		newGameBn = (ImageButton) findViewById(R.id.newgamebn);
		achievementBn = (ImageButton) findViewById(R.id.achievementbn);
		optionBn = (ImageButton) findViewById(R.id.optionbn);
		exitBn = (ImageButton) findViewById(R.id.exitbn);
		soundBn = (ImageButton) findViewById(R.id.soundbn);
		musicBn = (ImageButton) findViewById(R.id.musicbn);

		newGameBn.setOnClickListener(this);
		optionBn.setOnClickListener(this);
		achievementBn.setOnClickListener(this);
		exitBn.setOnClickListener(this);
		soundBn.setOnClickListener(this);
		musicBn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.newgamebn:
			Intent intent1 = new Intent(UltimateSnakeActivity.this,
					GameOptionActivity.class);
			startActivity(intent1);
			break;
		case R.id.optionbn:
			Intent intent2 = new Intent(UltimateSnakeActivity.this,
					OptionActivity.class);
			startActivity(intent2);
			break;
		case R.id.achievementbn:
			Intent intent3 = new Intent(UltimateSnakeActivity.this,
					Achievement.class);
			startActivity(intent3);
			break;
		case R.id.exitbn:
			System.exit(0);
			break;
		case R.id.soundbn:
			if (soundbnState == 0) {
				soundBn.setImageResource(R.drawable.soundoffbn);
				soundbnState = 1;
			} else {
				soundBn.setImageResource(R.drawable.soundbn);
				soundbnState = 0;
			}
			break;
		case R.id.musicbn:
			if (musicbnState == 0) {
				musicBn.setImageResource(R.drawable.soundbn);
				musicbnState = 1;
			} else {
				musicBn.setImageResource(R.drawable.musicbn);
				musicbnState = 0;
			}
			break;

		default:
			break;
		}
	}
}