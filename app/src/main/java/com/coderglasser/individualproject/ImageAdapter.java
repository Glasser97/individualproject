package com.coderglasser.individualproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter  extends BaseAdapter {
    private Context mContext;
    private ArrayList<Icon> mIcon;

    public ImageAdapter() {
    }

    public ImageAdapter(ArrayList<Icon> mIcon, Context mContext) {
        this.mIcon = mIcon;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mIcon.size();
    }

    @Override
    public Object getItem(int position) {
        return mIcon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        ImageAdapter.ViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid, parent,false);
            holder = new ImageAdapter.ViewHolder();
            holder.icon_img = (ImageView) convertView.findViewById(R.id.icon_img);
            holder.txt_icon = (TextView) convertView.findViewById(R.id.txt_icon);
            convertView.setTag(holder);
        }else{
            holder = (ImageAdapter.ViewHolder)convertView.getTag();
        }
        holder.icon_img.setImageResource(mIcon.get(position).getiId());
        holder.txt_icon.setText(mIcon.get(position).getiName());
        return convertView;
    }

    private static class ViewHolder {
        TextView txt_icon;
        ImageView icon_img;
    }

    public void add(Icon icon) {
        if (mIcon == null) {
            mIcon = new ArrayList<>();
        }
        mIcon.add(icon);
        notifyDataSetChanged();
    }

//    public void remove(int position){
//        if (mIcon != null) {
//            mIcon.remove(position);
//        }
//        notifyDataSetChanged();
//    }
}
