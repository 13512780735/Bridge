package com.bridgesafe.bridge.ui;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.ui.main.BridgeDetailDialogFragment;
import com.bridgesafe.bridge.ui.main.RoadActivity;
import com.bridgesafe.bridge.ui.main.TideActivity;
import com.bridgesafe.bridge.ui.main.WaterActivity;
import com.bridgesafe.bridge.ui.main.WeatherActivity;
import com.bridgesafe.bridge.util.AppManager;
import com.bridgesafe.bridge.util.StatusBarUtil;
import com.elvishew.xlog.XLog;
import com.lxj.xpopup.XPopup;
import com.unistrong.yang.zb_permission.ZbPermission;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.mapview)
    MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private double lat;
    private double lon;
    private boolean isFirstLocation = true;
    private BridgeDetailDialogFragment dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTranslucent(this);
        StatusBarUtil.hideFakeStatusBarView(this);
        openPermission();
        //初始化百度地图相关
        initBaiduMap();
    }

    private final int REQUEST_CONTACT = 50;

    private void openPermission() {
        XLog.e("");
        ZbPermission.with(MainActivity.this)
                .addRequestCode(REQUEST_CONTACT)
                .permissions(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .request(new ZbPermission.ZbPermissionCallback() {

                    @Override
                    public void permissionSuccess(int requestCode) {

                        Toast.makeText(MainActivity.this, "授予權限成功: " + requestCode, Toast.LENGTH_SHORT).show();
//                        isSuccess = true;
//                        SharedPreferencesUtils.put(mContext, "isSuccess", true);

                    }

                    @Override
                    public void permissionFail(int requestCode) {

                        Toast.makeText(MainActivity.this, "授予權限失敗: " + requestCode, Toast.LENGTH_SHORT).show();

                    }

                });
    }

    private void initBaiduMap() {
        //获取地图控件引用
        mBaiduMap = mMapView.getMap();
        //普通地图
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        // mLocationClient.registerLocationListener(myListener);

        //配置定位参数
        initLocation();
        //开始定位
        mLocationClient.start();
        //图片点击事件，回到定位点
        mLocationClient.requestLocation();
        mLocationClient.registerLocationListener(myListener);
    }

    /**
     * 配置定位参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * 实现定位监听 位置一旦有所改变就会调用这个方法
     * 可以在这个方法里面获取到定位之后获取到的一系列数据
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
//Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
//运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            lat = location.getLatitude();
            lon = location.getLongitude();
//这个判断是为了防止每次定位都重新设置中心点和marker
            if (isFirstLocation) {
                isFirstLocation = false;
                setMarker();
                setUserMapCenter();
            }
            Log.v("pcw", "lat : " + lat + " lon : " + lon);
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }

    /**
     * 添加marker
     */
    private void setMarker() {
        Log.v("pcw", "setMarker : lat : " + lat + " lon : " + lon);
        //定义Maker坐标点
        LatLng point = new LatLng(lat, lon);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    /**
     * 设置中心点
     */
    private void setUserMapCenter() {
        Log.v("pcw", "setUserMapCenter : lat : " + lat + " lon : " + lon);
        LatLng cenpt = new LatLng(lat, lon);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @OnClick({R.id.iv_member, R.id.rl_water, R.id.rl_weather, R.id.rl_tide, R.id.iv_location, R.id.rl_road, R.id.iv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_member:
                new XPopup.Builder(mContext)
//                        .asCustom(new CustomDrawerPopupView(getContext()))
                        .asCustom(new PagerDrawerPopup(mContext))
//                        .asCustom(new ListDrawerPopupView(getContext()))
                        .show();
                break;
            case R.id.rl_water:
                toActivity(WaterActivity.class);
                break;
            case R.id.rl_weather:
                toActivity(WeatherActivity.class);
                break;
            case R.id.rl_tide:
                toActivity(TideActivity.class);
                break;
            case R.id.iv_location:
                mBaiduMap.setMyLocationEnabled(true);
                isFirstLocation = true;
                mLocationClient.start();
                break;
            case R.id.rl_road:
                toActivity(RoadActivity.class);
                break;
            case R.id.iv_search:
                dialog = new BridgeDetailDialogFragment();
                dialog.show(getFragmentManager(), "tag");
                break;
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                //finish();
                AppManager.getAppManager().finishAllActivity();
                // System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
