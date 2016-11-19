package weatherheadnotify.tmori.com.chathead;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;

/**
 * Created by mori on 11/3/16.
 */

public class ChatHead implements ViewPropertyAnimatorListener {


    interface ChatHeadStateListener {
        void isShown();
    }

    private static final String TAG = ChatHead.class.getSimpleName();
    private static final int MSG_SHOW = 1;
    private static final int MSG_DISMISS = 2;

    /**
     * duration for show chathead
     */
    private static final long LONG_REMAIN_SHORT = 1500l;
    private static final long LONG_REMAIN_MEDIUM = 3000l;
    private static final long LONG_REMAIN_LONG = 5000l;

    public static final int DURATION_SHORT = -1;
    public static final int DURATION_MEDIUM = -2;
    public static final int DURATION_LONG = -3;
    //private static final int DURATION_INFINITE = 0;

    @IntDef(value = {/*DURATION_INFINITE,*/ DURATION_SHORT, DURATION_MEDIUM, DURATION_LONG})
    @Retention(RetentionPolicy.SOURCE)
    @interface Duration {
    }

    /**
     * to handle when dismiss chatHead
     */
    private static final Handler sHandler;

    static {
        sHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == MSG_DISMISS) {
                    ChatHead chatHead = (ChatHead) msg.obj;
                    chatHead.hide();
                    return true;
                }
                return false;
            }
        });
    }

    private ChatHeadStateListener mHeadStateListener;

    private final
    @Duration
    int duration;

    private ChatHeadLayout mView;
    private ViewGroup mTargetView;

    public ChatHead(@NonNull View view, final int res, @Duration int duration) {
        Context con = view.getContext();
        this.duration = duration;
        mTargetView = findSuitableParent(view);
        LayoutInflater inflater = LayoutInflater.from(con);
        mView = (ChatHeadLayout) inflater.inflate(res, mTargetView, false);
    }

    public ChatHeadLayout getLayoutView() {
        return mView;
    }

    public void setHeadStateListener(ChatHeadStateListener listener) {
        mHeadStateListener = listener;
    }


    public void show() {

        mTargetView.addView(mView, mView.getLayoutParams());
        if (ViewCompat.isLaidOut(mView)) {
            startAnimate();
        } else {
            mView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    startAnimate();
                    mView.removeOnLayoutChangeListener(this);
                }
            });
        }
    }

    /**
     * hideView
     */
    private void hide() {
        if (!attachToTarget()) {
            return;
        }
        hideAnimate();
    }

    private synchronized void startAnimate() {
        //move right
        Log.d(TAG, "startAnimate");
        ViewCompat.setTranslationX(mView, mView.getWidth());
        ViewCompat.animate(mView)
                .translationX(0f)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new ViewPropertyAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(View view) {
                        final long durationMs;
                        if (duration > 0) {
                            durationMs = duration;
                        } else if (duration == DURATION_SHORT) {
                            durationMs = LONG_REMAIN_SHORT;
                        } else if (duration == DURATION_LONG) {
                            durationMs = LONG_REMAIN_LONG;
                        } else {
                            durationMs = LONG_REMAIN_MEDIUM;
                        }
                        sHandler.sendMessageDelayed(Message.obtain(sHandler, MSG_DISMISS, ChatHead.this), durationMs);
                    }
                })
                .setDuration(1000l)
                .start();
    }

    private synchronized void hideAnimate() {
        Log.d(TAG, "hideAnimate");
        //mTargetView.updateViewLayout(mView,mView.getLayoutParams());
        //ViewCompat.setTranslationX(mView, -mView.getWidth());
        ViewCompat.setTranslationX(mView, 0);
        ViewCompat.animate(mView)
                .translationXBy(mView.getWidth())
                .setInterpolator(new LinearInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        //when end animation , remove view from mTargetView
                        mView.setVisibility(GONE);
                        mTargetView.removeView(mView);
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .setDuration(800l)
                .start();
    }


    private boolean attachToTarget() {
        if (mView == null || mTargetView == null) {
            return false;
        }
        View current = mView;
        do {
            ViewParent parent = current.getParent();
            if (parent == mTargetView) {
                return true;
            } else {
                current = (View) parent;
            }
        } while (current != null);
        return false;
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof CoordinatorLayout) {
                // We've found a CoordinatorLayout, use it
                return (ViewGroup) view;
            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }

    //ViewPropertyAnimatorListener


    @Override
    public void onAnimationStart(View view) {
        Log.d(TAG, "onAnimationStart");
    }

    @Override
    public void onAnimationEnd(View view) {
        Log.d(TAG, "onAnimationEnd");
        if (mHeadStateListener != null) {
            mHeadStateListener.isShown();
        }
    }

    @Override
    public void onAnimationCancel(View view) {
        Log.d(TAG, "onAnimationCancel");
    }
}

abstract class ViewPropertyAnimationEndListener implements ViewPropertyAnimatorListener {
    @Override
    public void onAnimationStart(View view) {
    }

    abstract public void onAnimationEnd(View view);

    @Override
    public void onAnimationCancel(View view) {
    }
}
