package com.golubroman.golub.foggyspace;

import android.app.Application;

import com.golubroman.golub.foggyspace.Dagger.Components.AppComponent;
import com.golubroman.golub.foggyspace.Dagger.Components.DaggerAppComponent;
import com.golubroman.golub.foggyspace.Dagger.Modules.FirebaseComponent;

/**
 * Created by roman on 09.07.17.
 */

public class App extends Application{

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        buildAppComponent();
    }

    public static void buildAppComponent(){
        component = DaggerAppComponent.builder().
                firebaseComponent(new FirebaseComponent()).
                build();
    }

    public static AppComponent getAppComponent(){
        return component;
    }
}
