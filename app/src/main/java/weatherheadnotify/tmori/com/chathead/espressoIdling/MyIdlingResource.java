package weatherheadnotify.tmori.com.chathead.espressoIdling;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mori on 11/3/16.
 */

public class MyIdlingResource implements IdlingResource {

    private AtomicBoolean mIsIdlingNow = new AtomicBoolean(false);
    private volatile IdlingResource.ResourceCallback mResourceCallback;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdlingNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback callback) {
        mResourceCallback = callback;
    }

    public void setIdleState(boolean isIdleNow) {
        mIsIdlingNow.set(isIdleNow);
        if (isIdleNow && mResourceCallback != null) {
            mResourceCallback.onTransitionToIdle();
        }
    }
}
