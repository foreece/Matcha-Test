package com.recee.matcha.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.recee.matcha.R;
import com.recee.matcha.presenter.LoginPresenter;
import com.recee.matcha.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Reece
 * @version V1.0
 * @Date 1/20/17
 * @Description
 */

public class LoginActivity extends BaseActivity implements LoginView{

    @BindView(R.id.email)
    protected EditText mEditTextEmail;
    @BindView(R.id.pwd)
    protected EditText mEditTExtPwd;
    @BindView(R.id.login)
    protected Button mButtonLogin;
    @BindView(R.id.clear)
    protected Button mButtonCancel;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.login(mEditTextEmail.getText().toString(), mEditTExtPwd.getText().toString());
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextEmail.setText("");
                mEditTExtPwd.setText("");
            }
        });
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MatchaActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInputError(int errorType) {
        String message = "";
        switch (errorType) {
            case LoginPresenter.TYPE_WRONG_EMAIL_FORMAT:
                message = getResources().getString(R.string.tips_wrong_email);
                break;
            case LoginPresenter.TYPE_WRONG_INPUT_ALL:
                message = getResources().getString(R.string.tips_wrong_input);
                break;
            case LoginPresenter.TYPE_WRONG_PASSWORD_LENGTH:
                message = getResources().getString(R.string.tips_wrong_password);
                break;
            default:
                break;
        }
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
