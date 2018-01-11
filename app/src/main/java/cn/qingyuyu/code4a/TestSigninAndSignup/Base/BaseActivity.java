package cn.qingyuyu.code4a.TestSigninAndSignup.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.qingyuyu.code4a.TestSigninAndSignup.tools.ContractProxy;

/**
 * 作者：YGL
 * 电话：13036804886
 * 邮箱：2369015621@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 创建时间：2018/01/10   22:33
 **/
public abstract class BaseActivity<M extends BaseModel,P extends BasePresenter> extends AppCompatActivity {
    //    定义Presenter
    protected P mPresenter;
    protected Unbinder unbinder;

    //在初始化时绑定MVP
    @Override
    public void onCreate(Bundle s){
        super.onCreate(s);
        if (getLayoutId() != 0) {
            //设置布局资源文件
            setContentView(getLayoutId());
            //注解绑定
            unbinder = ButterKnife.bind(this);
            bindMVP();
        }
    }

    //在销毁时解除绑定
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter = null;
        }
    }

    //    获取布局资源文件
    protected abstract int getLayoutId();

    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPresenter = getPresenterImpl();
            mPresenter.mContext = this;
            bindVM();//Presenter绑定VM
        }
    }
    private  void bindVM() {
        if(mPresenter!=null&&!mPresenter.isViewBind()&&getModelClazz()!=null&&getViewImp()!=null) {
            ContractProxy.getInstance().bindModel(getModelClazz(),mPresenter);
            ContractProxy.getInstance().bindView(getViewImp(),mPresenter);
            mPresenter.mContext=this;
        }
    }

    //   获取抽取View对象
    protected   abstract BaseView getViewImp();
    //    获得抽取接口Presenter对象
    protected Class getPresenterClazz() {
        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
    }
    //    获得抽取接口Model对象
    protected   Class getModelClazz()  {
        return (Class<M>)ContractProxy.getModelClazz(getClass(), 0);
    }
    //获得Presenter实例
    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }
}
