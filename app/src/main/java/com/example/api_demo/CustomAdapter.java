package com.example.api_demo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Activity context;
    ArrayList<RetrofitModel.Data> modelList;

    public CustomAdapter(Activity context, ArrayList<RetrofitModel.Data> model) {
        this.context = context;
        this.modelList = model;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView tvId, tvFirstname, tvLastname, tvEmail;
        ImageView ivAvtar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            v = inflater.inflate(R.layout.activity_customlistview, null);
            holder = new ViewHolder();
            holder.tvId = v.findViewById(R.id.tvId);
            holder.tvFirstname = v.findViewById(R.id.tvFirstName);
            holder.tvLastname = v.findViewById(R.id.tvLastName);
            holder.tvEmail = v.findViewById(R.id.tvEmail);
            holder.ivAvtar = v.findViewById(R.id.ivProfile);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        RetrofitModel.Data model = modelList.get(position);

        holder.tvId.setText(String.valueOf(model.getId()));
        holder.tvFirstname.setText(model.getFirst_name());
        holder.tvLastname.setText(model.getLast_name());
        holder.tvEmail.setText(model.getEmail());
//        holder.ivAvtar.setImageResource(model.getImage());
        String imageUrl = model.getAvatar();

        Glide.with(context)
                .load(imageUrl)
                .into(holder.ivAvtar);

        return v;
    }
}
