package jxnu.edu.cn.x3321.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import jxnu.edu.cn.x3321.MainActivity;
import jxnu.edu.cn.x3321.R;

public class GraidAdapter extends BaseAdapter {

	Context context;
	String[]button;
	String[]text;
	public GraidAdapter(Context context, String[] button, String[] text) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.button=button;
		this.text=text;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return button.length;
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return text[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)LinearLayout.inflate(context, R.layout.grid_item,null);
		Button bt=(Button)ll.findViewById(R.id.bt_grid);
		bt.setText(button[position]);
		final TextView tv=(TextView)ll.findViewById(R.id.tv_num1);
		tv.setText(text[position]);
		bt.setOnClickListener(new View.OnClickListener() {
			
		
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, tv.getText().toString(),Toast.LENGTH_LONG).show();
			}
		});
		
		return ll;
	}

}
