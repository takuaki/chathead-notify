package dev;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Button;

import weatherheadnotify.tmori.com.weatherheadnotify.R;

/**
 * Created by mori on 11/4/16.
 */

public class SnackActiivty extends Activity {

    private Button mButton;
    private static final String TAG = SnackActiivty.class.getSimpleName();

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);


        mButton = (Button) findViewById(R.id.button);
        Log.d(TAG, "mButton" + mButton.getHeight());
        Snackbar snackbar = Snackbar.make(mButton, "test", Snackbar.LENGTH_SHORT);
        snackbar.show();
        /*mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick");
                Snackbar snackbar = Snackbar.make(v, "test", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });*/
    }

}
