package com.golubroman.golub.foggyspace;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.golubroman.golub.foggyspace.Fragments.AboutUsFragment;
import com.golubroman.golub.foggyspace.Fragments.CoilCalculatorFragment;
import com.golubroman.golub.foggyspace.Fragments.ContactUsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 14.03.2017.
 */

public class NavigationActivity extends ActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;

    Fragment contactUsFragment;
    Fragment aboutUsFragment;
    Fragment calculatorFragment;
    FragmentTransaction fragmentTransaction;

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
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, calculatorFragment).
                addToBackStack(null).
                commit();

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
            // Handle the camera action
        } else if (id == R.id.nav_coilcalculator) {
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            calculatorFragment = CoilCalculatorFragment.newInstance();
            fragmentTransaction.
                    replace(R.id.fragment_container, calculatorFragment).
                    addToBackStack(null).
                    commit();
        } else if (id == R.id.nav_about) {
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            aboutUsFragment = AboutUsFragment.newInstance();
            fragmentTransaction.
                    replace(R.id.fragment_container, aboutUsFragment).
                    addToBackStack(null).
                    commit();
        } else if (id == R.id.nav_contact) {
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            contactUsFragment = ContactUsFragment.newInstance();
            fragmentTransaction.
                    replace(R.id.fragment_container, contactUsFragment).
                    addToBackStack(null).
                    commit();

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_vk) {

        } else if (id == R.id.nav_inst) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
