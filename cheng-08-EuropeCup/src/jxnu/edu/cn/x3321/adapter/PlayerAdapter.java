package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import jxnu.edu.cn.x3321.Player;
import jxnu.edu.cn.x3321.R;

public class PlayerAdapter extends BaseAdapter {

	Context context;
	int[]images;
	String[]names;
	int[]nums;
	/*int[][]image1=new int[][]{{R.drawable.goals,R.drawable.goals,R.drawable.yells,
			R.drawable.yells,R.drawable.substs,R.drawable.substs},
			{R.drawable.substs,R.drawable.substs,R.drawable.yells,
			R.drawable.substs,R.drawable.substs,R.drawable.goals}};

	String[][]name1=new String[][]{{"Petr Jiracek","Vaclav Pilar","Tomas Rosicky",
			"Petr Jiracek","Tomas Rosicky","Daniel Kolar"},
			{"Chalkias","Sifakis","Torossidis",
			"Fotakis","Fanis Gekas","Fanis Gekas"
			}};
	int[][]num1=new int[][]{{3,6,27,36,46,46},]{23,23,34,46,46,53}};*/

	ArrayList<Player> player=new ArrayList<Player>();
	public PlayerAdapter(Context context,int images[],String names[],int nums[])
    //public PlayerAdapter(Context context,int images[],String names[],String nums[])
	{
		this.context=context;
		this.images=images;
		this.names=names;
		this.nums=nums;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return this.player.size();
		return images.length;
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		//return this.player.get(position);
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.gridview_player, null);
		 
		 
		ImageView img=(ImageView)ll.findViewById(R.id.grid_img);
		img.setImageResource(images[position]);
		
		TextView tv=(TextView)ll.findViewById(R.id.grid_playername);
		tv.setText(names[position]);
		
		TextView tv2=(TextView)ll.findViewById(R.id.grid_playerscore);
		tv2.setText(String.valueOf(nums[position]));
		 
		return ll;
		/*LinearLayout linear=(LinearLayout)View.inflate(context, R.layout.listview_player,null);
		
       
		ImageView image1=(ImageView)linear.findViewById(R.id.imag1);
		image1.setImageResource(images[position][0]);
		TextView tvname1=(TextView)linear.findViewById(R.id.tv_name1);
		tvname1.setText(names[position][0]);
		TextView tvgetscore1=(TextView)linear.findViewById(R.id.tv_getscore1);
		tvgetscore1.setText(nums[position][0]);
		
		ImageView image2=(ImageView)linear.findViewById(R.id.imag2);
		image2.setImageResource(images[position][1]);
		TextView tvname2=(TextView)linear.findViewById(R.id.tv_name2);
		tvname2.setText(names[position][1]);
		TextView tvgetscore2=(TextView)linear.findViewById(R.id.tv_getscore2);
		tvgetscore2.setText(nums[position][1]);
		
		return linear;*/
	}

}
