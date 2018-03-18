package jxnu.edu.cn.edu;

import jxnu.edu.cn.edu.adapter.MyExpandAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
    
	ExpandableListView expand;
	String[] group=new String[]{"ͬѧ[3]","��ʦ[3]","����[2]","����[3]","����[3]"};
	String[][] grouplist=new String[][]{
			{"ͬѧ1","ͬѧ2","ͬѧ3"},
			{"��ʦ1","��ʦ2","��ʦ3"},
			{"����1","����2"},
			{"����1","����2","����3"},
			{"����1","����2","����3"}
	};
	String[][]signs=new String[][]{{"�����������ĵ��¸�","����˵�ó��ڵĵ�������","���ҹ¾������"},
		{"���Ҳ�����ѹ���ʻ�","����������çײ��������","����һ������������"},
		{"����Ц�ղ����紺��","����ɱ��ӹµ���黳"},
		{"�������ݵı�����޺�","������Ȼ�Ķ�������","���Ұ��ʵ��峿�����"},
       {"������������","�����������","���ܲ��˲��ʲ�˵,Ҳ����"}};
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
