package com.bridgesafe.bridge.ui.login;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.MainActivity;
import com.bridgesafe.bridge.ui.adapter.LoginRegisterTabAdapter;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.ui.fragment.LoginFragment;
import com.bridgesafe.bridge.ui.fragment.RegisterFragment;
import com.bridgesafe.bridge.view.NoScrollViewPager;
import com.elvishew.xlog.XLog;
import com.unistrong.yang.zb_permission.ZbPermission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.sliding_tabs)
    TabLayout mTabLayout;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatas = new ArrayList<>(Arrays.asList("登录", "注册"));
        openPermission();
        initLocationOption();
        initUI();
    }

    private void initUI() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(viewpager);
        List<Fragment> mfragments = new ArrayList<Fragment>();
        mfragments.add(new LoginFragment());
        mfragments.add(new RegisterFragment());
        viewpager.setAdapter(new LoginRegisterTabAdapter(getSupportFragmentManager(), mfragments, mDatas));
        viewpager.setCurrentItem(0);
    }


    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
    }

    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            //获取纬度信息
            double latitude = location.getLatitude();
            //获取经度信息
            double longitude = location.getLongitude();
            //获取定位精度，默认值为0.0f
            float radius = location.getRadius();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            int errorCode = location.getLocType();

        }
    }

    private final int REQUEST_CONTACT = 50;

    private void openPermission() {
        XLog.e("");
        ZbPermission.with(LoginActivity.this)
                .addRequestCode(REQUEST_CONTACT)
                .permissions(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .request(new ZbPermission.ZbPermissionCallback() {

                    @Override
                    public void permissionSuccess(int requestCode) {

                        Toast.makeText(LoginActivity.this, "授予權限成功: " + requestCode, Toast.LENGTH_SHORT).show();
//                        isSuccess = true;
//                        SharedPreferencesUtils.put(mContext, "isSuccess", true);

                    }

                    @Override
                    public void permissionFail(int requestCode) {

                        Toast.makeText(LoginActivity.this, "授予權限失敗: " + requestCode, Toast.LENGTH_SHORT).show();

                    }

                });
    }

}
