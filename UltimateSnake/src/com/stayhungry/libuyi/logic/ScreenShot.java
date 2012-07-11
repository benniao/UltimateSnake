package com.stayhungry.libuyi.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.os.Environment;
import android.text.format.Time;
import android.view.View;

public class ScreenShot
{
	public static String formatFileName(Time time)
	{
		String fileName;
		time=new Time("GMT+8");
		time.setToNow();//获得当前时间
		fileName = time.year + "_" + time.month + "_" + time.monthDay + "_"
				+ time.hour + "_" + time.minute + "_" + time.second + ".png";// 格式化文件名
		return fileName;
	}

	public static boolean screenShot(String fileName, View view)
	{
		// Context context = getApplicationContext();
		// View rootView = findViewById(R.id.rootLayout);
		Bitmap newb = Bitmap.createBitmap(320, 480, Config.ARGB_8888);
		Canvas canvas = new Canvas(newb);
		view.draw(canvas);
		File file = new File(Environment.getExternalStorageDirectory() + "/"
				+ fileName);
		FileOutputStream f = null;
		try
		{

			f = new FileOutputStream(file);

		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean shoot = newb.compress(Bitmap.CompressFormat.PNG, 100, f);
		return shoot;
	}

}
