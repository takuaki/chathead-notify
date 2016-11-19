package weatherheadnotify.tmori.com.chathead;

import android.app.Application;

import javax.inject.Singleton;

import weatherheadnotify.tmori.com.chathead.di.component.AppComponent;
import weatherheadnotify.tmori.com.chathead.di.component.DaggerAppComponent;
import weatherheadnotify.tmori.com.chathead.di.module.AppModule;

/**
 * Created by mori on 11/3/16.
 */
@Singleton
public class App extends Application {

    private AppComponent mAppComponent;

    public void onCreate() {
        mAppComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this)).build();
    }


}
