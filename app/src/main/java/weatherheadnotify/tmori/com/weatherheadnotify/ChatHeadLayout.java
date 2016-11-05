package weatherheadnotify.tmori.com.weatherheadnotify;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by mori on 11/3/16.
 */

public class ChatHeadLayout extends LinearLayout {
    private static final String TAG = ChatHeadLayout.class.getSimpleName();

    private final int mMaxWidth;

    public ChatHeadLayout(Context context) {
        this(context, null);
    }

    public ChatHeadLayout(Context con, AttributeSet attrs) {
        super(con, attrs);
        Log.d(TAG, "ChatHeadLayout");

        TypedArray a = con.obtainStyledAttributes(attrs, R.styleable.ChatHeadLayout);
        mMaxWidth = a.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
        a.recycle();

        LayoutInflater.from(con).inflate(R.layout.chathead_layout_include, this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");

        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
            throw new IllegalArgumentException("android:width should be exact value");
        }

        if (mMaxWidth > 0 && getMeasuredWidth() > mMaxWidth) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        Log.d(TAG, "height:" + this.getHeight() + " width:" + this.getWidth());
    }


}