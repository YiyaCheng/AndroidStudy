package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxnu.edu.cn.x3321.adapter.CountryAdapter;
import jxnu.edu.cn.x3321.adapter.ExpandAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	ListView lvCountry;
	ExpandableListView expand;
	int[] rankImages = new int[] { R.drawable.czech, R.drawable.greece,
			R.drawable.russian, R.drawable.poland };
	
	String[] scores = new String[] { "2    0    1    4    5    6",
			"1    1    1    3    3    4", "1    1    1    5    3    4",
			"0    2    1    2    3    2" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

		final int[][] images = new int[][] {
				{ R.drawable.russian, R.drawable.poland, R.drawable.greece,
						R.drawable.czech },
				{ R.drawable.netherlands, R.drawable.germany,
						R.drawable.portugal, R.drawable.denmark },
				{ R.drawable.spain, R.drawable.italy, R.drawable.ireland,
						R.drawable.croatia },
				{ R.drawable.england, R.drawable.france, R.drawable.sweden,
						R.drawable.ukraine } };
		final String[][] names = new String[][] {
				{ "Russian", "Poland", "Greece", "Czech" },
				{ "Netherlands", "Germany", "Portugal", "Denmark" },
				{ "Spain", "Italy", "Ireland", "Croatia" },
				{ "England", "France", "Sweden", "Ukraine" } };

		CountryAdapter ct = new CountryAdapter(getApplicationContext(), images,
				names);
		lvCountry.setAdapter(ct);
		lvCountry.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				AlertDialog ad = createDialog();
				ad.show();
			}
		});
	}

	protected AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

		builder.setIcon(R.drawable.ic_menu_largetiles);
		builder.setTitle("Welcome to Europe Cup2012");
		LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(
				R.layout.dialog, null);
		builder.setView(ll);
		expand = (ExpandableListView) ll.findViewById(R.id.expandlist);

		ExpandAdapter expandadapter = new ExpandAdapter(getApplication(),
				rankImages, scores);

		expand.setAdapter(expandadapter);
		//expand.setScrollbarFadingEnabled(false);
		builder.setPositiveButton("OK", null);
		/*
		 * ad.setPositiveButton("OK", new DialogInterface.OnClickListener(){
		 * public void onClick(DialogInterface dialog,int which){
		 * dialog.dismiss(); } });
		 */
		alertDialog = builder.create();
		return alertDialog;
	}

	private void init() {
		lvCountry = (ListView) this.findViewById(R.id.lv_country);
		expand=(ExpandableListView)this.findViewById(R.id.expandlist);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
