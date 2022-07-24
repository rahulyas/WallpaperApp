package com.example.wallpaperapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallpaperapp.Model.Photo;
import com.example.wallpaperapp.Model.Search;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.SetWallpaper;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    Context context;
    List<Photo> list;

    public Adapter(Context context, List<Photo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter.Viewholder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserintent = new Intent(Intent.ACTION_VIEW);
                browserintent.setData(Uri.parse("https://www.pexels.com/"));
                browserintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserintent);
            }
        });

        Glide.with(context)
                .load(list.get(position).getSrc().getPortrait()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SetWallpaper.class);
                intent.putExtra("image",list.get(position).getSrc().getPortrait());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public Viewholder(View itemview) {
            super(itemview);

            imageView= itemview.findViewById(R.id.image);
            textView= itemview.findViewById(R.id.text);
        }
    }
}
