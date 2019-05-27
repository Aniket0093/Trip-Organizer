package com.github.aniketc.android.ui.destinations;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.aniketc.android.R;
import com.github.aniketc.android.model.Place;
import com.github.aniketc.android.ui.trips.AddTripActivity;
import com.github.aniketc.android.utils.FirebaseUtil;

import org.parceler.Parcels;

import java.util.ArrayList;

//Adapter for dislpaying places in RecyclerView in Explore Fragment
public class DestinationsAdapter extends RecyclerView.Adapter<DestinationsAdapter.PlaceViewHolder> {

    ArrayList<Place> places;
    Context mContext;

    public DestinationsAdapter(Context context, ArrayList<Place> p) {
        mContext = context;
        places = p;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.explore_grid_item_bac, parent, false);
        PlaceViewHolder vh = new PlaceViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        holder.name.setText(place.getName());
    //    Glide.with(mContext).load(place.getPhotoUrl()).into(holder.icon);
        Glide.with(mContext)
                .load(place.getPhotoUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        // do something with the bitmap
                        // for demonstration purposes, let's just set it to an ImageView
                        Log.v("Inside Bitmap", "dsfsdf");
                        Palette p = Palette.generate(bitmap);
                        int color = p.getDarkVibrantColor(0xFF333333);
                        holder.icon.setImageBitmap(bitmap);
                        holder.view.setBackgroundColor(color);
                        holder.view.setAlpha(0.5f);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public ImageView icon;
        public ImageButton addBucketList;
        public View viewPallete;

        public ImageButton addLikeList;
        public ImageButton addTripList;

        public View view;


        public PlaceViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            icon = (ImageView) itemView.findViewById(R.id.item_image);
            addBucketList = (ImageButton) itemView.findViewById(R.id.item_bucketlist);
            viewPallete=itemView.findViewById(R.id.vPalette);


            view = itemView.findViewById(R.id.explore_view);
            addBucketList.setOnClickListener(this);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int pos = getAdapterPosition();
            Place place = places.get(pos);
            switch (id) {
                case R.id.item_bucketlist:
                    FirebaseUtil.savePlace(place);
                    FirebaseUtil.bucketPlace(place.getPlaceid());
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.add_bucketlist),
                            Toast.LENGTH_SHORT).show();
                    break;
           /*     case R.id.item_like:
                    FirebaseUtil.savePlace(place);
                    FirebaseUtil.likePlace(place.getPlaceid());
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.add_likedlist),
                            Toast.LENGTH_SHORT).show();
                    break;*/

                case R.id.item_image:
                    Intent intent=new Intent(mContext,AddTripActivity.class);
                    intent.putExtra("SearchedLocation", Parcels.wrap(place));
                    mContext.startActivity(intent);
                    break;

            }
        }
    }
}