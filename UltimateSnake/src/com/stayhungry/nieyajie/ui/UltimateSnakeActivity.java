package com.stayhungry.nieyajie.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.stayhungry.libuyi.logic.R;

public class UltimateSnakeActivity extends Activity {
	ImageButton newGameBn;
	ImageButton achievementBn;
	ImageButton optionBn;
	ImageButton exitBn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		newGameBn = (ImageButton) findViewById(R.id.newgamebn);
		achievementBn = (ImageButton) findViewById(R.id.achievementbn);
		optionBn = (ImageButton) findViewById(R.id.optionbn);
		exitBn = (ImageButton) findViewById(R.id.exitbn);

		newGameBn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UltimateSnakeActivity.this,
						GameOptionActivity.class);
				startActivity(intent);
			}
		});
		optionBn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UltimateSnakeActivity.this,
						OptionActivity.class);
				startActivity(intent);
			}
		});
		achievementBn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UltimateSnakeActivity.this,
						Achievement.class);
				startActivity(intent);

			}
		});
		exitBn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
}