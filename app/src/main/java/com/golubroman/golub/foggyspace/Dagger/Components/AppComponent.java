package com.golubroman.golub.foggyspace.Dagger.Components;

import com.golubroman.golub.foggyspace.Authentication.SignIn.SignInActivity;
import com.golubroman.golub.foggyspace.Dagger.Modules.FirebaseComponent;
import com.golubroman.golub.foggyspace.Shop.Fragments.Review.ReviewFragment;
import com.golubroman.golub.foggyspace.Shop.Fragments.Shop.ShopFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by roman on 09.07.17.
 */

@Component(modules = {FirebaseComponent.class})
@Singleton
public interface AppComponent {
    void inject(SignInActivity signInActivity);
    void inject(ShopFragment shopFragment);
    void inject(ReviewFragment reviewFragment);
}
