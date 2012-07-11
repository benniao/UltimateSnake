package com.stayhungry.nieyajie.ui;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.stayhungry.libuyi.logic.R;

public class Achievement extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost tabHost = getTabHost();
		// 设置使用TabHost布局
		LayoutInflater.from(Achievement.this).inflate(R.layout.achievement,
				tabHost.getTabContentView(), true);
		// 添加第一个标签页
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("经典模式")
				.setContent(R.id.tab01));
		// 添加第二个标签页
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("竞技模式")
				.setContent(R.id.tab02));
		// 添加第三个标签页
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("ABC模式")
				.setContent(R.id.tab03));
	}
}
