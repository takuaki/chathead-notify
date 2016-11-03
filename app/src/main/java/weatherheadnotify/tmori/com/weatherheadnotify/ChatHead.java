package weatherheadnotify.tmori.com.weatherheadnotify;

import android.animation.Animator;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;

/**
 * Created by mori on 11/3/16.
 */

public class ChatHead {


    interface ChatHeadStateListener {
        void isShown();
    }

    private static final String TAG = ChatHead.class.getSimpleName();
    private ChatHeadStateListener mHeadStateListener;
    private ViewPropertyAnimator mPropertyAnimator;


    public ChatHead(final ChatHeadLayout layout, long durationMillis) {

        mPropertyAnimator = layout.animate();
        mPropertyAnimator.setDuration(durationMillis);
        mPropertyAnimator.translationX(2000);
        mPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd");
                mHeadStateListener.isShown();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    public void setStateListener(final ChatHeadStateListener listener) {
        mHeadStateListener = listener;
    }

    public void start() {
        Log.d(TAG, "startAnimation");
        mPropertyAnimator.start();
    }


    public static ChatHead make(ChatHeadLayout layout, long durationMillis) {
        return new ChatHead(layout, durationMillis);
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


}
