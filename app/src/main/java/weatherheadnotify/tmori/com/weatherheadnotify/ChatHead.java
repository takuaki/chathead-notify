package weatherheadnotify.tmori.com.weatherheadnotify;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;

/**
 * Created by mori on 11/3/16.
 */

public class ChatHead implements ViewPropertyAnimatorListener {


    interface ChatHeadStateListener {
        void isShown();
    }

    private static final String TAG = ChatHead.class.getSimpleName();
    private ChatHeadStateListener mHeadStateListener;
    private ViewPropertyAnimator mPropertyAnimator;

    private final long duration;

    private ChatHeadLayout mView;
    private ViewGroup mTargetView;

    public ChatHead(@NonNull View view, final int res, final long duration) {
        Context con = view.getContext();
        this.duration = duration;
        mTargetView = findSuitableParent(view);
        LayoutInflater inflater = LayoutInflater.from(con);
        mView = (ChatHeadLayout) inflater.inflate(res, mTargetView, false);
        //mTargetView.addView(mView, mView.getLayoutParams());
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

    private void startAnimate() {
        //move right
        Log.d(TAG, "startAnimate");
        ViewCompat.setTranslationX(mView, mView.getWidth());
        ViewCompat.animate(mView)
                .translationX(0f)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setListener(this)
                .setDuration(duration)
                .start();
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
