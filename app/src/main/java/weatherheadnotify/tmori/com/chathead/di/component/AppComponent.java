package weatherheadnotify.tmori.com.chathead.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import weatherheadnotify.tmori.com.chathead.di.module.AppModule;

/**
 * Created by mori on 11/3/16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    //expose to subgraph
    Context context();
}
