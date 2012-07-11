package com.stayhungry.kuanglizhong.logic;

import com.stayhungry.libuyi.logic.R;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Insert extends Activity
{
	/**
	 * @uml.property  name="dbInsert"
	 * @uml.associationEnd  
	 */
	SQLiteDatabase dbInsert;

	// EditText text1;
	// EditText text2;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
		dbInsert = SQLiteDatabase.openOrCreateDatabase(
				"/data/data/com.stayhungry.libuyi/my.db3", null);
		Button log = (Button) findViewById(R.id.log);
		log.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String name = ((EditText) findViewById(R.id.name)).getText()
						.toString();
				String score = ((EditText) findViewById(R.id.score)).getText()
						.toString();
				insertRecord(name,score);
			}
		});
	}

	public void onDestroy()
	{
		super.onDestroy();
		if (dbInsert != null && dbInsert.isOpen())
		{
			dbInsert.close();
		}
	}

	private void insertRecord(String name, String score)
	{
		int scores = Integer.parseInt(score);
		try
		{
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("score", scores);
			dbInsert.insert("hero", null, values);

		}
		catch (SQLiteException s)
		{
			dbInsert.execSQL("create table hero(_id integer primary key autoincrement,"
					+ " name varchar(50)," + " score integer)");
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("score", score);
			dbInsert.insert("hero", null, values);
		}
		Insert.this.finish();

	}

}
