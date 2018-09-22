package myapplication66.yanglh6.example.com.textactivity;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 覃微
 * Data:2017/3/23.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public static List<Activity> activitiesList = new ArrayList<Activity>(); // 活动管理集合

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);     		// 初始化 JPush
//        BleManger.getInstance().intiBle(this);


    }

    /**
     * 获取单例
     *
     * @return
     *
     */
    public MyApplication() {
    }

    //单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }



    /**
     * 把活动添加到活动管理集合
     *
     * @param acty
     */
    public void addActyToList(Activity acty) {
        if (!activitiesList.contains(acty))
            activitiesList.add(acty);
    }

    /**
     * 把活动从活动管理集合移除
     *
     * @param acty
     */
    public void removeActyFromList(Activity acty) {
        if (activitiesList.contains(acty))
            activitiesList.remove(acty);
    }

    /**
     * 程序退出
     */
    public void clearAllActies() {
        for (Activity acty : activitiesList) {
            if (acty != null)
                acty.finish();
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public static void exit() {
        for (Activity activity : activitiesList) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}
