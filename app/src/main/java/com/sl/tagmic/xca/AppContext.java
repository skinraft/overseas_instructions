package com.sl.tagmic.xca;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class AppContext extends Application {

    public static AppContext instance;
    /**
     * 线程池
     */
    public static ExecutorService cachedThreadPool = Executors
            .newCachedThreadPool();
    /**
     * 窗口管理
     **/
    private WindowManager mManager = null;
    /**
     * 屏幕数据
     **/
    public static DisplayMetrics metrics = null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 初始化屏幕参数
        mManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        metrics = new DisplayMetrics();
        mManager.getDefaultDisplay().getMetrics(metrics);
    }
    // 获取android手机串号
    public static String getImei(Context context) {
        String imi = "";
        try {
            TelephonyManager TelephonyMgr = (TelephonyManager) context
                    .getSystemService(TELEPHONY_SERVICE);
            imi = TelephonyMgr.getDeviceId();
        } catch (Exception e) {
            imi = "获取手机串号异常";
        }
        return imi;
    }
}
