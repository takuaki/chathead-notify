package weatherheadnotify.tmori.com.weatherheadnotify;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by mori on 11/3/16.
 */

abstract public class ChatHeadBaseContainer<T> {

    private Subscription mSubscription = Subscriptions.empty();


    public ChatHeadBaseContainer() {
    }

    protected void start(Subscriber<T> subscriber) {
        mSubscription = createObservable().
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(subscriber);
    }

    abstract protected Observable<T> createObservable();

    public void unSubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }


}
