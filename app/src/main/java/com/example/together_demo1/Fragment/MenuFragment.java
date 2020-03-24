package com.example.together_demo1.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.together_demo1.R;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private ImageView iv_head;
    private TextView tv_username;
    private ListView menu_list;
    private String[] titlelist={"ETC账户管理系统","停车管理系统","生活助手","公交查询系统"};
    private Activity activity;
    private ArrayAdapter<String> adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View navView = inflater.inflate(R.layout.fragment_menulist, container, false);

        initView(navView);
        return navView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View navView) {
        iv_head = (ImageView) navView.findViewById(R.id.iv_head);
        tv_username = (TextView) navView.findViewById(R.id.tv_username);
        menu_list = (ListView) navView.findViewById(R.id.menu_list);


        adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_dropdown_item_1line, titlelist);
        menu_list.setAdapter(adapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }
}
