package com.stayhungry.libuyi.logic;


import com.stayhungry.libuyi.logic.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PhotoShare extends Activity
{
	/**
	 * Called when the activity is first created.
	 * @uml.property  name="renRenShare"
	 * @uml.associationEnd  
	 */
	Button renRenShare;
	/**
	 * Called when the activity is first created.
	 * @uml.property  name="xinLangShare"
	 * @uml.associationEnd  
	 */
	Button xinLangShare;
	/**
	 * Called when the activity is first created.
	 * @uml.property  name="shoot"
	 * @uml.associationEnd  
	 */
	Button shoot;
	/**
	 * @uml.property  name="fileName"
	 */
	String fileName;
	/**
	 * @uml.property  name="picPath"
	 */
	String picPath;
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  
	 */
	View view;
	/**
	 * @uml.property  name="intent"
	 * @uml.associationEnd  
	 */
	Intent intent;
	/**
	 * @uml.property  name="isShoot"
	 */
	Boolean isShoot;// 鏄惁鎴浘鎴愬姛銆�
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		renRenShare = (Button) findViewById(R.id.renRenShare);
		xinLangShare = (Button) findViewById(R.id.xinLangShare);
		shoot = (Button) findViewById(R.id.shoot);
		view = (View) findViewById(R.id.rootView);
		intent = new Intent();
		shoot.setOnClickListener(new OnClickListener()// shoot the current picture
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				fileName = ScreenShot.formatFileName(new Time());
				isShoot = ScreenShot.screenShot(fileName, view);
				if (isShoot)
				{
					Toast.makeText(PhotoShare.this, "Share Success", 4000).show();
				}
				else
				{
					Toast.makeText(PhotoShare.this, "Share Fail", 4000).show();
				}
			}
		});
		renRenShare.setOnClickListener(new OnClickListener()//Share into the renren		
		{
			@Override
			public void onClick(View v)
			{
				if (isShoot)
				{
					Intent intent = new Intent();
					intent.setClass(PhotoShare.this, RenRenShare.class);
					intent.putExtra("fileName", fileName);
					startActivity(intent);
				}
				else
				{
					Toast.makeText(PhotoShare.this, "Share Fail", 4000).show();
				}
			}
		});
		xinLangShare.setOnClickListener(new OnClickListener()//Share into xinLang	
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (isShoot)
				{
					intent.setClass(PhotoShare.this, XinLangShare.class);
					intent.putExtra("fileName", fileName);
					startActivity(intent);
				}
				else
				{
					Toast.makeText(PhotoShare.this, "Share Fail", 4000).show();
				}
			}
		});
	}
}