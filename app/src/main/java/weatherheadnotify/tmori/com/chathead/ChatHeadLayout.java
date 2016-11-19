package weatherheadnotify.tmori.com.chathead;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mori on 11/3/16.
 */

public class ChatHeadLayout extends LinearLayout {
    private static final String TAG = ChatHeadLayout.class.getSimpleName();

    private TextView mNotificationText;
    private ImageView mImageView;


    public ChatHeadLayout(Context context) {
        this(context, null);
    }

    public ChatHeadLayout(Context con, AttributeSet attrs) {
        super(con, attrs);
        Log.d(TAG, "ChatHeadLayout");

        TypedArray a = con.obtainStyledAttributes(attrs, R.styleable.ChatHeadLayout);
        final String text = a.getString(R.styleable.ChatHeadLayout_chathead_text);
        final Drawable icon = a.getDrawable(R.styleable.ChatHeadLayout_chathead_icon);
        final int textColor = a.getColor(R.styleable.ChatHeadLayout_chathead_text_color, android.R.color.black);
        a.recycle();
        View layout = LayoutInflater.from(con).inflate(R.layout.chathead_layout_include, this);
        mNotificationText = (TextView) layout.findViewById(R.id.chathead_text);
        mImageView = (ImageView) layout.findViewById(R.id.chathead_icon);

        mNotificationText.setTextColor(textColor);
        mNotificationText.setText(text);
        mImageView.setImageDrawable(icon);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");

        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
            throw new IllegalArgumentException("android:width should be exact value");
        }
        Log.d(TAG, "height:" + this.getHeight() + " width:" + this.getWidth());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}