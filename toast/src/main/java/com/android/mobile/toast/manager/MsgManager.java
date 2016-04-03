package com.android.mobile.toast.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.mobile.toast.model.AppMsg;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.WeakHashMap;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;

/**
 * Created by xinming.xxm on 2016/4/3.
 */
public class MsgManager extends Handler {
    private static final int MESSAGE_DISPLAY = 0xc2007;
    private static final int MESSAGE_ADD_VIEW = 0xc20074dd;
    private static final int MESSAGE_REMOVE = 0xc2007de1;

    private static WeakHashMap<Activity, MsgManager> sManagers;
    private static ReleaseCallbacks sReleaseCallbacks;

    private final Queue<AppMsg> msgQueue;
    private Animation inAnimation, outAnimation;

    private MsgManager() {
        msgQueue = new LinkedList<AppMsg>();
    }

    /**
     * @return A {@link MsgManager} instance to be used for given {@link android.app.Activity}.
     */
    public static synchronized MsgManager obtain(Activity activity) {
        if (sManagers == null) {
            sManagers = new WeakHashMap<Activity, MsgManager>(1);
        }
        MsgManager manager = sManagers.get(activity);
        if (manager == null) {
            //new一个MsgManager对象，此时就会初始化AppMsg的队列
            manager = new MsgManager();
            ensureReleaseOnDestroy(activity);
            sManagers.put(activity, manager);
        }

        return manager;
    }

    /**
     * 确保当前应用在Activity的OnDestroy方法中释放
     * @param activity
     */
    public static void ensureReleaseOnDestroy(Activity activity) {
        //不支持sdk版本在14以下
        if (SDK_INT < ICE_CREAM_SANDWICH) {
            return;
        }
        if (sReleaseCallbacks == null) {
            sReleaseCallbacks = new ReleaseCallbacksIcs();
        }
        //ReleaseCallbacksIcs注册
        sReleaseCallbacks.register(activity.getApplication());
    }


    public static synchronized void release(Activity activity) {
        if (sManagers != null) {
            final MsgManager manager = sManagers.remove(activity);
            if (manager != null) {
                manager.clearAllMsg();
            }
        }
    }

    public static synchronized void clearAll() {
        if (sManagers != null) {
            final Iterator<MsgManager> iterator = sManagers.values().iterator();
            while (iterator.hasNext()) {
                final MsgManager manager = iterator.next();
                if (manager != null) {
                    manager.clearAllMsg();
                }
                iterator.remove();
            }
            sManagers.clear();
        }
    }

    /**
     * Inserts a {@link AppMsg} to be displayed.
     *
     * @param appMsg
     */
    public void add(AppMsg appMsg) {
        msgQueue.add(appMsg);
        //初始化toast从无到有的动画
        if (inAnimation == null) {
            inAnimation = AnimationUtils.loadAnimation(appMsg.getActivity(),
                    android.R.anim.fade_in);
        }
        //初始化toast从有到无的动画
        if (outAnimation == null) {
            outAnimation = AnimationUtils.loadAnimation(appMsg.getActivity(),
                    android.R.anim.fade_out);
        }
        displayMsg();
    }

    /**
     * Removes all {@link AppMsg} from the queue.
     */
    public void clearMsg(AppMsg appMsg) {
        if (msgQueue.contains(appMsg)) {
            // Avoid the message from being removed twice.
            removeMessages(MESSAGE_DISPLAY, appMsg);
            removeMessages(MESSAGE_ADD_VIEW, appMsg);
            removeMessages(MESSAGE_REMOVE, appMsg);
            msgQueue.remove(appMsg);
            removeMsg(appMsg);
        }
    }

    /**
     * Removes all {@link AppMsg} from the queue.
     */
    void clearAllMsg() {
        //清除AppMsg队列中的所有AppMsg
        if (msgQueue != null) {
            msgQueue.clear();
        }
        //移除Message队列中所有已what标识的post出来的message
        removeMessages(MESSAGE_DISPLAY);
        removeMessages(MESSAGE_ADD_VIEW);
        removeMessages(MESSAGE_REMOVE);
    }

    /**
     * Displays the next {@link AppMsg} within the queue.
     */
    private void displayMsg() {
        if (msgQueue.isEmpty()) {
            return;
        }
        // First peek whether the AppMsg is being displayed.
        final AppMsg appMsg = msgQueue.peek();//返回队头的AppMsg，不进行移除操作
        final Message msg;
        if (!appMsg.isShowing()) {
            // Display the AppMsg
            msg = obtainMessage(MESSAGE_ADD_VIEW);
            msg.obj = appMsg;
            sendMessage(msg);
        } else {
            msg = obtainMessage(MESSAGE_DISPLAY);
            sendMessageDelayed(msg, appMsg.getDuration()
                    + inAnimation.getDuration() + outAnimation.getDuration());
        }
    }

    /**
     * Removes the {@link AppMsg}'s view after it's display duration.
     *
     * @param appMsg The {@link AppMsg} added to a {@link ViewGroup} and should be removed.s
     */
    private void removeMsg(final AppMsg appMsg) {
        clearMsg(appMsg);
        final View view = appMsg.getView();
        ViewGroup parent = ((ViewGroup) view.getParent());
        if (parent != null) {
            //做动画监听
            outAnimation.setAnimationListener(new OutAnimationListener(appMsg));
            view.startAnimation(outAnimation);
            if (appMsg.isFloating()) {
                // Remove the AppMsg from the view's parent.
                parent.removeView(view);
            } else {
                appMsg.getView().setVisibility(View.INVISIBLE);
            }
        }

        Message msg = obtainMessage(MESSAGE_DISPLAY);
        sendMessage(msg);
    }

    /**
     * 将一个AppMsg装载的view和其layoutParams追加到当前Activity之后，然后发出延时消息给Handler，移除当前AppMsg
     * @param appMsg
     */
    private void addMsgToView(AppMsg appMsg) {
        View view = appMsg.getView();
        if (view.getParent() == null) {
            appMsg.getActivity().addContentView(
                    view,
                    appMsg.getLayoutParams());
        }
        view.startAnimation(inAnimation);
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        //实现一个Toast显示时间周期过了后，发起meesag_remove消息给Handler，让其移除当前生命周期结束的Toast
        final Message msg = obtainMessage(MESSAGE_REMOVE);
        msg.obj = appMsg;
        sendMessageDelayed(msg, appMsg.getDuration());
    }

    @Override
    public void handleMessage(Message msg) {
        final AppMsg appMsg;
        switch (msg.what) {
            case MESSAGE_DISPLAY:
                displayMsg();
                break;
            case MESSAGE_ADD_VIEW:
                appMsg = (AppMsg) msg.obj;
                addMsgToView(appMsg);
                break;
            case MESSAGE_REMOVE:
                appMsg = (AppMsg) msg.obj;
                removeMsg(appMsg);
                break;
            default:
                super.handleMessage(msg);
                break;
        }
    }

    private static class OutAnimationListener implements Animation.AnimationListener {

        private final AppMsg appMsg;

        private OutAnimationListener(AppMsg appMsg) {
            this.appMsg = appMsg;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //动画结束后，如果当前的toast不是悬浮状态,则gone掉view
            if (!appMsg.isFloating()) {
                appMsg.getView().setVisibility(View.GONE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    interface ReleaseCallbacks {
        void register(Application application);
    }

    @TargetApi(ICE_CREAM_SANDWICH)
    static class ReleaseCallbacksIcs implements Application.ActivityLifecycleCallbacks, ReleaseCallbacks {
        private WeakReference<Application> mLastApp;

        @Override
        public void register(Application app) {
            if (mLastApp != null && mLastApp.get() == app) {
                return; // Already registered with this app
            } else {
                mLastApp = new WeakReference<Application>(app);
            }
            //当前上下文环境注册
            app.registerActivityLifecycleCallbacks(this);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            release(activity);
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }
    }
}