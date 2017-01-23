package com.recee.matcha;

import android.content.Intent;
import android.widget.EditText;

import com.recee.matcha.view.activity.LoginActivity;
import com.recee.matcha.view.activity.MatchaActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

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
    /**
     * a simple test of login.
     * set email and pwd,then click the login button,
     * assert if {@link MatchaActivity} started.
     */
    @Test
    public void testLogin() {
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        ((EditText)loginActivity.findViewById(R.id.email)).setText("reece");
        ((EditText)loginActivity.findViewById(R.id.pwd)).setText("111");
        loginActivity.findViewById(R.id.login).performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(loginActivity);
        Intent intent = shadowActivity.getNextStartedActivity();
        Intent tempIntent = new Intent(loginActivity, MatchaActivity.class);

        assertTrue(intent.filterEquals(tempIntent));
    }
}
