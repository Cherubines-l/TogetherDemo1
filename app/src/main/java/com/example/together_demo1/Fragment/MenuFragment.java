package com.example.together_demo1.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.together_demo1.Main2Activity;
import com.example.together_demo1.MainActivity;
import com.example.together_demo1.R;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private ImageView iv_head;
    private TextView tv_username;
    private ListView menu_list;
    private String[] titlelist={"我的账户","账单管理","红绿灯模块","违章查看","公交线路查询"};
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
        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity activity = (MainActivity) getActivity();
                DrawerLayout mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.dl_mydl);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                activity.switchFragment(position);
                activity.getActionBar().setTitle(titlelist[position]);

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }
}
