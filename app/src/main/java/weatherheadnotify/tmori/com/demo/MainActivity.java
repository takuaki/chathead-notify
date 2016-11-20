package weatherheadnotify.tmori.com.demo;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import chatheadnotify.tmori.com.chathead.ChatHead;
import weatherheadnotify.tmori.com.demo.espressoIdling.MyIdlingResource;

public class MainActivity extends AppCompatActivity implements ChatHead.ChatHeadStateListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    ChatHead mChatHead;

    //Idling resource for test
    MyIdlingResource mIdlingResource = new MyIdlingResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIdlingResource.setIdleState(false);

        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        ChatHead chatHead = new ChatHead(root, R.layout.chathead_layout, ChatHead.DURATION_MEDIUM);
        chatHead.setHeadStateListener(this);
        chatHead.setLaunchActivity(this, LaunchableActivity.class);
        chatHead.setBGM(false, R.raw.knock);

        chatHead.show();

    }


    @Override
    public void isShown() {
        Log.d(TAG, "isShown");
        mIdlingResource.setIdleState(true);
    }


    @VisibleForTesting
    MyIdlingResource getIdlingResource() {
        if (mIdlingResource != null) {
            return mIdlingResource;
        }
        return null;
    }

}
