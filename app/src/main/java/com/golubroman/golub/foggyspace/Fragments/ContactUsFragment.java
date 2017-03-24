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

public class ContactUsFragment extends Fragment {

    @BindView(R.id.contact_us_contact_us) TextView contact_us_contact_us;
    @BindView(R.id.message_contact_us) TextView message_contact_us;

    public static ContactUsFragment newInstance() {

        Bundle args = new Bundle();
        ContactUsFragment fragment = new ContactUsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this, view);
        changeFont();
        return view;
    }
    private void changeFont(){
        Typeface segoe = Typeface.createFromAsset(this.getResources().getAssets(), "segoe.ttf");
        contact_us_contact_us.setTypeface(segoe);
        message_contact_us.setTypeface(segoe);
    }

}
