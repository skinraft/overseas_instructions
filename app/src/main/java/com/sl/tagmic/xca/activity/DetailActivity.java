//package com.sl.tagmic.xca;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.artifex.mupdflib.UserInfoUtil;
//import com.sl.tagmic.xca.view.BottomPopupRemoveWindow;
//import com.sl.tagmic.xca.zxing.XHttpUtil;
//import com.sl.tagmic.xca.zxing.view.XCallBack;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.util.ArrayList;
//
//import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
//import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
//import es.voghdev.pdfviewpager.library.remote.DownloadFile;
//import es.voghdev.pdfviewpager.library.util.FileUtil;
//
//public class DetailActivity extends Activity implements DownloadFile.Listener, View.OnClickListener {
//
//    RemotePDFViewPager remotePDFViewPager;
//    PDFPagerAdapter adapter;
//    FrameLayout content;
//    ImageView imageView;
//    String code = "";
//    LinearLayout no_his;
//    BottomPopupRemoveWindow pop;
//    ArrayList<PdfEntity> mlist = new ArrayList<PdfEntity>();
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//        code = getIntent().getExtras().getString("code");
//        imageView = (ImageView) findViewById(R.id.swich);
//        no_his = (LinearLayout) findViewById(R.id.no_his);
//        content = (FrameLayout) findViewById(R.id.content);
//        pop = new BottomPopupRemoveWindow(DetailActivity.this, mlist);
//        pop.setListener(new BottomPopupRemoveWindow.MuneItemClickListener() {
//            @Override
//            public void menuItemClick(PdfEntity entity) {
//                pop.dismiss();
//                UserInfoUtil.setLang(DetailActivity.this, entity.getLang());
//                swich();
//            }
//        });
//        load();
//    }
//
//    private void launchOpenPDFIntent(String destinationPath) {
//        File fi = new File(destinationPath);
//        Uri uri = Uri.parse(fi.getAbsolutePath());
//        Intent intent = new Intent(this, com.artifex.mupdflib.MuPDFActivity.class);
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(uri);
//        //if document protected with password
//        intent.putExtra("password", "encrypted PDF password");
//        //if you need highlight link boxes
//        intent.putExtra("linkhighlight", true);
//        //if you don't need device sleep on reading document
//        intent.putExtra("idleenabled", false);
//        //set true value for horizontal page scrolling, false value for vertical page scrolling
//        intent.putExtra("horizontalscrolling", true);
//        //document name
//        intent.putExtra("docname", "PDF document name");
//        startActivity(intent);
//    }
//    public void load() {
//        XHttpUtil xHttpUtil = new XHttpUtil(this);
//        String url = "http://www.eeqmcc.com/cgi-bin/get_pdf.cgi?code=" + code + "&lang=" + UserInfoUtil.getLang(this) + "&plat=2&imei=" + AppContext.getImei(this);
//        xHttpUtil.get(url, new XCallBack() {
//            @Override
//            public void success(String response) {
//                try {
//                    JSONObject object = new JSONObject(response);
//                    if (object.getString("code").equals("0")) {
//                        JSONArray array = object.getJSONArray("result");
//                        String url = "";
//                        mlist.clear();
//                        for (int i = 0; i < array.length(); i++) {
//                            PdfEntity e = new PdfEntity();
//                            e.setLang(array.getJSONObject(i).getString("lang"));
//                            e.setPdf_url(array.getJSONObject(i).getString("pdf_url"));
//                            mlist.add(e);
//                            if (UserInfoUtil.getLang(DetailActivity.this).equals(array.getJSONObject(i).getString("lang"))) {
//                                url = array.getJSONObject(i).getString("pdf_url");
//                            }
//                            if (e.getLang().equals("1")) {
//                                e.setName("简体中文");
//                            } else if (e.getLang().equals("2")) {
//                                e.setName("繁体中文");
//                            } else if (e.getLang().equals("3")) {
//                                e.setName("英文");
//                            } else if (e.getLang().equals("4")) {
//                                e.setName("日文");
//                            } else if (e.getLang().equals("5")) {
//                                e.setName("韩文");
//                            }
//                        }
//                        if (mlist.isEmpty()) {
//                            no_his.setVisibility(View.VISIBLE);
//                            imageView.setVisibility(View.GONE);
//                        } else {
//                            if ("".equals(url)) {
//                                url = array.getJSONObject(0).getString("pdf_url");
//                                remotePDFViewPager = new RemotePDFViewPager(DetailActivity.this, url, DetailActivity.this);
//                                Toast.makeText(DetailActivity.this, "正在加载文件", Toast.LENGTH_SHORT).show();
//                                no_his.setVisibility(View.GONE);
//                            } else {
//                                remotePDFViewPager = new RemotePDFViewPager(DetailActivity.this, url, DetailActivity.this);
//                                Toast.makeText(DetailActivity.this, "正在加载文件", Toast.LENGTH_SHORT).show();
//                                no_his.setVisibility(View.GONE);
//                            }
//                            //
//                            imageView.setVisibility(View.VISIBLE);
//                            pop.update(mlist);
//                        }
//                    } else {
//                        //无结果
//                        no_his.setVisibility(View.VISIBLE);
//                        imageView.setVisibility(View.GONE);
//                    }
//                } catch (JSONException e) {
//                }
//            }
//
//            @Override
//            public void error(String response) {
//
//            }
//        });
//    }
//
//    public void swich() {
//        for (PdfEntity e : mlist) {
//            if (e.getLang().equals(UserInfoUtil.getLang(this))) {
//                remotePDFViewPager = new RemotePDFViewPager(DetailActivity.this, e.getPdf_url(), DetailActivity.this);
//                Toast.makeText(DetailActivity.this, "正在加载文件", Toast.LENGTH_SHORT).show();
//                no_his.setVisibility(View.GONE);
//            }
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.swich://切换语言//1-简体中文, 2-繁体中文, 3-英文, 4-日語，5-韓語
//                pop.showAsDropDown(v, AppContext.metrics.widthPixels / 2 - pop.getWidth() / 2, 0);
//                break;
//            case R.id.iv_head_left:
//                finish();
//                break;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (adapter != null) {
//            adapter.close();
//            adapter = null;
//        }
//    }
//
//    @Override
//    public void onSuccess(String url, String destinationPath) {
//        // That's the positive case. PDF Download went fine
//        if (Build.VERSION.SDK_INT >= 21) {
//            adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
//            remotePDFViewPager.setAdapter(adapter);
//            updateLayout();
//        } else {
//            launchOpenPDFIntent(destinationPath);
//        }
//    }
//
//    @Override
//    public void onFailure(Exception e) {
//        // This will be called if download fails
//    }
//
//    @Override
//    public void onProgressUpdate(int progresss, int total) {
//    }
//
//    public void updateLayout() {
//        content.removeAllViewsInLayout();
//        content.addView(remotePDFViewPager,
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//    }
//}
