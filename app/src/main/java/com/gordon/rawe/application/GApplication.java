package com.gordon.rawe.application;

import android.app.Application;

import com.gordon.rawe.db.DBManager;

/**
 * Created by gordon on 16/4/23.
 */
public class GApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.getInstance().configure(getApplicationContext());
    }
}
