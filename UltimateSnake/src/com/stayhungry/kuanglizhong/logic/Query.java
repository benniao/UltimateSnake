package com.stayhungry.kuanglizhong.logic;

import com.stayhungry.libuyi.logic.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Query extends Activity {
/**
 * @uml.property  name="dbQuery"
 * @uml.associationEnd  
 */
SQLiteDatabase dbQuery;
	/**
	 * @uml.property  name="listView"
	 * @uml.associationEnd  
	 */
	ListView listView;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
       dbQuery=SQLiteDatabase.openOrCreateDatabase("/data/data/com.stayhungry.libuyi/my.db3", null);
        //dbQuery=SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/my.db3",null);
        
        listView=(ListView)findViewById(R.id.listView1);
        Cursor cursor = dbQuery.query("hero", null, null, null, null, null, "score desc",null);
		inflateList(cursor);
	}
	private void inflateList(Cursor cursor)
	{
		//���SimpleCursorAdapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				Query.this , R.layout.line, cursor 
			, new String[]{"name" , "score"}
			, new int[]{R.id.my_title , R.id.my_content});
		//��ʾ���
		listView.setAdapter(adapter);
	}
	public void onDestroy()
	 {
		 super.onDestroy();
		 if(dbQuery!=null && dbQuery.isOpen())
		 {
			 dbQuery.close();
		 }
	 }
}
