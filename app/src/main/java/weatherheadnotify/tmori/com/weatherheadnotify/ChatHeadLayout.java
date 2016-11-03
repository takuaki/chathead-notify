package weatherheadnotify.tmori.com.weatherheadnotify;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by mori on 11/3/16.
 */

public class ChatHeadLayout extends LinearLayout {
    private static final String TAG = ChatHeadLayout.class.getSimpleName();

    public ChatHeadLayout(Context context) {
        this(context, null);
    }

    public ChatHeadLayout(Context con, AttributeSet attrs) {
        super(con, attrs);
        Log.d(TAG, "ChatHeadLayout");
        LayoutInflater.from(con).inflate(R.layout.chathead_layout, this);

    }

}