package com.sl.tagmic.xca.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sl.tagmic.xca.R;
import com.sl.tagmic.xca.data.PdfEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by techssd on 2017/1/4.
 */

public class XAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    ArrayList<PdfEntity> mList = null;

    public XAdapter(Context context, ArrayList<PdfEntity> list) {
        this.mContext = context;
        setmList(list);
        mInflater = LayoutInflater.from(mContext);
    }

    public void setmList(ArrayList<PdfEntity> mList) {
        this.mList = mList;
    }

    public void update(ArrayList<PdfEntity> list) {
        setmList(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public PdfEntity getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.listview_item, null);
            holder.pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PdfEntity e = mList.get(position);
        holder.date.setText(dataFormatToyyyyMMdds(Long.parseLong(e.getDate()))+"");
        holder.title.setText(e.getName());
        if (e.getRight_conner().equals("0")) {
            //红色
            holder.date.setBackgroundResource(R.drawable.item_red);
        } else {
            //黄色
            holder.date.setBackgroundResource(R.drawable.item_flo);
        }
        Glide.with(mContext).load(e.getPic()).placeholder(R.drawable.item_no_history).into(holder.pic);
        return convertView;
    }

    class ViewHolder {
        ImageView pic;
        TextView title, date;
    }

    @SuppressLint("SimpleDateFormat")
    public static String dataFormatToyyyyMMdds(long dataSource) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM");
        Date date = new Date(dataSource);
        return format.format(date);
    }
}
