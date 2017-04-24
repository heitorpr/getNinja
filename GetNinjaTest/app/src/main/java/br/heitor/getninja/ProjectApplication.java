package br.heitor.getninja;

import android.app.Application;
import android.content.Context;

import java.io.File;

public class ProjectApplication extends Application {
    private static ProjectApplication instance;

    public static ProjectApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public File getCacheRequestDir() {
        File cacheDirectory = new File(getAppContext().getCacheDir(), "responses");
        if (!cacheDirectory.exists()) {
            cacheDirectory.mkdirs();
        }

        return cacheDirectory;
    }
}
