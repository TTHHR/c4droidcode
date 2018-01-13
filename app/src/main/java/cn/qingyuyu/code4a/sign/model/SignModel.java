package cn.qingyuyu.code4a.sign.model;

import android.util.Log;

import java.io.IOException;

import cn.atd3.proxy.exception.PermissionException;
import cn.atd3.proxy.exception.ServerException;
import cn.qingyuyu.code4a.sign.presenter.interfaces.Sign;
import cn.qingyuyu.code4a.sign.presenter.interfaces.SigninContract;
import cn.qingyuyu.code4a.remote.Remote;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：YGL
 * 电话：13036804886
 * 邮箱：2369015621@qq.com
 * 版本号：1.0
 * 类描述：负责提供相应数据
 * 备注消息：
 * 创建时间：2018/01/10   22:17
 **/
public class SignModel implements Sign {
    private static final String TAG = "SignModel";
    @Override
    public Observable<Object> signin(final String account, final String password, final Boolean remember, final String code) {
        //这里使用了Rxjava
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                Object o=null;
                try {
                    o= Remote.user.method("signin").call(account,password,remember,code);
                }catch (ServerException e){
                    Log.e(TAG,"ServerException:"+e);
                }catch (PermissionException e){
                    Log.e(TAG,"ermissionException:"+e);
                }catch (IOException e){
                    Log.e(TAG,"IOException:"+e);
                }
                subscriber.onNext(o);
            }
        }).subscribeOn(Schedulers.io())//在其他线程执行
          .observeOn(AndroidSchedulers.mainThread());//在主线程触发
    }

    @Override
    public Observable<Object> signup(final String user, final String email, final String password,final String code) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                Log.i("xxxxx","1");
                Object o=null;
                try {
                    o= Remote.user.method("signup").call(user,email,password,code);
                    Log.i("xxxxx","4 o:"+o.toString());
                }catch (ServerException e){
                    Log.i("xxxxx","5");
                    Log.e(TAG,"ServerException:"+e);
                }catch (PermissionException e){
                    Log.i("xxxxx","6");
                    Log.e(TAG,"ermissionException:"+e);
                }catch (IOException e){
                    Log.i("xxxxx","7");
                    Log.e(TAG,"IOException:"+e);
                }
                Log.i("xxxxx","2");
                subscriber.onNext(o);
                Log.i("xxxxx","3");
            }
        }).subscribeOn(Schedulers.io())//在其他线程执行
          .observeOn(AndroidSchedulers.mainThread());//在主线程触发
    }
}
