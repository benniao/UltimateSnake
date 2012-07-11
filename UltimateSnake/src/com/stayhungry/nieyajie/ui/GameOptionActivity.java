package com.stayhungry.nieyajie.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.stayhungry.libuyi.logic.R;

public class GameOptionActivity extends Activity implements OnClickListener {
	ImageButton classicBn;
	ImageButton limitBn;
	ImageButton abcBn;
	ImageButton doubleBn;
	ImageButton backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameoption);
		classicBn = (ImageButton) findViewById(R.id.classic);
		limitBn = (ImageButton) findViewById(R.id.limittime);
		abcBn = (ImageButton) findViewById(R.id.abc);
		doubleBn = (ImageButton) findViewById(R.id.doubleperson);
		backButton = (ImageButton) findViewById(R.id.backToMain);
		classicBn.setOnClickListener(this);
		abcBn.setOnClickListener(this);
		limitBn.setOnClickListener(this);
		doubleBn.setOnClickListener(this);
		backButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.classic:
			Intent intent = new Intent(GameOptionActivity.this,
					LevelActivity.class);
			startActivity(intent);
			break;
		case R.id.backToMain:
			GameOptionActivity.this.finish();
		default:
			break;
		}
	}

}
