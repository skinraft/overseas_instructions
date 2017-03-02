package com.sl.tagmic.xca.view;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.sl.tagmic.xca.AppContext;
import com.sl.tagmic.xca.data.PdfEntity;
import com.sl.tagmic.xca.R;
import com.sl.tagmic.xca.adapter.XMenuAdapter;

import java.util.ArrayList;

public class BottomPopupRemoveWindow extends PopupWindow {

    ListView mListview;
    ArrayList<PdfEntity> mlist = new ArrayList<PdfEntity>();
    XMenuAdapter adapter;

    public BottomPopupRemoveWindow(Context context,ArrayList<PdfEntity>list) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainview = inflater.inflate(R.layout.pop_window, null);
        setWidth(AppContext.metrics.widthPixels/3);
        setHeight(LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setContentView(mainview);
        mListview = (ListView) mainview.findViewById(R.id.listview);
        setMlist(list);
        adapter = new XMenuAdapter(context, mlist);
        mListview.setAdapter(adapter);
        mListview.requestFocus();
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   if (null!=listener){
                       listener.menuItemClick(mlist.get(position));
                       dismiss();
                   }
            }
        });
    }

    public void setMlist(ArrayList<PdfEntity> mlist) {
        this.mlist = mlist;
    }

    public void update(ArrayList<PdfEntity> mlist){
        setMlist(mlist);
        adapter.update(mlist);
        mListview.requestFocus();
    }
    private  MuneItemClickListener listener;

    public void setListener(MuneItemClickListener listener) {
        this.listener = listener;
    }

    public interface MuneItemClickListener{
        public void menuItemClick(PdfEntity entity);
    }
}
