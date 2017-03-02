package com.sl.tagmic.xca.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sl.tagmic.xca.R;
import com.sl.tagmic.xca.data.PdfEntity;

import java.util.ArrayList;

/**
 * Created by techssd on 2017/1/4.
 */

public class XMenuAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    ArrayList<PdfEntity> mList = null;

    public XMenuAdapter(Context context, ArrayList<PdfEntity> list) {
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
            convertView = mInflater.inflate(R.layout.pop_window_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PdfEntity e = mList.get(position);
        holder.title.setText(e.getName());
        return convertView;
    }

    class ViewHolder {
        TextView title;
    }
}
