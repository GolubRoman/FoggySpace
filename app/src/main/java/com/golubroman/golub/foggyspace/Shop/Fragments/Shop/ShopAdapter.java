package com.golubroman.golub.foggyspace.Shop.Fragments.Shop;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.golubroman.golub.foggyspace.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roman on 24.03.17.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

    private Context context;
    private List<ShopElement> shopElementList;
    private OnCustomClickListener onCustomClickListener;


    public ShopAdapter(List<ShopElement> shopElementList, Context context,
                       OnCustomClickListener onCustomClickListener){
        this.context = context;
        this.shopElementList = shopElementList;
        this.onCustomClickListener = onCustomClickListener;
    }
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_shop, parent, false);
        ShopAdapter.ViewHolder viewHolder = new ShopAdapter.ViewHolder(view, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShopAdapter.ViewHolder holder, final int position) {
        holder.shopTaste.setText(shopElementList.get(position).getName());
        holder.shopDescription.setText(shopElementList.get(position).getDescription());
        holder.shopPrice.setText(Integer.toString(shopElementList.get(position).getPrice()) + '₴');
        holder.addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCustomClickListener.reviewClicked(shopElementList.get(position).getId());
            }
        });
        Glide.with(context).
                load(shopElementList.get(position).getPhotoUrl()).
                into(holder.shopImage);

    }

    @Override
    public int getItemCount() {
        return shopElementList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        @BindView(R.id.row_shop_taste) TextView shopTaste;
        @BindView(R.id.row_shop_description) TextView shopDescription;
        @BindView(R.id.row_shop_price) TextView shopPrice;
        @BindView(R.id.row_shop_price_text) TextView shopPriceText;
        @BindView(R.id.row_shop_image) ImageView shopImage;
        @BindView(R.id.btn_add_review) ImageView addReview;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
            changeFont();
            addReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        private void changeFont(){
            Typeface segoe = Typeface.createFromAsset(context.getAssets(), "segoe.ttf");
            shopTaste.setTypeface(segoe);
            shopDescription.setTypeface(segoe);
            shopPrice.setTypeface(segoe);
            shopPriceText.setTypeface(segoe);
        }
    }
}
