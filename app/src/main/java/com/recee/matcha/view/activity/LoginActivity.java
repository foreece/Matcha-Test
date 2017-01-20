package com.recee.matcha.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.recee.matcha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Reece
 * @version V1.0
 * @Date 1/20/17
 * @Description
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.email)
    protected EditText mEditTextEmail;
    @BindView(R.id.pwd)
    protected EditText mEditTExtPwd;
    @BindView(R.id.login)
    protected Button mButtonLogin;
    @BindView(R.id.clear)
    protected Button mButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }
}
