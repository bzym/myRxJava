package com.bzym.myrxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ZLog z;
    private Observer<String> observer;
    private Subscriber<String> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        z = new ZLog(TAG, 1);

//        initObserver();
//
//        initObservable();

//        initActionObserver();

//        printString();

//        getDrawableOb();

        runInOtherThrean();
    }

    private void runInOtherThrean() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        z.zz("Action1 - number " + number);
                    }
                });
    }

    private void getDrawableOb() {
        int drawableRes = 1;
        ImageView imageView ;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {

            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Drawable drawable) {

            }
        });
    }

    private void printString() {
        String[] names = {"你妹", "fuck", "hello"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        z.zz("Action1 - name " + name);
                    }
                });
    }

    private void initActionObserver() {
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                z.zz("Action1 - onNextAction " + s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                z.zz("Action1 - onErrorAction");
                throwable.printStackTrace();
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                z.zz("Action0 - onCompletedAction ");
            }
        };
        Observable observable = Observable.just("Hello", "Hi", "Aloha");
// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    private void initObservable() {
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
        Observable observable = Observable.just("Hello", "Hi", "Aloha");
        String[] words = {"HelloAA", "HiAA", "AlohaAA"};
        Observable observable2 = Observable.from(words);
        observable.subscribe(observer);
        // 或者：
        observable2.subscribe(subscriber);
    }

    private void initObserver() {

        observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                z.zz("Observer - onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                z.zz("Observer - onError");
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                z.zz("Observer - onNext " + s);
            }
        };

        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                z.zz("Subscriber - onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                z.zz("Subscriber - onError");
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                z.zz("Subscriber - onNext " + s);
            }
        };
    }
}
