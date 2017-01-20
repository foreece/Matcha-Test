package com.recee.matcha.core;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author Reece
 * @version V1.0
 * @Date 1/20/17
 * @Description
 */

public class MatchaApp extends Application {
    private static MatchaApp INSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Fresco.initialize(this);
    }

    public static MatchaApp getInstance() {
        return INSTANCE;
    }


}
