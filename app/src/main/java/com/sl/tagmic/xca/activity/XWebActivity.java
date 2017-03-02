package com.sl.tagmic.xca.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sl.tagmic.xca.AppContext;
import com.sl.tagmic.xca.R;
import com.sl.tagmic.xca.data.PdfEntity;
import com.sl.tagmic.xca.data.UserInfoUtil;
import com.sl.tagmic.xca.view.BottomPopupRemoveWindow;
import com.sl.tagmic.xca.zxing.XHttpUtil;
import com.sl.tagmic.xca.zxing.view.XCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by techssd on 2017/1/10.
 */

public class XWebActivity extends Activity {
    WebView mWebView;
    String code = "";
    ArrayList<PdfEntity> mlist = new ArrayList<PdfEntity>();
    BottomPopupRemoveWindow pop;
    LinearLayout no_his;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        mWebView= (WebView) findViewById(R.id.webview);
        imageView = (ImageView) findViewById(R.id.swich);
        no_his = (LinearLayout) findViewById(R.id.no_his);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.requestFocus();
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        code = getIntent().getExtras().getString("code");
        mWebView.loadUrl("http://www.eeqmcc.com/cgi-bin/manual_get_manual_app.cgi?code="+code+"&lang="+ UserInfoUtil.getLang(this));
        pop = new BottomPopupRemoveWindow(XWebActivity.this, mlist);
        pop.setListener(new BottomPopupRemoveWindow.MuneItemClickListener() {
            @Override
            public void menuItemClick(PdfEntity entity) {
                pop.dismiss();
                UserInfoUtil.setLang(XWebActivity.this, entity.getLang());
                swich();
            }
        });
        imageView.setVisibility(View.GONE);
        load();
    }
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.swich://切换语言//1-简体中文, 2-繁体中文, 3-英文, 4-日語，5-韓語
                pop.showAsDropDown(v, AppContext.metrics.widthPixels / 2 - pop.getWidth() / 2, 0);
                break;
            case R.id.iv_head_left:
                finish();
                break;
        }
    }
        public void swich() {
        for (PdfEntity e : mlist) {
            if (e.getLang().equals(UserInfoUtil.getLang(this))) {
                Toast.makeText(this, "正在加载文件", Toast.LENGTH_SHORT).show();
                no_his.setVisibility(View.GONE);
                mWebView.loadUrl("http://www.eeqmcc.com/cgi-bin/manual_get_manual_app.cgi?code="+code+"&lang="+ UserInfoUtil.getLang(this));
            }
        }
    }
    public void load() {
        XHttpUtil xHttpUtil = new XHttpUtil(this);
        String url = "http://www.eeqmcc.com/cgi-bin/get_pdf.cgi?code=" + code + "&lang=" + UserInfoUtil.getLang(this) + "&plat=2&imei=" + AppContext.getImei(this);
        xHttpUtil.get(url, new XCallBack() {
            @Override
            public void success(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("code").equals("0")) {
                        JSONArray array = object.getJSONArray("result");
                        mlist.clear();
                        for (int i = 0; i < array.length(); i++) {
                            PdfEntity e = new PdfEntity();
                            e.setLang(array.getJSONObject(i).getString("lang"));
                            e.setPdf_url(array.getJSONObject(i).getString("pdf_url"));
                            mlist.add(e);
                            if (e.getLang().equals("1")) {
                                e.setName("简体中文");
                            } else if (e.getLang().equals("2")) {
                                e.setName("繁体中文");
                            } else if (e.getLang().equals("3")) {
                                e.setName("英文");
                            } else if (e.getLang().equals("4")) {
                                e.setName("日文");
                            } else if (e.getLang().equals("5")) {
                                e.setName("韩文");
                            }
                        }
                            no_his.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                            pop.update(mlist);
                    } else {
                        //无结果
                        no_his.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                }
            }

            @Override
            public void error(String response) {

            }
        });
    }
}
