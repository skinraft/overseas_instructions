package com.sl.tagmic.xca.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sl.tagmic.xca.AppContext;
import com.sl.tagmic.xca.data.PdfEntity;
import com.sl.tagmic.xca.R;
import com.sl.tagmic.xca.data.UserInfoUtil;
import com.sl.tagmic.xca.adapter.XRAdapter;
import com.sl.tagmic.xca.activity.XWebActivity;
import com.sl.tagmic.xca.view.SwipeRefreshLayout;
import com.sl.tagmic.xca.zxing.XHttpUtil;
import com.sl.tagmic.xca.zxing.view.XCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankRFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankRFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankRFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SwipeRefreshLayout mSwipeLayout;
    ListView mListView;
    ArrayList<PdfEntity>mList=new ArrayList<PdfEntity>();
    XRAdapter adapter;
    int page=0;
    LinearLayout no_his;
    public BlankRFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankLFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankRFragment newInstance(String param1, String param2) {
        BlankRFragment fragment = new BlankRFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank_l, container, false);
        no_his= (LinearLayout) view. findViewById(R.id.no_his);
        mListView = (ListView)view. findViewById(R.id.id_listview);
        mSwipeLayout = (SwipeRefreshLayout)view. findViewById(R.id.id_swipe_ly);
        adapter=new XRAdapter(getActivity(),mList);
        getList(page);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PdfEntity e=mList.get(position);
                if (null!=e){
                    if (Build.VERSION.SDK_INT >= 21) {
                        startActivity(new Intent(getActivity(), XWebActivity.class).putExtra("code",e.getCode()).putExtra("swich",true));
                    } else {
                        startActivity(new Intent(getActivity(), XWebActivity.class).putExtra("code",e.getCode()).putExtra("swich",true));
                    }
                }
            }
        });
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                  page=1;
                getList(page);
            }
        });
        mSwipeLayout.setOnLoadListener(new SwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                 page++;
                getList(page);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void getList(final int page){
        XHttpUtil xHttpUtil=new XHttpUtil(getActivity());
        String url="http://www.eeqmcc.com/cgi-bin/get_hot_history_pdf.cgi?lang="+ UserInfoUtil.getLang(getActivity())+"&plat=2&imei="+ AppContext.getImei(getActivity())+"&type=0"+"&idx="+page+"&num=30";
        xHttpUtil.get(url, new XCallBack() {
            @Override
            public void success(String response) {
                no_his.setVisibility(View.GONE);
                try {
                    JSONObject object = new JSONObject(response);
                    ArrayList<PdfEntity>list=new ArrayList<PdfEntity>();
                    if (object.getString("code").equals("0")) {
                        JSONArray array = object.getJSONArray("result");
                        for (int i = 0; i < array.length(); i++) {
                            PdfEntity e = new PdfEntity();
                            e.setName(array.getJSONObject(i).getString("name"));
                            e.setCode(array.getJSONObject(i).getString("code"));
                            e.setPic(array.getJSONObject(i).getString("pic"));
                            list.add(e);
                        }
                       if (page!=0){
                           mList.addAll(list);
                       }else{
                           mList=list;
                       }
                        if (mList.isEmpty()){
                            no_his.setVisibility(View.VISIBLE);
                        }else{
                            no_his.setVisibility(View.GONE);
                            adapter.update(mList);
                        }
                    }
                } catch (JSONException e) {
                    no_his.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void error(String response) {
                no_his.setVisibility(View.VISIBLE);
            }
        });
    }
}
