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
import java.util.LinkedList;

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private LinkedList<Data> mData;

    public MyAdapter() {
    }

    public MyAdapter(LinkedList<Data> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        ViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent,false);
            holder = new ViewHolder();
            holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            holder.txt_mount = (TextView) convertView.findViewById(R.id.txt_mount);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.img_icon.setImageResource(mData.get(position).getImgId());
        holder.txt_content.setText(mData.get(position).getContent());
        holder.txt_mount.setText(mData.get(position).getMount());
        return convertView;
    }

    private static class ViewHolder {
        TextView txt_content;
        TextView txt_mount;
        ImageView img_icon;
    }

    public void add(Data data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.addFirst(data);
        notifyDataSetChanged();
    }

    public void remove(int position){
        if (mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }
}
