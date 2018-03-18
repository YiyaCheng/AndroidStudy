package jxnu.edu.cn.x3321.adapter;

import jxnu.edu.cn.x3321.Player;
import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.mylistview;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class ExpandAdapter extends BaseExpandableListAdapter {

	Context context;
	int[] rankImages;
	String[]scores;
	/*String []scores1=new String[]{"2","1","1","0"};
	String []scores2=new String[]{"0","1","1","2"};
	String []scores3=new String[]{"1","1","1","1"};
	String []scores4=new String[]{"4","3","5","2"};
	String []scores5=new String[]{"5","3","3","3"};
	String []scores6=new String[]{"6","4","4","2"};*/

	String[][]title=new String[][]{
			{"Czech 2:1 Greece","Russian 4：1 Czech"},
	        {"Netherlands 3:1 Denmark","Portual 4：1 Germany"},
	        {"Italy 1:2 Ireland","Croatia 2：1 Spain"},
	        {"England 2:1 France","Sweden 0：1 Ukraine"}};
	int[][]images=new int[][]{
		   {R.drawable.goals,R.drawable.substs,R.drawable.goals,
			R.drawable.substs,R.drawable.yells,R.drawable.yells,
			R.drawable.yells,R.drawable.substs,R.drawable.substs,
			R.drawable.substs,R.drawable.substs,R.drawable.goals},
		       {R.drawable.goals,R.drawable.substs,R.drawable.goals,
				R.drawable.substs,R.drawable.substs,R.drawable.goals,
				R.drawable.substs,R.drawable.substs,R.drawable.goals,
				R.drawable.substs,R.drawable.goals,R.drawable.substs}
	};
	String names[][]=new String[][] {
		{"Petr Jiracek","Chalkias","Vaclav Pilar",
			"Sifakis","Tomas Rosicky","Torossidis",
		"Petr Jiracek","Fotakis","Tomas Rosicky",
			"Fanis Gekas","Daniel Kolar","Fanis Gekas"},
		{"Alan Dzageoev","Jan Rezek","Roman Shirokov",
				"Hubschman","Kerzhakov","Vaclav Pilar",
		"Pavlyuchenko","Petr Jiracek","Alan Dzagoev",
				"David Limbersky","Pavlyuchenko","Milan Baros"}
	
	};
	int[][]nums=new int[][]{
		{3,6,27,36,46,46,23,23,34,46,46,53},
		{13,46,24,46,73,52,73,76,79,76,82,85}
	};      
	
	public ExpandAdapter(Context context,int []rankImages,String[]scores)
	{
		this.context=context;
		this.rankImages=rankImages;
		this.scores=scores;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return title[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}
 
	
         

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		LinearLayout ll= (LinearLayout) LinearLayout.inflate(context, R.layout.extend_child, null); 
		
		TextView tvscore=(TextView)ll.findViewById(R.id.tv_score);
		tvscore.setText(title[groupPosition][childPosition]);
		
		//GridView grid=new GridView(context);这种方式错了！！！！！已经重写了方法
		mylistview grid=new mylistview(context);
		grid.setNumColumns(2);
		
		PlayerAdapter playeradapter=new PlayerAdapter(context,images[childPosition],names[childPosition],nums[childPosition]);
	    
	    grid.setAdapter(playeradapter);
		
	    ll.addView(grid);
	    	
		return ll;
	}

    
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return title[groupPosition].length;
		
	}
    
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return rankImages[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return rankImages.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.extend_group,null);
		ImageView tvarrow=(ImageView)ll.findViewById(R.id.tv_arrow);
		
		
		TextView tvrank=(TextView)ll.findViewById(R.id.tv_rank);
		String []rank=new String[]{"1","2","3","4"};
		tvrank.setText(rank[groupPosition]);
		
		
		ImageView countryflag=(ImageView)ll.findViewById(R.id.countryflag);
		countryflag.setImageResource(rankImages[groupPosition]);
		
		TextView tvscore1=(TextView)ll.findViewById(R.id.tv_score1);
		tvscore1.setText(scores[groupPosition]);
		/*TextView tvscore1=(TextView)ll.findViewById(R.id.tv_score1);
		tvscore1.setText(scores1[groupPosition]);
		
		TextView tvscore2=(TextView)ll.findViewById(R.id.tv_score2);
		tvscore2.setText(scores2[groupPosition]);
		
		TextView tvscore3=(TextView)ll.findViewById(R.id.tv_score3);
		tvscore3.setText(scores3[groupPosition]);
		
		TextView tvscore4=(TextView)ll.findViewById(R.id.tv_score4);
		tvscore4.setText(scores4[groupPosition]);
		
		TextView tvscore5=(TextView)ll.findViewById(R.id.tv_score5);
		tvscore5.setText(scores5[groupPosition]);
		
		TextView tvscore6=(TextView)ll.findViewById(R.id.tv_score6);
		tvscore6.setText(scores6[groupPosition]);*/
		return ll;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
