package com.recee.matcha;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.recee.matcha.presenter.LoginPresenter;
import com.recee.matcha.view.LoginView;
import com.recee.matcha.view.activity.LoginActivity;
import com.recee.matcha.view.activity.MatchaActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Reece
 * @version V1.0
 * @Date 1/22/17
 * @Description 使用 {@link ActivityController} 来验证Activity的生命周期是否正常调用。
 * {@link #testLifeCycle()} 测试{@link LoginActivity} 的生命周期
 * {@link #testLogin()} 测试{@link LoginActivity} 的登录按钮的点击，登录成功是否正确跳转
 * {@link #testClear()} 测试{@link LoginActivity} 的清除按钮，点击之后输入框内容被清空
 * {@link #testLoginPresenter()} 测试点击登录之后，{@link LoginView} 回调方法是否被正确调用
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginTest {

    private ActivityController mController;
    private LoginActivity mLoginActivity;
    private EditText mEditEmail;
    private EditText mEditPwd;
    private Button mLogin;
    private Button mClear;

    @Before
    public void setUp() {
        mController = Robolectric.buildActivity(LoginActivity.class);

        mLoginActivity = Robolectric.setupActivity(LoginActivity.class);
        mEditEmail = ((EditText)mLoginActivity.findViewById(R.id.email));
        mEditPwd = ((EditText)mLoginActivity.findViewById(R.id.pwd));
        mLogin = (Button) mLoginActivity.findViewById(R.id.login);
        mClear = (Button) mLoginActivity.findViewById(R.id.clear);
    }
    /**
     * a simple test of login.
     * set email and pwd,then click the login button,
     * assert if {@link MatchaActivity} started.
     */
    @Test
    public void testLogin() {
        mEditEmail.setText("reece");
        mEditPwd.setText("111");
        mLogin.performClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(mLoginActivity);
        Intent intent = shadowActivity.getNextStartedActivity();
        Intent tempIntent = new Intent(mLoginActivity, MatchaActivity.class);
        assertTrue(intent.filterEquals(tempIntent));
    }

    /**
     * test clear button.
     * when the button is clicked, email and pwd EditText is set to ''.
     */
    @Test
    public void testClear() {
        mEditEmail.setText("reece");
        mEditPwd.setText("111");
        mClear.performClick();
        assertEquals(mEditEmail.getText().toString(), "");
        assertEquals(mEditPwd.getText().toString(), "");
    }

    /**
     * test login presenter using mock.
     */
    @Test
    public void testLoginPresenter() {
        LoginView view = mock(LoginView.class);
        LoginPresenter loginPresenter = new LoginPresenter(view);
        loginPresenter.login("reece", "111");
        verify(view).onLoginSuccess();

        loginPresenter.login("reece", "11");
        verify(view).onLoginFailed();
    }

    /**
     * test {@link LoginActivity} 's life cycle.
     * if something is wrong, it will break.
     */
    @Test
    public void testLifeCycle() {
        mController.start()
                .create()
                .resume()
                .pause()
                .resume();
    }

}
