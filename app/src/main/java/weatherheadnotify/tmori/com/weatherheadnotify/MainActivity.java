package weatherheadnotify.tmori.com.weatherheadnotify;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import weatherheadnotify.tmori.com.weatherheadnotify.espressoIdling.MyIdlingResource;

public class MainActivity extends AppCompatActivity implements ChatHead.ChatHeadStateListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    ChatHead mChatHead;

    //Idling resource for test
    MyIdlingResource mIdlingResource = new MyIdlingResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        ChatHead chatHead = new ChatHead(root, R.layout.chathead_layout, 3000);

        ChatHeadLayout chatHeadLayout = chatHead.getLayoutView();
        ((Button) chatHeadLayout.findViewById(R.id.action_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick");
            }
        });

        chatHead.show();

        //resource
        mIdlingResource.setIdleState(false);
    }


    @Override
    public void isShown() {
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
