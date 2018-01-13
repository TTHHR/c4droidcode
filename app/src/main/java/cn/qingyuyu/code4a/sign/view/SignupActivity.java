package cn.qingyuyu.code4a.sign.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

import butterknife.BindView;
import cn.qingyuyu.code4a.R;
import cn.qingyuyu.code4a.sign.model.SignModel;
import cn.qingyuyu.code4a.sign.mvpbase.BaseActivity;
import cn.qingyuyu.code4a.sign.mvpbase.BaseView;
import cn.qingyuyu.code4a.sign.presenter.impl.SignupPresenter;
import cn.qingyuyu.code4a.sign.presenter.interfaces.SignupContract;
import es.dmoral.toasty.Toasty;

/**
 * 作者：YGL
 * 电话：13036804886
 * 邮箱：2369015621@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 创建时间：2018/01/12   9:37
 **/
public class SignupActivity extends BaseActivity<SignModel,SignupPresenter> implements SignupContract.View {

    @BindView(R.id.user_name)
    BootstrapEditText userName;
    @BindView(R.id.email)
    BootstrapEditText email;
    @BindView(R.id.password)
    BootstrapEditText password;
    @BindView(R.id.password_confirm)
    BootstrapEditText passwordConfirm;
    @BindView(R.id.signup_button)
    BootstrapButton signupButton;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle s){
        super.onCreate(s);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                mPresenter.signupButtonClick(userName.getText().toString(),email.getText().toString(),password.getText().toString());
            }
        });
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void userNameError(String message) {
        closeProgressDialog();
        userName.setError("用户名错误:"+message);
    }

    @Override
    public void emailError(String message) {
        closeProgressDialog();
        email.setError("邮箱错误:"+message);
    }

    @Override
    public void passwordError(String message) {
        closeProgressDialog();
        password.setError("密码错误:"+message);
    }

    @Override
    public void passwordDifferent(String message) {
        closeProgressDialog();
        password.setError("密码错误不一样");
    }

    @Override
    public void remoteError(String message) {
        closeProgressDialog();
        Toasty.error(this,"出现错误:"+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signupSuccessful() {
        closeProgressDialog();
        Toasty.success(this, "注册成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_signup;
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    /**   * 显示进度对话框   */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("登陆中");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    /**   * 关闭进度对话框   */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
