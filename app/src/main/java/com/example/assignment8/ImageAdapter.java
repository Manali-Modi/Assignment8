package com.example.assignment8;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    Context ctx;
    ArrayList<String> allImages;

    public ImageAdapter(Context ct, ArrayList<String> allImages) {
        ctx = ct;
        this.allImages = allImages;
        Log.d("images3", allImages.toString());
    }

    @NonNull
    @Override
    public ImageAdapter.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(LayoutInflater.from(ctx).inflate(R.layout.images_row, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageHolder holder, int position) {
        String filePath = allImages.get(position);
        Log.d("images4", filePath);
        Glide.with(ctx).load(filePath).into(holder.imgImage);
    }

    @Override
    public int getItemCount() {
        return allImages.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ImageView imgImage;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.img_image);
        }
    }
}
