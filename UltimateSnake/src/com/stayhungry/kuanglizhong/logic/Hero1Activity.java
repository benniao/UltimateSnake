package com.stayhungry.kuanglizhong.logic;

import com.stayhungry.libuyi.logic.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Hero1Activity extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kuangmain);
		Button query = (Button) findViewById(R.id.query);
		Button insert = (Button) findViewById(R.id.insert);
		insert.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intentInsert = new Intent(Hero1Activity.this,
						Insert.class);
				startActivity(intentInsert);

			}
		});
		query.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intentQuery = new Intent(Hero1Activity.this, Query.class);
				startActivity(intentQuery);
			}
		});
	}
}