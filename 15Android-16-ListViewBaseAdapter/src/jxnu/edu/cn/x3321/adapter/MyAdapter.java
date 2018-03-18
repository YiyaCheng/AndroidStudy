package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	ArrayList<String> al=new ArrayList<String>();
	Context context;

	public MyAdapter(Context context, ArrayList<String> al) {
		// TODO Auto-generated constructor stub
		this.al=al;
		this.context=context;
	}
  //�õ�Ҫ��ʾ���ݵ�����
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.al.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.al.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
    //����ÿ�����ݵ���ʽ,���ᱻ�������ɴ�
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//����table.xml��ʽ��ÿ������
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.table, null);
		
		TextView tvUserName=(TextView)ll.findViewById(R.id.tv_userName);
		tvUserName.setText(al.get(position));
		
		TextView tvTel=(TextView)ll.findViewById(R.id.tv_tel);
		tvTel.setText("1390000000"+position);
		
		return ll;
	}

}
