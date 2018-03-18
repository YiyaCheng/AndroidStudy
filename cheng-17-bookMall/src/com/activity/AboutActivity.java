package com.activity;

import android.os.Bundle;

import com.example.cheng_17_bookmall.R;
import com.example.cheng_17_bookmall.R.layout;
import com.example.cheng_17_bookmall.R.menu;

import android.app.Activity;
import android.view.Menu;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

}
