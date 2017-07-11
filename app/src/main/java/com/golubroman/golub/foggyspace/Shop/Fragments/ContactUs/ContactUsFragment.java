package com.golubroman.golub.foggyspace.Shop.Fragments.ContactUs;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.golubroman.golub.foggyspace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 15.03.2017.
 */

public class ContactUsFragment extends Fragment {

    @BindView(R.id.contact_us_contact_us) TextView contact_us_contact_us;
    @BindView(R.id.message_contact_us) TextView message_contact_us;
    @BindView(R.id.name_contact_us) TextView name_contact_us;
    @BindView(R.id.cellphone_contact_us) TextView cellphone_contact_us;
    @BindView(R.id.subject_contact_us) TextView subject_contact_us;
    @BindView(R.id.edit_subject_contact_us) EditText edit_subject_contact_us;
    @BindView(R.id.edit_name_contact_us) EditText edit_name_contact_us;
    @BindView(R.id.edit_cellphone_contact_us) EditText edit_cellphone_contact_us;
    @BindView(R.id.edit_message_contact_us) EditText edit_message_contact_us;
    @BindView(R.id.btn_contact_us) ImageView btn_contact_us;

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
        btn_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO = {"botanique250198@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                if(fullInfo(edit_name_contact_us.getText().toString()) &&
                        fullInfo(edit_subject_contact_us.getText().toString()) &&
                        fullInfo(edit_message_contact_us.getText().toString())) {

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, edit_subject_contact_us.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, edit_message_contact_us.getText().toString() +
                            "\n\n" + edit_cellphone_contact_us.getText().toString() +
                            "\n" + edit_name_contact_us.getText().toString());
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(getContext(), "Not all of neccessary information was inputed!", Toast.LENGTH_SHORT).show();
            }
        });
        changeFont();
        return view;
    }
    private boolean fullInfo(String s){
        if(s.trim().equals("") || s == null)
            return false;
        else
            return true;
    }
    private void changeFont(){
        Typeface segoe = Typeface.createFromAsset(this.getResources().getAssets(), "segoe.ttf");
        contact_us_contact_us.setTypeface(segoe);
        subject_contact_us.setTypeface(segoe);
        message_contact_us.setTypeface(segoe);
        name_contact_us.setTypeface(segoe);
        cellphone_contact_us.setTypeface(segoe);
    }

}
