/*
 * Copyright 2011 Sina.
 *
 * Licensed under the Apache License and Weibo License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.open.weibo.com
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stayhungry.libuyi.logic;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.Toast;

import com.stayhungry.libuyi.logic.R;
import com.weibo.net.AccessToken;
import com.weibo.net.DialogError;
import com.weibo.net.ShareActivity;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboDialogListener;
import com.weibo.net.WeiboException;

/**
 * Sample code for testing weibo APIs.
 * 
 * @author ZhangJie (zhangjie2@staff.sina.com.cn)
 */

public class XinLangShare extends Activity
{
	/**
	 * Called when the activity is first created.
	 * @uml.property  name="share"
	 * @uml.associationEnd  
	 */
	Button share;
	/**
	 * @uml.property  name="fileName"
	 */
	String fileName;

	private static final String CONSUMER_KEY = "1980605877";// 鏇挎崲涓哄紑鍙戣�鐨刟ppkey锛屼緥濡�1646212960";
	private static final String CONSUMER_SECRET = "db6a936ecc133b5774adbc2afaf16cfa";// 鏇挎崲涓哄紑鍙戣�鐨刟ppkey锛屼緥濡�94098772160b6f8ffc1315374d8861f9";

	/**
	 * @uml.property  name="username"
	 */
	private String username = "";
	/**
	 * @uml.property  name="password"
	 */
	private String password = "";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		Intent intent=getIntent();
		fileName=intent.getStringExtra("fileName");//得到截图的文件名
		share = (Button) this.findViewById(R.id.share);
		share.setOnClickListener(new OnClickListener()//分享到新浪
		{
			@Override
			public void onClick(View v)
			{

					Weibo weibo = Weibo.getInstance();
					weibo.setupConsumerConfig(CONSUMER_KEY, CONSUMER_SECRET);
					weibo.setRedirectUrl("http://www.sina.com");
					weibo.authorize(XinLangShare.this,new AuthDialogListener());
			}
		});
	}

	public void onResume()
	{
		super.onResume();
	}

	class AuthDialogListener implements WeiboDialogListener
	{

		@Override
		public void onComplete(Bundle values)
		{
			String token = values.getString("access_token");
			String expires_in = values.getString("expires_in");
			AccessToken accessToken = new AccessToken(token, CONSUMER_SECRET);
			accessToken.setExpiresIn(expires_in);
			Weibo.getInstance().setAccessToken(accessToken);
			File file = Environment.getExternalStorageDirectory();
			String sdPath = file.getAbsolutePath();
			// 请保证SD卡根目录下有这张图片文件
			String picPath = sdPath + "/" + fileName;
			File picFile = new File(picPath);
			if (!picFile.exists())
			{
				Toast.makeText(XinLangShare.this, "图片" + picPath + "不存在！",
						Toast.LENGTH_SHORT).show();
				picPath = null;
			}
			try
			{
				share2weibo("UltimateSnake", picPath);
				Intent i = new Intent(XinLangShare.this,
						ShareActivity.class);
				XinLangShare.this.startActivity(i);

			}
			catch (WeiboException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{

			}

		}
		private void share2weibo(String content, String picPath)
				throws WeiboException
		{
			Weibo weibo = Weibo.getInstance();
			weibo.share2weibo(XinLangShare.this, weibo.getAccessToken().getToken(), weibo
					.getAccessToken().getSecret(), content, picPath);
		}
		@Override
		public void onError(DialogError e)
		{
			Toast.makeText(getApplicationContext(),
					"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onCancel()
		{
			Toast.makeText(getApplicationContext(), "Auth cancel",
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e)
		{
			Toast.makeText(getApplicationContext(),
					"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

	}

}