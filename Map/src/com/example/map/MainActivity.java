package com.example.map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * �ҵİٶȵ�ͼ��ҳ
 *
 */
public class MainActivity extends Activity implements OnClickListener {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private ImageButton ib_large,ib_small,ib_mode,ib_loc,ib_traffic;
    //ģʽ�л�������ģʽ
    private boolean modeFlag = true;
    //��ǰ��ͼ���ż���
    private float zoomLevel; 
    //��λ���
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    //�Ƿ��һ�ζ�λ������ǵ�һ�ζ�λ�Ļ�Ҫ���Լ���λ����ʾ�ڵ�ͼ �м�
    private boolean isFirstLocation = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
        //ע��÷���Ҫ��setContentView����֮ǰʵ��  
        SDKInitializer.initialize(getApplicationContext());  
        
        setContentView(R.layout.activity_main);  
        //��ʼ���ؼ�
        initView();
        //��ʼ����ͼ
        initMap();
        //��λ
        initLocation();
    }  
    private void initMap() {
        //��ȡ��ͼ�ؼ�����  
        mMapView = (MapView) findViewById(R.id.bmapView);
        // ����ʾ���ű�����
        mMapView.showZoomControls(false);
        // ����ʾ�ٶȵ�ͼLogo
        mMapView.removeViewAt(1);
        //�ٶȵ�ͼ
        mBaiduMap = mMapView.getMap();
        // �ı��ͼ״̬
        MapStatus mMapStatus = new MapStatus.Builder().zoom(15).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        //���õ�ͼ״̬�ı������
        mBaiduMap.setOnMapStatusChangeListener(new OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus arg0) {
            }
            @Override
            public void onMapStatusChangeFinish(MapStatus arg0) {
            }
            @Override
            public void onMapStatusChange(MapStatus arg0) {
                //����ͼ״̬�ı��ʱ�򣬻�ȡ�Ŵ󼶱�
                zoomLevel = arg0.zoom;
            }
			@Override
			public void onMapStatusChangeStart(MapStatus arg0, int arg1) {
				// TODO Auto-generated method stub
				zoomLevel = arg0.zoom;
			}
			
        });
    }
    private void initLocation() {
        //��λ�ͻ��˵�����
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        //ע�����
        mLocationClient.registerLocationListener(mLocationListener);
        //���ö�λ
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");//��������
        option.setIsNeedAddress(true);//��ѡ�������Ƿ���Ҫ��ַ��Ϣ��Ĭ�ϲ���Ҫ
        option.setOpenGps(true);//��Gps
        option.setScanSpan(1000);//1000���붨λһ��
        option.setIsNeedLocationPoiList(true);//��ѡ��Ĭ��false�������Ƿ���ҪPOI�����������BDLocation.getPoiList��õ�
        mLocationClient.setLocOption(option);
    }
    private void initView() {
        //��ͼ���ư�ť
        ib_large = (ImageButton)findViewById(R.id.ib_large);
        ib_large.setOnClickListener(this);
        ib_small = (ImageButton)findViewById(R.id.ib_small);
        ib_small.setOnClickListener(this);
        ib_mode = (ImageButton)findViewById(R.id.ib_mode);
        ib_mode.setOnClickListener(this);
        ib_loc = (ImageButton)findViewById(R.id.ib_loc);
        ib_loc.setOnClickListener(this);
        ib_traffic = (ImageButton)findViewById(R.id.ib_traffic);
        ib_traffic.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.ib_large:
            if (zoomLevel < 18) {
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomIn());
                ib_small.setEnabled(true);
            } else {
                showInfo("�Ѿ�������󣬿ɼ�����������");
                ib_large.setEnabled(false);
            }
            break;
        case R.id.ib_small:
            if (zoomLevel > 6) {
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomOut());
                ib_large.setEnabled(true);
            } else {
                ib_small.setEnabled(false);
                showInfo("�Ѿ�������С���ɼ�����������");
            }
            break;
        case R.id.ib_mode://����ģʽ����ͨģʽ
            if(modeFlag){
                modeFlag = false;
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                showInfo("��������ģʽ");
            }else{
                modeFlag = true;
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                showInfo("������ͨģʽ");
            }
            break;
        case R.id.ib_loc:
            isFirstLocation = true;
            showInfo("�����Լ�λ��");
            break;
        case R.id.ib_traffic://�Ƿ�����ͨͼ
            if(mBaiduMap.isTrafficEnabled()){
                mBaiduMap.setTrafficEnabled(false);
                ib_traffic.setBackgroundResource(R.drawable.a);
                showInfo("�ر�ʵʱ��ͨͼ");
            }else{
                mBaiduMap.setTrafficEnabled(true);
                ib_traffic.setBackgroundResource(R.drawable.a);
                showInfo("����ʵʱ��ͨͼ");
            }
            break;
        default:
            break;
        }
    }  
    @Override
    protected void onStart() {
        super.onStart();
        //������λ
        mBaiduMap.setMyLocationEnabled(true);
        if(!mLocationClient.isStarted()){
            mLocationClient.start();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        //�رն�λ
        mBaiduMap.setMyLocationEnabled(false);
        if(mLocationClient.isStarted()){
            mLocationClient.stop();
        }
    }
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onResume();  
    }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onPause();  
    }
    //��ʾ��Ϣ
    private void showInfo(String str){
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }
    //�Զ���Ķ�λ����
    private class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location) {
            //����ȡ��location��Ϣ���ٶ�map
            MyLocationData data = new MyLocationData.Builder()  
                    .accuracy(location.getRadius())  
                    // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360  
                    .direction(100)
                    .latitude(location.getLatitude())  
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(data);
            if(isFirstLocation){
                //��ȡ��γ��
                LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                //mBaiduMap.setMapStatus(status);//ֱ�ӵ��м�
                mBaiduMap.animateMapStatus(status);//�����ķ�ʽ���м�
                isFirstLocation = false;
                showInfo("λ�ã�" + location.getAddrStr());
            }
        }

    }
}