package jxnu.edu.cn.edu;

import jxnu.edu.cn.edu.adapter.MyExpandAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
    
	ExpandableListView expand;
	String[] group=new String[]{"同学[3]","老师[3]","家人[2]","朋友[3]","室友[3]"};
	String[][] grouplist=new String[][]{
			{"同学1","同学2","同学3"},
			{"老师1","老师2","老师3"},
			{"家人1","家人2"},
			{"朋友1","朋友2","朋友3"},
			{"室友1","室友2","室友3"}
	};
	String[][]signs=new String[][]{{"借我亡命天涯的勇敢","借我说得出口的旦旦誓言","借我孤绝如初见"},
		{"借我不惧碾压的鲜活","借我生猛与莽撞不问明天","借我一束光照亮黯淡"},
		{"借我笑颜灿烂如春天","借我杀死庸碌的情怀"},
		{"借我纵容的悲怆与哭喊","借我怦然心动如往昔","借我安适的清晨与傍晚"},
       {"静看光阴荏苒","借我喑哑无言","不管不顾不问不说,也不念"}};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		MyExpandAdapter expandAdpter=new MyExpandAdapter(getApplication(),group,grouplist,signs);
		expand.setAdapter(expandAdpter);
	}

	private void init() {
		// TODO Auto-generated method stub
		expand=(ExpandableListView)this.findViewById(R.id.expand);
		//expand.setDivider(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
