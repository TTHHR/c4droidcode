package cn.atd3.code4a.model.inter;


import android.graphics.Bitmap;

import cn.atd3.code4a.model.model.User;
import cn.atd3.code4a.mvpbase.BaseModel;
import rx.Observable;

/**
 * 作者：YGL
 * 电话：13036804886
 * 邮箱：2369015621@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 创建时间：2018/01/12   10:24
 **/
public interface Sign extends BaseModel {
    Observable<Integer> signin(String account, String password, Boolean remember, String code);
    Observable<Integer> signup(String user, String email, String password, String code);
    Observable<User> getUserInfo();
}