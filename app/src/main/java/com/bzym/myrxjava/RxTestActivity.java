package com.bzym.myrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.bzym.myrxjava.bean.XiaoSuang;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxTestActivity extends AppCompatActivity {
    private String TAG = "RxTestActivity";
    private ZLog z;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
        z = new ZLog(TAG, 1);
        initData();
        assignViews();

        initObserver();
//        ObservableT1();
//        ObservableT2();
        ObservableT3();

    }
    private String hah = "";
    private String hah2 = "";
    private void ObservableT3() {
        hah = "";
        hah2 = "";
        Func1<XiaoSuang,String> func110 = new Func1<XiaoSuang, String>() {
            @Override
            public String call(XiaoSuang xiaoSuang) {
                return xiaoSuang.getAge() + xiaoSuang.getName();
            }
        };

        Func1<String,String> showTest = new Func1<String, String>() {
            @Override
            public String call(String s) {
                hah2 = hah2 + s + "\n";
                textView2.setText(hah2);
                return s;
            }
        };

        Observable.from(xiaoXLists)
                .subscribeOn(Schedulers.io())
                .map(func110)
                .observeOn(Schedulers.computation())
                .map(showTest)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        hah = hah + s + "\n";
                        textView.setText(hah);
                        z.zz("call<String>() - s = " + s);
                    }
                });
    }

    private void ObservableT2() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                z.zz("onNext<String>() - s = " + s);
            }
        };

        Observable.from(xiaoXLists)
                .flatMap(new Func1<XiaoSuang, Observable<String>>() {
                    @Override
                    public Observable<String> call(XiaoSuang xiaoSuang) {
                        return Observable.just(xiaoSuang.getName(),xiaoSuang.getAge()+"",xiaoSuang.getAddress());
                    }
                })
                .subscribe(subscriber);
    }

    private void ObservableT1() {
        Observable.just("牛逼","第二","我真是日了狗了","第四")
                .map(new Func1<String, Double>() {
                    @Override
                    public Double call(String s) {
                        double toDouble = stringToDouble(s);
                        z.zz("Action1<String>() - toDouble = " + toDouble);
                        return toDouble;
                    }
                })
                .map(new Func1<Double, String>() {
                    @Override
                    public String call(Double aDouble) {
                        return String.valueOf(aDouble * 2);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        z.zz("Action1<String>() - s = " + s);
                    }
                });
    }

    private List<XiaoSuang> xiaoXLists = new ArrayList<>();

    private void initData() {
        for (int i = 0; i < 5; i++) {
            XiaoSuang xiaoSuang = new XiaoSuang();
            xiaoSuang.setName(getRandomChar(i));
            xiaoSuang.setAge(i);
            xiaoSuang.setAddress("我是地址" + getRandomChar(i));
            xiaoXLists.add(xiaoSuang);
        }
    }

    //在0x4e00---0x9fa5之间产生一个随机的字符
    public static String getRandomChar(int sum)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sum + 3; i++) {
            //40869 - 19968
            sb.append((char)(0x4e00+(int)(Math.random()*(0x9fa5-0x4e00+1))));
        }
        return sb.toString();
    }
//    new Action1<Double>() {
//        @Override
//        public void call(Double aDouble) {
//            z.zz("Action1<Double>() - aDouble = " + aDouble);
//        }
//    }

    private double stringToDouble(String s) {
        return Math.random()*10000;
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
    }

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private Button button;
    private Button button2;
    private RadioButton radioButton;
    private CheckBox checkBox;
    private Switch switch1;
    private ImageButton imageButton;
    private ImageView imageView;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;
    private ProgressBar progressBar4;

    private void assignViews() {
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        switch1 = (Switch) findViewById(R.id.switch1);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
    }

}
