package com.golubroman.golub.foggyspace.Shop.Fragments.Review;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.golubroman.golub.foggyspace.R;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FirebaseAuth firebaseAuth;
    private List<ReviewElement> reviewElementList;
    private Context context;
    private final int VIEW_ITEM_LEFT = 1;
    private final int VIEW_ITEM_RIGHT = 2;


    public ReviewAdapter(List<ReviewElement> reviewElementList,
                         FirebaseAuth firebaseAuth, Context context){
        this.reviewElementList = reviewElementList;
        this.firebaseAuth = firebaseAuth;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        ReviewElement reviewElement = reviewElementList.get(position);
        if(firebaseAuth.getCurrentUser().getEmail().equals(reviewElement.getEmail())){
                return VIEW_ITEM_RIGHT;
        }else{
                return VIEW_ITEM_LEFT;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        View view;
        if(viewType == VIEW_ITEM_LEFT) {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_review_left, parent, false);
            vh = new ReviewElementLeftViewHolder(view, parent.getContext());
        }else if(viewType == VIEW_ITEM_RIGHT) {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_review_right, parent, false);
            vh = new ReviewElementRightViewHolder(view, parent.getContext());
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        boolean hasPhoto = reviewElementList.get(position).getPhotoUrl() != null;
        boolean hasText = reviewElementList.get(position).getText() != null;

        ImageView photo = null, avatar = null;
        TextView message = null, name = null, time = null;
        holder.setIsRecyclable(false);

            if(holder instanceof ReviewAdapter.ReviewElementLeftViewHolder) {
                ReviewElementLeftViewHolder leftHolder = ((ReviewElementLeftViewHolder) holder);
                photo= leftHolder.photo;
                message = leftHolder.message;
                name = leftHolder.name;
                time = leftHolder.time;
                leftHolder.setAvatar(reviewElementList.get(position).getAvatar());

            }else if(holder instanceof ReviewAdapter.ReviewElementRightViewHolder) {
                ReviewElementRightViewHolder rightHolder = ((ReviewElementRightViewHolder) holder);
                photo= rightHolder.photo;
                message = rightHolder.message;
                name = rightHolder.name;
                time = rightHolder.time;
                rightHolder.setAvatar(reviewElementList.get(position).getAvatar());
            }
            initializeViewHolder(photo, name, message, time,
                    position, hasPhoto, hasText);
    }

    public void initializeViewHolder(ImageView photo, TextView name, TextView message,
                                     TextView time, int position, boolean hasPhoto, boolean hasText){
        name.setText(reviewElementList.get(position).getName());
        time.setText(reviewElementList.get(position).getTime());

            if (hasPhoto) {
                photo.setVisibility(View.VISIBLE);
                Glide.with(photo.getContext())
                        .load(reviewElementList.get(position).getPhotoUrl())
                        .into(photo);

            }if (hasText) {
                message.setVisibility(View.VISIBLE);
                message.setText(reviewElementList.get(position).getText());
            }
    }

    @Override
    public int getItemCount() {
        return reviewElementList.size();
    }



    public class ReviewElementLeftViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        @BindView(R.id.backgroundRight) RelativeLayout relativeLayoutRight;
        @BindView(R.id.message) TextView message;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.photo) ImageView photo;
        @BindView(R.id.time) TextView time;
        @BindView(R.id.avatar) ImageView avatar;

        public ReviewElementLeftViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void setAvatar(String photoUrl){
            new SetFriendAvatarTask().execute(photoUrl);
        }

        public ReviewElementLeftViewHolder getViewHolder(){return this;}

        class SetFriendAvatarTask extends AsyncTask<String, Void, Bitmap> {

            @Override
            protected Bitmap doInBackground(String... photoUrls) {
                Bitmap avatar = null;
                try {
                    avatar = Glide.
                            with(context).
                            load(photoUrls[0]).
                            asBitmap().
                            into(100, 100).
                            get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return avatar;
            }

            @Override
            protected void onPostExecute(Bitmap avatar) {
                super.onPostExecute(avatar);
                (getViewHolder()).avatar.setBackground(
                        new BitmapDrawable(getCroppedBitmap(avatar)));
            }

            private Bitmap getCroppedBitmap(Bitmap bitmap) {
                Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                        bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(color);
                canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                        bitmap.getWidth() / 2, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap, rect, rect, paint);
                return output;
            }
        }
    }

    public class ReviewElementRightViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        @BindView(R.id.backgroundRight) RelativeLayout relativeLayoutRight;
        @BindView(R.id.message) TextView message;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.photo) ImageView photo;
        @BindView(R.id.time) TextView time;
        @BindView(R.id.avatar) ImageView avatar;

        public ReviewElementRightViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void setAvatar(String photoUrl){
            new SetFriendAvatarTask().execute(photoUrl);
        }

        public ReviewElementRightViewHolder getViewHolder(){return this;}

        class SetFriendAvatarTask extends AsyncTask<String, Void, Bitmap> {

            @Override
            protected Bitmap doInBackground(String... photoUrls) {
                Bitmap avatar = null;
                try {
                    avatar = Glide.
                            with(context).
                            load(photoUrls[0]).
                            asBitmap().
                            into(100, 100).
                            get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return avatar;
            }

            @Override
            protected void onPostExecute(Bitmap avatar) {
                super.onPostExecute(avatar);
                (getViewHolder()).avatar.setBackground(
                        new BitmapDrawable(getCroppedBitmap(avatar)));
            }

            private Bitmap getCroppedBitmap(Bitmap bitmap) {
                Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                        bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(color);
                canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                        bitmap.getWidth() / 2, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap, rect, rect, paint);
                return output;
            }
        }

    }
}
