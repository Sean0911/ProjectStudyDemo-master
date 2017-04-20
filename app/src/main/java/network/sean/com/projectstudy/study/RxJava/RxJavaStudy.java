package network.sean.com.projectstudy.study.RxJava;

import android.util.Log;
import android.view.View;

import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class RxJavaStudy {

    public void btn11(View view){
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onNext("4");
                e.onNext("5");
                e.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Logger.e("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Logger.e(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Logger.e("onComplete");
            }
        };

        observable.subscribe(observer);
    }


    //doOnNext()有问题接收不到发送的消息
    public void btn33(View view){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Logger.e("Before subscribeOn(), current thread is: " + Thread.currentThread().getName());
                e.onNext("btn");
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.e("After observeOn(), current thread is: " + Thread.currentThread().getName());
                        Logger.e(s);
                    }
                }).observeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.e("After observeOn(), current thread is: " + Thread.currentThread().getName());
                        Logger.e(s);
                    }
                });
    }

    public void btn22(View view){
        final Disposable[] mDisposable = new Disposable[1];
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Logger.e("start Emitter");
                Logger.e("Emitter - 1");
                e.onNext("1");
                Logger.e("Emitter - 2");
                e.onNext("2");
                Logger.e("Emitter - 3");
                e.onNext("3");
                Logger.e("Emitter - 4");
                e.onNext("4");
                Logger.e("Emitter Complete");
                e.onComplete();
                Logger.e("Emitter - 5");
                e.onNext("5");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.e("onSubscribe");
                mDisposable[0] = d;
            }

            @Override
            public void onNext(String value) {
                Logger.e("Receive" + value);
                if(value.equals("2")){
                    Logger.e("dispose");
                    mDisposable[0].dispose();
                    Logger.e("isDisposed : " + mDisposable[0].isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Logger.e("perform onComplete");
            }
        });
    }

    public void btn44(View view){

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Logger.e("Before subscribeOn(), current thread is: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).subscribeOn(Schedulers.io())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Logger.e("After subscribeOn(), current thread is: " + Thread.currentThread().getName());
                        return "This is result" + integer;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.e("After observeOn(), current thread is: " + Thread.currentThread().getName());
                        Logger.e(s);
                    }
                });
    }

    public void btn55(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.e(s);
            }
        });
    }
}
