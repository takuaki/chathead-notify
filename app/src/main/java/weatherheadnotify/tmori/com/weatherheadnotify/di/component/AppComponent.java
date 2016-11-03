package weatherheadnotify.tmori.com.weatherheadnotify.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import weatherheadnotify.tmori.com.weatherheadnotify.di.module.AppModule;

/**
 * Created by mori on 11/3/16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    //expose to subgraph
    Context context();
}
