package com.android.mobile.utils.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class AppUtils {
    /**
     * 判断当前运行的进程中是否有以processName命名的
     * @param context
     * @param processName
     * @return
     */
    public static boolean isNamedProcess(Context context, String processName) {
        //if context is null, return false
        if (context == null) {
            return false;
        }

        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();
        //if ActivityManager.getRunningAppProcesses() is null, return false
        if (ListUtils.isEmpty(processInfoList)) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            // if one porcess of ActivityManager.getRunningAppProcesses() is equal to processName,
            //return true, otherwise return false;
            if (processInfo != null && processInfo.pid == pid
                    && ObjectUtils.isEquals(processName, processInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前应用是否在后台
     * need use permission android.permission.GET_TASKS in Manifest.xml
     * @param context
     * @return
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            // if application is in backgroud, return true; otherwise return false
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
