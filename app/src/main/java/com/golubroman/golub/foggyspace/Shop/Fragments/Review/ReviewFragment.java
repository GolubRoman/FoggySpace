package com.golubroman.golub.foggyspace.Shop.Fragments.Review;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.golubroman.golub.foggyspace.App;
import com.golubroman.golub.foggyspace.R;
import com.golubroman.golub.foggyspace.Shop.Fragments.Shop.ShopElement;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by roman on 11.07.17.
 */

public class ReviewFragment extends Fragment{

    final int DEFAULT_MSG_LENGTH_LIMIT = 300;

    @Inject @Named("Auth") FirebaseAuth firebaseAuth;
    @Inject @Named("Database") FirebaseDatabase firebaseDatabase;
    @BindView(R.id.messageEditText) EditText messageEditText;
    @BindView(R.id.sendButton) ImageView sendButton;
    @BindView(R.id.messageRecyclerView) RecyclerView reviewRecyclerView;

    @OnClick(R.id.sendButton) public void sendButtonClick(){
        sendMessage(messageEditText.getText().toString());
    }

    private List<ReviewElement> reviewElementList;
    private ReviewAdapter reviewAdapter;
    private ChildEventListener childEventListener;
    private DatabaseReference reviewsReference;
    private LinearLayoutManager linearLayoutManager;
    private static String idPath;

    public static ReviewFragment newInstance() {

        Bundle args = new Bundle();
        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, view);
        App.getAppComponent().inject(this);

        sendButton.setEnabled(false);
        setEditTextBehavior();

        reviewElementList = new ArrayList<>();
        reviewRecyclerView.setHasFixedSize(true);
        reviewAdapter = new ReviewAdapter(reviewElementList, firebaseAuth, getActivity());


        linearLayoutManager = new LinearLayoutManager(getContext());
        reviewRecyclerView.setLayoutManager(linearLayoutManager);
        reviewRecyclerView.setAdapter(reviewAdapter);

        idPath = getArguments().getString("idPath");
        reviewsReference = firebaseDatabase.getReference(idPath).child("review");
        childEventListener = setChildEventListener();
        attachChildEventListener(reviewsReference,
                childEventListener);


        /*shopElementList = new ArrayList<>();
        shopRecycler.setHasFixedSize(true);
        shopAdapter = new ShopAdapter(shopElementList, getActivity(), new OnCustomClickListener() {
            @Override
            public void reviewClicked(String idPath) {
                addReviewListener.addReview(idPath);

            }
        });

        shopManager = new LinearLayoutManager(getContext());
        shopRecycler.setLayoutManager(shopManager);
        shopRecycler.setAdapter(shopAdapter);
        childEventListener = setChildEventListener();
        attachChildEventListener(firebaseDatabase.getReference().child("eliquids"), childEventListener);*/

        return view;
    }


    private void sendMessage(String text){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM, HH:mm", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        String date = simpleDateFormat.format(new Date());
        String avatar = firebaseAuth.getCurrentUser().getPhotoUrl().toString();
        ReviewElement reviewElement = new ReviewElement(
                firebaseAuth.getCurrentUser().getDisplayName(),
                text, date, firebaseAuth.getCurrentUser().getEmail(),
                null, avatar);
        reviewsReference.push().setValue(reviewElement);
        messageEditText.setText("");
    }

    @Override
    public void onStop() {
        super.onStop();
        if(childEventListener != null) {
            detachChildEventListener(reviewsReference,
                    childEventListener);
            reviewElementList = new ArrayList<>();
            reviewAdapter.notifyDataSetChanged();
        }
    }

    private void attachChildEventListener(final DatabaseReference databaseReference,
                                          ChildEventListener childEventListener){
        databaseReference.addChildEventListener(childEventListener);
    }

    private void detachChildEventListener(final DatabaseReference  databaseReference,
                                          ChildEventListener childEventListener){
        databaseReference.removeEventListener(childEventListener);
    }

    private ChildEventListener setChildEventListener(){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot != null && dataSnapshot.getValue() != null){
                    ReviewElement reviewElement = dataSnapshot.getValue(ReviewElement.class);
                    reviewElementList.add(reviewElement);
                    reviewAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
    }

    private void setEditTextBehavior(){
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    sendButton.setEnabled(true);
                } else{
                    sendButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        messageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
    }


}
