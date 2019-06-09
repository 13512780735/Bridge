package com.bridgesafe.bridge.app;

import android.app.Application;
import android.content.Context;

import com.bridgesafe.bridge.BuildConfig;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    public static MyApplication mContext;
    private static MyApplication instance;
    public static Context applicationContext;


    public static MyApplication getInstance() {
        if (mContext == null) {
            return new MyApplication();
        } else {
            return mContext;
        }
    }

    @Override
    public void onCreate() {
        // MultiDex.install(this);
        super.onCreate();
        instance = this;
        applicationContext = this;
        initLogger();//日志打印初始化

        // MobSDK.init(this);//shareSDk初始化
        // preInitX5Core();
        // ZXingLibrary.initDisplayOpinion(this);
        // initX5WebView();
        // DemoHelper.getInstance().init(mContext);
        //
        //                                             initX5WebView();
    }
//    private void preInitX5Core() {
//        //预加载x5内核
//        Intent intent = new Intent(this, X5NetService.class);
//        startService(intent);
//    }


    private void initLogger() {
        XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE,
                config);
    }

    LogConfiguration config = new LogConfiguration.Builder()
            .tag("HL").build();

    /**
     * Bugly更新
     */
//    private void initBugly() {
//        Bugly.init(getApplicationContext(), "a8042d9287", false);
//        Beta.autoCheckUpgrade = true;//设置自动检查
//        Beta.upgradeCheckPeriod = 60 * 60 * 1000;
//        Beta.largeIconId = R.mipmap.ic_launcher;
//
//    }
    public static MyApplication getInstance(Context mContext) {
        return instance;
    }


//    private void initX5WebView() {
//        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.d("app", " onViewInitFinished is " + arg0);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//            }
//        };
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(), cb);
//    }


}
