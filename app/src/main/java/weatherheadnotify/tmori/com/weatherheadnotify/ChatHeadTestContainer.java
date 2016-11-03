package weatherheadnotify.tmori.com.weatherheadnotify;

import rx.Observable;

/**
 * Created by mori on 11/3/16.
 */
public class ChatHeadTestContainer extends ChatHeadBaseContainer<String> {


    public ChatHeadTestContainer() {
        super();
    }

    protected Observable<String> createObservable() {
        return Observable.from(new String[]{"test0", "test1", "test2"});
    }

}
