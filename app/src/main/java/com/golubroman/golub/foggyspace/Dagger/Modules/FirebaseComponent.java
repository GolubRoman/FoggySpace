package com.golubroman.golub.foggyspace.Dagger.Modules;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roman on 09.07.17.
 */

@Module
public class FirebaseComponent {

    @Provides
    @NonNull
    @Singleton
    @Named("Auth")
    public FirebaseAuth provideAuth(){ return FirebaseAuth.getInstance(); }

    @Provides
    @NonNull
    @Singleton
    @Named("Database")
    public FirebaseDatabase provideDatabase(){ return FirebaseDatabase.getInstance(); }
}
