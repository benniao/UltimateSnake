package com.stayhungry.libuyi.logic;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.exception.RenrenAuthError;
import com.renren.api.connect.android.view.RenrenAuthListener;
import com.stayhungry.libuyi.logic.R;

public class RenRenShare extends Activity  implements RenrenAuthListener
{

	private static final String API_KEY = "29d86f14293d435bae499bd4b4ee9197";

	private static final String SECRET_KEY = "70f75e64925744aa90f309db44967a1f";

	private static final String APP_ID = "105381";

	/**
	 * @uml.property  name="renren"
	 * @uml.associationEnd  
	 */
	private Renren renren;

	/**
	 * @uml.property  name="handler"
	 * @uml.associationEnd  
	 */
	private Handler handler;

	/**
	 * @uml.property  name="fileName"
	 */
	String fileName;
	/**
	 * @uml.property  name="share"
	 * @uml.associationEnd  
	 */
	Button share;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		renren = new Renren(API_KEY, SECRET_KEY, APP_ID, RenRenShare.this);
		handler = new Handler();
		Intent intent=getIntent();
		fileName=intent.getStringExtra("fileName");
		share=(Button) findViewById(R.id.share);
		share.setOnClickListener(new OnClickListener()	
		{
			@Override
			public void onClick(View v)
			{
				
				uploadPhotoWithActivity(RenRenShare.this, renren,
						fileName);
			}

			private void uploadPhotoWithActivity(Activity activity,
					Renren renren, String fileName)
			{
				// TODO Auto-generated method stub
				File file = Environment.getExternalStorageDirectory();
				String sdPath = file.getAbsolutePath();
				String picPath = sdPath + "/" + fileName;
				// 以上准备好了File参数
				// 下面调用SDK接口
				renren.publishPhoto(activity, new File(picPath), "UltimateSnake");
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		renren.init(this);
	}

	/**
	 * start the api list activity
	 */

	/**
	 * initialize the buttons and events
	 */

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (renren != null)
		{
			renren.authorizeCallback(requestCode, resultCode, data);
		}
	}
	@Override
	public void onComplete(Bundle values)
	{
		// TODO Auto-generated method stub
		Log.d("test", values.toString());
		Toast.makeText(RenRenShare.this, "验证成功", 4000).show();
		this.finish();
	}

	@Override
	public void onRenrenAuthError(RenrenAuthError renrenAuthError)
	{
		// TODO Auto-generated method stub
		handler.post(new Runnable()
		{

			@Override
			public void run()
			{
				Toast.makeText(RenRenShare.this, "验证失败",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onCancelLogin()
	{
		// TODO Auto-generated method stub
		Toast.makeText(RenRenShare.this, "取消登录", 4000).show();
	}

	@Override
	public void onCancelAuth(Bundle values)
	{
		// TODO Auto-generated method stub
		Toast.makeText(RenRenShare.this, "取消授权", 4000).show();
	}

}