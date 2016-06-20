package com.android.mobile.utils.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class PackageUtils {


    /**
     * ��ȡָ��������Ϣ
     */
    public static ActivityManager.RunningTaskInfo getTopRunningTask(Context context) {
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            // �õ���ǰ�������е�����ջ
            List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(1);
            // �õ�ǰ̨��ʾ������ջ
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            return runningTaskInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ȡ��ǰӦ�õİ汾��
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        if (context != null) {
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                PackageInfo pi;
                try {
                    pi = pm.getPackageInfo(context.getPackageName(), 0);
                    if (pi != null) {
                        return pi.versionCode;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * whether context is system application
     */
    public static boolean isSystemApplication(Context context) {
        if (context == null) {
            return false;
        }
        return isSystemApplication(context, context.getPackageName());
    }

    /**
     * whether packageName is system application
     */
    public static boolean isSystemApplication(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || packageName == null || packageName.length() == 0) {
            return false;
        }
        try {
            ApplicationInfo app = packageManager.getApplicationInfo(packageName, 0);
            return (app != null && (app.flags & ApplicationInfo.FLAG_SYSTEM) > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ��ȡ�Ѱ�װ��ȫ��Ӧ����Ϣ
     */
    public static List<PackageInfo> getInsatalledPackages(Context context) {
        return context.getPackageManager().getInstalledPackages(0);
    }

    /**
     * �жϵ�ǰpkg�Ƿ�װ
     * @param context
     * @param pkg
     * @return
     */
    public static boolean isInsatalled(Context context, String pkg) {
        if (!StringUtils.isEmpty(pkg)) {
            List<PackageInfo> list = getInsatalledPackages(context);
            if (!ListUtils.isEmpty(list)) {
                for (PackageInfo pi : list) {
                    if (pkg.equalsIgnoreCase(pi.packageName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * ��ȡָ��������Ϣ
     */
    public static ApplicationInfo getApplicationInfo(Context context, String pkg) {
        try {
            return context.getPackageManager().getApplicationInfo(pkg, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ȡָ��������Ϣ
     */
    public static android.content.pm.PackageInfo getPackageInfo(Context context, String pkg) {
        try {
            return context.getPackageManager().getPackageInfo(pkg, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����Ӧ��
     */
    public static boolean startAppByPackageName(Context context, String packageName) {
        return startAppByPackageName(context, packageName, null);
    }

    /**
     * ����Ӧ��
     */
    public static boolean startAppByPackageName(Context context, String packageName, Map<String, String> param) {
        android.content.pm.PackageInfo pi = null;
        try {
            pi = context.getPackageManager().getPackageInfo(packageName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                resolveIntent.setPackage(pi.packageName);
            }

            List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(resolveIntent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                String packageName1 = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                ComponentName cn = new ComponentName(packageName1, className);

                intent.setComponent(cn);
                if (param != null) {
                    for (Map.Entry<String, String> en : param.entrySet()) {
                        intent.putExtra(en.getKey(), en.getValue());
                    }
                }
                context.startActivity(intent);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "����ʧ��",
                    Toast.LENGTH_LONG).show();
        }
        return false;
    }

    /**
     *�жϵ�ǰapp�Ƿ���ջ��
     * @param context
     * @param packageName
     * @return
     */
    public static Boolean isTopActivity(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            return null;
        }

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo == null || tasksInfo.size() < 1) {
            return null;
        }
        try {
            return packageName.equals(tasksInfo.get(0).topActivity
                    .getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
