package com.golubroman.golub.foggyspace.Shop;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.golubroman.golub.foggyspace.Shop.Fragments.AboutUs.AboutUsFragment;
import com.golubroman.golub.foggyspace.Shop.Fragments.CoilCalculator.CoilCalculatorFragment;
import com.golubroman.golub.foggyspace.Shop.Fragments.ContactUs.ContactUsFragment;
import com.golubroman.golub.foggyspace.Shop.Fragments.Review.ReviewFragment;
import com.golubroman.golub.foggyspace.Shop.Fragments.Shop.ShopFragment;
import com.golubroman.golub.foggyspace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 14.03.2017.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ShopFragment.AddReviewListener {
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;

    Fragment contactUsFragment;
    Fragment aboutUsFragment;
    Fragment calculatorFragment;
    Fragment shopFragment;
    private ActionBarDrawerToggle toggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        calculatorFragment = CoilCalculatorFragment.newInstance();
        contactUsFragment = ContactUsFragment.newInstance();
        aboutUsFragment = AboutUsFragment.newInstance();
        calculatorFragment = CoilCalculatorFragment.newInstance();
        shopFragment = ShopFragment.newInstance();

        replaceFragment(shopFragment, false);

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            replaceFragment(shopFragment, true);
        } else if (id == R.id.nav_coilcalculator) {
            replaceFragment(calculatorFragment, true);
        } else if (id == R.id.nav_about) {
            replaceFragment(aboutUsFragment, true);
        } else if (id == R.id.nav_contact) {
            replaceFragment(contactUsFragment, true);
        } else if (id == R.id.nav_cart) {

        }else if (id == R.id.nav_vk) {
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment, boolean add){
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.
                replace(R.id.fragment_container, fragment);
        if(add)
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void addReview(String idPath) {
        Bundle bundle = new Bundle();
        bundle.putString("idPath", idPath);
        Fragment reviewFragment = ReviewFragment.newInstance();
        reviewFragment.setArguments(bundle);
        replaceFragment(reviewFragment, true);
    }
}
