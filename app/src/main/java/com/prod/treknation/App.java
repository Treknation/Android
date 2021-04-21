package com.prod.treknation;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.amplitude.api.Amplitude;

public class App extends MultiDexApplication {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize
        Amplitude.getInstance().initialize(this, "afb5037c98fad01eabca21e3a49d4688");
        Amplitude.getInstance().enableCoppaControl();
        MultiDex.install(this);

        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
