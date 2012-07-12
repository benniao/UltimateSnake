package com.stayhungry.kuanglizhong.logic;

import com.stayhungry.libuyi.logic.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Query {
	static SQLiteDatabase dbQuery;
	static ListView listView;
	public static void query(Activity activity){
		dbQuery=SQLiteDatabase.openOrCreateDatabase("/data/data/com.Query/my.db5", null);
        //dbQuery=SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/my.db5",null)       
        listView=(ListView)activity.findViewById(R.id.listView1);
        
       Cursor cursor = dbQuery.query("hero", null, null, null, null, null, "score desc",null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				activity , R.layout.line, cursor 
			, new String[]{"name" , "score"}
			, new int[]{R.id.my_title , R.id.my_content});
		listView.setAdapter(adapter);
	}

}
