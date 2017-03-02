package com.sl.tagmic.xca.activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.sl.tagmic.xca.R;
import com.sl.tagmic.xca.fragment.BlankFragment;
import com.sl.tagmic.xca.fragment.BlankLFragment;
import com.sl.tagmic.xca.fragment.BlankRFragment;

public class MainActivity extends FragmentActivity implements BlankFragment.OnFragmentInteractionListener,BlankLFragment.OnFragmentInteractionListener,BlankRFragment.OnFragmentInteractionListener{
    // 定义FragmentTabHost对象
    FragmentTabHost mTabHost;
    // 定义数组来存放Fragment界面
    Class<?> mFragmentArray[] = {BlankFragment.class, BlankLFragment.class, BlankRFragment.class};
    // Tab选项卡的文字
    String mTextviewArray[] = {"扫一扫", "历史", "热门"};
    ////////////////////////////
    FrameLayout r_layout,l_layout,s_layout;
    TextView r_text,l_text,s_text;
    ImageView r_image,l_image,s_image;

    @Override
    protected void onCreate(@Nullable Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);
        initView();
    }
    /**
     * 初始化组件
     */
    @SuppressLint({"CutPasteId", "NewApi"})
    private void initView() {
        r_layout= (FrameLayout) findViewById(R.id.r_layout);
        l_layout= (FrameLayout) findViewById(R.id.l_layout);
        s_layout= (FrameLayout) findViewById(R.id.sao_layout);
        r_text= (TextView) findViewById(R.id.r_text);
        l_text= (TextView) findViewById(R.id.l_text);
        s_text= (TextView) findViewById(R.id.sao_text);
        r_image= (ImageView) findViewById(R.id.r_image);
        l_image= (ImageView) findViewById(R.id.l_image);
        s_image= (ImageView) findViewById(R.id.s_image);
        mTabHost = new FragmentTabHost(this);
        mTabHost.addView(new FrameLayout(this));
        LinearLayout this_layout = (LinearLayout) findViewById(R.id.this_layout);
        this_layout.addView(mTabHost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content);
        mTabHost.getTabWidget().setDividerDrawable(null);

        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
        }
        //////////////////
        mTabHost.setCurrentTab(0);
        s_text.setVisibility(View.VISIBLE);
        l_text.setVisibility(View.INVISIBLE);
        r_text.setVisibility(View.INVISIBLE);
        s_image.setImageResource(R.drawable.l_bg);
        l_image.setImageResource(R.drawable.h_bg);
        r_image.setImageResource(R.drawable.h_bg);
        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) s_layout.getLayoutParams();
        params.bottomMargin=50;
        s_layout.setLayoutParams(params);
        RelativeLayout.LayoutParams params2= (RelativeLayout.LayoutParams) l_layout.getLayoutParams();
        params2.bottomMargin=20;
        l_layout.setLayoutParams(params2);
        RelativeLayout.LayoutParams params3= (RelativeLayout.LayoutParams) r_layout.getLayoutParams();
        params3.bottomMargin=20;
        r_layout.setLayoutParams(params3);
    }


    /**
     * 给Tab按钮设置图标和文字
     */
    @SuppressLint("InflateParams")
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(
                R.layout.x_main_activity_bottom_tabhost_item, null);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        view.setTag(mFragmentArray[index]);
        return view;
    }


    public void onClick(View view){
        int id= view.getId();
        switch (id){
            case R.id.sao_layout://扫一扫
                mTabHost.setCurrentTab(0);
                s_text.setVisibility(View.VISIBLE);
                l_text.setVisibility(View.INVISIBLE);
                r_text.setVisibility(View.INVISIBLE);
                s_image.setImageResource(R.drawable.l_bg);
                l_image.setImageResource(R.drawable.h_bg);
                r_image.setImageResource(R.drawable.h_bg);
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) s_layout.getLayoutParams();
                params.bottomMargin=50;
                s_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams params2= (RelativeLayout.LayoutParams) l_layout.getLayoutParams();
                params2.bottomMargin=20;
                l_layout.setLayoutParams(params2);
                RelativeLayout.LayoutParams params3= (RelativeLayout.LayoutParams) r_layout.getLayoutParams();
                params3.bottomMargin=20;
                r_layout.setLayoutParams(params3);
                break;
            case R.id.l_layout://历史
                mTabHost.setCurrentTab(1);
                l_text.setVisibility(View.VISIBLE);
                s_text.setVisibility(View.INVISIBLE);
                r_text.setVisibility(View.INVISIBLE);
                l_image.setImageResource(R.drawable.l_bg);
                s_image.setImageResource(R.drawable.h_bg);
                r_image.setImageResource(R.drawable.h_bg);
                RelativeLayout.LayoutParams params4= (RelativeLayout.LayoutParams) s_layout.getLayoutParams();
                params4.bottomMargin=20;
                s_layout.setLayoutParams(params4);
                RelativeLayout.LayoutParams params5= (RelativeLayout.LayoutParams) l_layout.getLayoutParams();
                params5.bottomMargin=50;
                l_layout.setLayoutParams(params5);
                RelativeLayout.LayoutParams params6= (RelativeLayout.LayoutParams) r_layout.getLayoutParams();
                params6.bottomMargin=20;
                r_layout.setLayoutParams(params6);
                break;
            case R.id.r_layout://热门
                mTabHost.setCurrentTab(2);
                r_text.setVisibility(View.VISIBLE);
                s_text.setVisibility(View.INVISIBLE);
                l_text.setVisibility(View.INVISIBLE);
                r_image.setImageResource(R.drawable.l_bg);
                l_image.setImageResource(R.drawable.h_bg);
                s_image.setImageResource(R.drawable.h_bg);
                RelativeLayout.LayoutParams params7= (RelativeLayout.LayoutParams) s_layout.getLayoutParams();
                params7.bottomMargin=20;
                s_layout.setLayoutParams(params7);
                RelativeLayout.LayoutParams params8= (RelativeLayout.LayoutParams) l_layout.getLayoutParams();
                params8.bottomMargin=20;
                l_layout.setLayoutParams(params8);
                RelativeLayout.LayoutParams params9= (RelativeLayout.LayoutParams) r_layout.getLayoutParams();
                params9.bottomMargin=50;
                r_layout.setLayoutParams(params9);


                break;
        }

    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
