package com.example.walk01;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {

	private TextView tv_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_content_fragment, container, false);
        tv_content = (TextView) view.findViewById(R.id.main_content);
        String text = getArguments().getString("text");
        tv_content.setText(text);
        return view;
    }

}
