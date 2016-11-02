package weatherheadnotify.tmori.com.weatherheadnotify;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mori on 11/2/16.
 */

public class WeatherHeadLayout extends LinearLayout {

    private static final String TAG = weatherheadnotify.tmori.com.weatherheadnotify.WeatherHeadLayout.class.getSimpleName();
    private TextView mWeatherText;
    private ImageView mImageView;
    private CharSequence weatherText;
    private Drawable drawable;

    public WeatherHeadLayout(Context context) {
        this(context, null);
    }

    public WeatherHeadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherHeadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WeatherLayout);
        int resourceID = a.getResourceId(R.styleable.WeatherLayout_weather_icon, 0);
        drawable = context.getDrawable(resourceID);
        weatherText = a.getText(R.styleable.WeatherLayout_weather_text);
        a.recycle();

        View layout = LayoutInflater.from(context).inflate(R.layout.weather_notification, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = (ImageView) findViewById(R.id.weather_icon);
        mWeatherText = (TextView) findViewById(R.id.text_weather);
        mImageView.setImageDrawable(drawable);
        mWeatherText.setText(weatherText);
    }

    public CharSequence getText() {
        return mWeatherText.getText();
    }

    public Drawable getDrawable() {
        return drawable;
    }

}
