package org.crazyit.app;

import org.crazyit.app.model.Book;
import org.crazyit.app.model.BookContent;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Description:
 * <br/>website: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class BookListFragment extends ListFragment
{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 为该ListFragment设置Adapter
		this.setListAdapter(new ArrayAdapter<Book>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, BookContent.ITEMS));
	}
	
	
	// 当用户点击某列表项时激发该回调方法
	@Override
	public void onListItemClick(ListView listView
		, View view, int position, long id)
	{
		super.onListItemClick(listView, view, position, id);
		// 创建Bundle，准备向Fragment传入参数
		Bundle arguments = new Bundle();
		arguments.putInt("book_id", position);
		// 创建Bundle，准备向Fragment传入参数);
		// 创建Bundle，准备向Fragment传入参数);
		// 创建BookDetailFragment对象
		BookDetailFragment fragment = new BookDetailFragment();
		// 向Fragment传入参数
		fragment.setArguments(arguments);
		// 使用fragment替换book_detail_container容器当前显示的Fragment
		getFragmentManager().beginTransaction()
			.replace(R.id.book_detail_container, fragment)
			.commit();  //①
	}

	public void setActivateOnItemClick(boolean activateOnItemClick)
	{
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}
}
