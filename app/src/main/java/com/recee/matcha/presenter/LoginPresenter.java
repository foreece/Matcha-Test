package com.recee.matcha.presenter;

import com.recee.matcha.view.LoginView;

/**
 * @author Reece
 * @version V1.0
 * @Date 1/20/17
 * @Description
 */

public class LoginPresenter implements Presenter {

    private LoginView mLoginView;

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
        if ("reece".equals(email) && "111".equals(pwd)) {
            mLoginView.onLoginSuccess();
        } else {
            mLoginView.onLoginFailed();
        }
    }
}
