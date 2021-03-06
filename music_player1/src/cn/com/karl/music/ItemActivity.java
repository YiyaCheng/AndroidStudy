package cn.com.karl.music;


import java.util.ArrayList;
import java.util.List;

import cn.com.karl.adapter.MusicListAdapter;
import cn.com.karl.domain.Music;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//音乐列表
public class ItemActivity extends Activity {
	private ListView listview;
	private List<Music> lists;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		
		listview=(ListView) this.findViewById(R.id.music_item);
		
		setListData();
	}
	
	 private void setListData(){
	    	lists=new ArrayList<Music>();
	    	
	    	ContentResolver cr=getApplication().getContentResolver();
	    	if(cr==null){
	    		return ;
	    	}
	    	//获取所有歌曲
	    	Cursor cursor=cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
	    			null, null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
	    	if(null==cursor){
	    		return ;
	    	}
	    	if(cursor.moveToFirst()){
	    		do{
	    			Music m=new Music();
	    	     String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
	    	     String singer=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
	    	     String album=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
	    	     long size=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
	    	     long time=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
	    	     String url=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
	    	     m.setTitle(title);
	    	     m.setSinger(singer);
	    	     m.setAlbum(album);
	    	     m.setSize(size);
	    	     m.setTime(time);
	    	     m.setUrl(url);
	    	     lists.add(m);
	    		}while(cursor.moveToNext());
	    	}
	    	
	    	MusicItemAdapter adapter=new MusicItemAdapter();
	    	listview.setAdapter(adapter);
	    	
	    	listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					// Toast.makeText(ItemActivity.this, arg2, Toast.LENGTH_SHORT).show();
					 
					 Intent intent=new Intent(ItemActivity.this,MusicActivity.class);
					 intent.putExtra("id", arg2);
					 startActivity(intent);
				}
			});
	    }
	    private class MusicItemAdapter extends BaseAdapter{
	       
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return lists.size();
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return lists.get(arg0);
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if(convertView==null){
					convertView=LayoutInflater.from(getApplicationContext()).inflate(R.layout.musiclist, null);
				}
				Music m=lists.get(position);
				TextView textName=(TextView) convertView.findViewById(R.id.music_list_singer);
				textName.setText(m.getSinger());
				TextView music_singer=(TextView) convertView.findViewById(R.id.music_list_name);
				music_singer.setText(m.getTitle());
				TextView music_time=(TextView) convertView.findViewById(R.id.music_time);
				music_time.setText(toTime((int)m.getTime()));
				
				ImageView img = (ImageView)convertView.findViewById(R.id.item_image);
				img.setBackgroundResource(R.drawable.item);
				return convertView;
			}
	    	
	    }
	    /**
		 * 时间格式转换
		 * @param time
		 * @return
		 */
		public String toTime(int time) {
	        
			time /= 1000;
			int minute = time / 60;
			int hour = minute / 60;
			int second = time % 60;
			minute %= 60;
			return String.format("%02d:%02d", minute, second);
		}
}
