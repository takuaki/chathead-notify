package weatherheadnotify.tmori.com.weatherheadnotify;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Subscriber;
import weatherheadnotify.tmori.com.weatherheadnotify.espressoIdling.MyIdlingResource;

public class MainActivity extends AppCompatActivity implements ChatHead.ChatHeadStateListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    ChatHeadTestContainer mContainer;
    ChatHeadLayout mHeadLayout;
    ChatHead mChatHead;

    //Idlig resource for test
    MyIdlingResource mIdlingResource = new MyIdlingResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHeadLayout = (ChatHeadLayout) findViewById(R.id.chathead_layout);
        mChatHead = ChatHead.make(mHeadLayout, 3000);
        mChatHead.setStateListener(this);
        mContainer = new ChatHeadTestContainer();
        mContainer.start(new TextSubscriber());

        //resource
        mIdlingResource.setIdleState(false);
    }

    @Override
    public void isShown() {
        mIdlingResource.setIdleState(true);
    }


    private class TextSubscriber extends Subscriber<String> {
        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted");
            mChatHead.start();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "string:" + s);
        }
    }


    @VisibleForTesting
    MyIdlingResource getIdlingResource() {
        if (mIdlingResource != null) {
            return mIdlingResource;
        }
        return null;
    }

    /***
     * ChatHeadLayout layout= ,findViewByID(R.layout.chathead);
     * ChatHead chatHead = ChatHead.Builder(con).
     * moveIn(duration).
     * moveOut(duration).
     * setDuration(duration).
     * build();
     *
     * chatHead.setAdvanceJob(Object job,Callback viewUpgrade);
     * chatHead.start();
     *
     *
     *
     * chatHead.setJobs()
     */


}
