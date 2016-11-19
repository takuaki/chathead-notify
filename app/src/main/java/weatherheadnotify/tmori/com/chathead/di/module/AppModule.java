package weatherheadnotify.tmori.com.chathead.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import weatherheadnotify.tmori.com.chathead.App;

/**
 * Created by mori on 11/3/16.
 */
@Module
public class AppModule {

    final private App app;

    public AppModule(App application) {
        this.app = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.app.getApplicationContext();
    }
}
