package com.golubroman.golub.foggyspace.Shop.Fragments.Shop;

import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.golubroman.golub.foggyspace.App;
import com.golubroman.golub.foggyspace.R;
import com.golubroman.golub.foggyspace.Shop.Fragments.Review.ReviewFragment;
import com.golubroman.golub.foggyspace.Shop.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 15.03.2017.
 */

public class ShopFragment extends Fragment {


    @Inject @Named("Auth") FirebaseAuth firebaseAuth;
    @Inject @Named("Database") FirebaseDatabase firebaseDatabase;
    @BindView(R.id.shop_title) TextView shop_title;
    @BindView(R.id.shop_recycler) RecyclerView shopRecycler;

    private RecyclerView.LayoutManager shopManager;
    private RecyclerView.Adapter shopAdapter;
    private List<ShopElement>  shopElementList;
    private ChildEventListener childEventListener;
    private AddReviewListener addReviewListener;

    public interface AddReviewListener{
        void addReview(String idPath);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addReviewListener = (AddReviewListener)context;
    }

    public static ShopFragment newInstance() {

        Bundle args = new Bundle();
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        App.getAppComponent().inject(this);

        shopElementList = new ArrayList<>();
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
        attachChildEventListener(firebaseDatabase.getReference().child("eliquids"), childEventListener);
        changeFont();
        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        if(childEventListener != null) {
            detachChildEventListener(firebaseDatabase.getReference().child("eliquids"),
                    childEventListener);
            shopElementList = new ArrayList<>();
            shopAdapter.notifyDataSetChanged();
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
                    ShopElement shopElement = dataSnapshot.getValue(ShopElement.class);
                    shopElementList.add(shopElement);
                    shopAdapter.notifyDataSetChanged();
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


    private void changeFont(){
        Typeface segoe = Typeface.createFromAsset(this.getResources().getAssets(), "segoe.ttf");
        shop_title.setTypeface(segoe);
    }

}
