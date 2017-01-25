package com.recee.matcha;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * @author Reece
 * @version V1.0
 * @Date 1/22/17
 * @Description
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginTest {
    private LoginActivity mLoginActivity;
    private EditText mEditEmail;
    private EditText mEditPwd;
    private Button mLogin;
    private Button mClear;

    @Before
    public void setUp() {
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


}
