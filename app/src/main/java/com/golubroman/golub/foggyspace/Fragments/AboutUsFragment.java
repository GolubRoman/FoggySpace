package com.golubroman.golub.foggyspace.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.golubroman.golub.foggyspace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 15.03.2017.
 */

public class AboutUsFragment extends Fragment {

    @BindView(R.id.about_us_about_us) TextView about_us_about_us;
    @BindView(R.id.foggy_space_about_us) TextView foggy_space_about_us;
    @BindView(R.id.about_foggy_space_about_us) TextView about_foggy_space_about_us;
    @BindView(R.id.top_service_about_us) TextView top_service_about_us;
    @BindView(R.id.about_top_service_about_us) TextView about_top_service_about_us;
    @BindView(R.id.goals_about_us) TextView goals_about_us;
    @BindView(R.id.about_goals_about_us) TextView about_goals_about_us;
    @BindView(R.id.delivery_about_us) TextView delivery_about_us;
    @BindView(R.id.about_delivery_about_us) TextView about_delivery_about_us;
    @BindView(R.id.eliquids_about_us) TextView eliquids_about_us;
    @BindView(R.id.about_eliquids_about_us) TextView about_eliquids_about_us;


    public static AboutUsFragment newInstance() {

        Bundle args = new Bundle();
        AboutUsFragment fragment = new AboutUsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, view);
        setDescription();
        changeFont();
        return view;
    }
    private void setDescription(){
        about_foggy_space_about_us.setText(R.string.foggy_space);
        about_top_service_about_us.setText(R.string.top_service);
        about_goals_about_us.setText(R.string.our_goals);
        about_delivery_about_us.setText(R.string.quick_delivery);
        about_eliquids_about_us.setText(R.string.eliquids);
    }
    private void changeFont(){
        Typeface segoe = Typeface.createFromAsset(this.getResources().getAssets(), "segoe.ttf");
        Typeface segoeBold = Typeface.createFromAsset(this.getResources().getAssets(), "segoe_bold.ttf");
        about_us_about_us.setTypeface(segoe);
        foggy_space_about_us.setTypeface(segoeBold);
        about_foggy_space_about_us.setTypeface(segoe);
        top_service_about_us.setTypeface(segoeBold);
        about_top_service_about_us.setTypeface(segoe);
        goals_about_us.setTypeface(segoeBold);
        about_goals_about_us.setTypeface(segoe);
        delivery_about_us.setTypeface(segoeBold);
        about_delivery_about_us.setTypeface(segoe);
        eliquids_about_us.setTypeface(segoeBold);
        about_eliquids_about_us.setTypeface(segoe);
    }

}
