package com.stayhungry.nieyajie.ui;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.stayhungry.libuyi.logic.R;

public class OptionActivity extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost tabHost = getTabHost();
		// 设置使用TabHost布局
		LayoutInflater.from(OptionActivity.this).inflate(R.layout.option,
				tabHost.getTabContentView(), true);
		// 添加第一个标签页
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("游戏介绍")
				.setContent(R.id.tab001));
		// 添加第二个标签页
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("关于我们")
				.setContent(R.id.tab002));
	
	}
}
