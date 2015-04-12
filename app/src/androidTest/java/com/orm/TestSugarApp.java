package com.orm;

import android.app.Application;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestLifecycleApplication;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./app/src/main/AndroidManifest.xml", emulateSdk = 18)
public class TestSugarApp extends Application implements TestLifecycleApplication {

    @Test
    public void startEverTestSugarAppAsFirst() {
    }

    @Override
    public void beforeTest(Method method) {
        Log.v("test", "beforeTest");
    }

    @Override
    public void prepareTest(Object test) {
        Log.v("test", "prepareTest");
    }

    @Override
    public void afterTest(Method method) {
        Log.v("test", "afterTest");
    }
}

