package com.recee.matcha.presenter;

import android.text.TextUtils;

import com.recee.matcha.utils.AccountUtil;
import com.recee.matcha.view.LoginView;

/**
 * @author Reece
 * @version V1.0
 * @Date 1/20/17
 * @Description
 */

public class LoginPresenter implements Presenter {

    private LoginView mLoginView;
    public static final int TYPE_WRONG_INPUT_ALL = 1;
    public static final int TYPE_WRONG_EMAIL_FORMAT = 2;
    public static final int TYPE_WRONG_PASSWORD_LENGTH = 3;

    public LoginPresenter(LoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    public void login(String email, String pwd) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)) {
            mLoginView.onInputError(TYPE_WRONG_INPUT_ALL);
            return;
        }
        if (!AccountUtil.validate(email)) {
            mLoginView.onInputError(TYPE_WRONG_EMAIL_FORMAT);
            return;
        }
        if (pwd.length() < 6) {
            mLoginView.onInputError(TYPE_WRONG_PASSWORD_LENGTH);
            return;
        }
        if ("foreece@gmail.com".equals(email) && "111111".equals(pwd)) {
            mLoginView.onLoginSuccess();
        } else {
            mLoginView.onLoginFailed();
        }
    }
}
