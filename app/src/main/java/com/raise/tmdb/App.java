package com.raise.tmdb;

import android.app.Application;
import android.content.Context;

import com.raise.tmdb.util.ToastUtils;
import com.raise.trace.Trace;

/**
 * Created by raise.yang on 17/08/08.
 */

public class App extends Application {

    private static Context m_context;

    @Override
    public void onCreate() {
        super.onCreate();
        m_context = this.getApplicationContext();

        ToastUtils.init(this);
        Trace.level(Trace.DEBUG);
        Trace.showCodePosition(true);
        Trace.logPath(getCacheDir() + "/log");
    }

    public static Context get() {
        return m_context;
    }
}
