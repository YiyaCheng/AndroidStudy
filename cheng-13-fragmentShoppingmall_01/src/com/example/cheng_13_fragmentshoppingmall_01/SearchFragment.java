package com.example.cheng_13_fragmentshoppingmall_01;

import com.example.cheng_13_fragmentshoppingmall_01.adapter.detail2Adapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class SearchFragment extends Fragment {

	Data data=new Data();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, Bundle savedInstanceState)
	{
		final View view=inflater.inflate(R.layout.fragment_search, container, false);
		Button bt=(Button)view.findViewById(R.id.searchImage);
		
		final AutoCompleteTextView ac=(AutoCompleteTextView)view.findViewById(R.id.ac);
		final ListView list2=(ListView)view.findViewById(R.id.searchlist);
		
		ArrayAdapter<String> aa=new ArrayAdapter<String>(
				getActivity(),R.layout.style_autocompletetextview,R.id.autoComplete
				,data.textTitle);
		ac.setAdapter(aa);
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str=ac.getText().toString().trim();
				for(int i=0;i<data.images.length;i++)
				if(str.equals(data.textTitle[i]))
				{
					detail2Adapter la2=new detail2Adapter(getActivity(),i,view);
					list2.setAdapter(la2);
					ac.setText("");
				}
			}
		});
		return view;
	}
}
