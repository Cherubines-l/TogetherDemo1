package com.example.together_demo1;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.together_demo1.Fragment.ETC.TabFragment1;
import com.example.together_demo1.Fragment.RGBlight.TabFragment2;
import com.example.together_demo1.Fragment.Check.TabFragment3;
import com.example.together_demo1.Fragment.Peccancy.TabFragment4;
import com.example.together_demo1.Fragment.Bus.TabFragment5;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar mytoolbar;
    private FrameLayout content_view;
    private DrawerLayout dl_mydl;
    private Fragment currentFragment;
    private List<Fragment> tabFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
        //设置标题栏
        setActionBar(mytoolbar);
        getActionBar().setTitle("我的账户"); // 不显示程序应用名
        mytoolbar.setTitleTextColor(Color.WHITE);
        mytoolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp); // 在toolbar最左边添加icon

        mytoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_mydl.openDrawer(GravityCompat.START);
            }
        });

        //设置Drawer
        dl_mydl.setStatusBarBackgroundColor(Color.parseColor("#000000"));

        //侧滑抽屉监听
        dl_mydl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                View mContent = dl_mydl.getChildAt(0);
                View mMenu = view;
                float scale = 1 - v;
                float rightScale = 0.9f + scale *0.1f;
                float leftScale = 0.5f + v * 0.5f;
                mMenu.setAlpha(leftScale);
                mMenu.setScaleX(leftScale);
                mMenu.setScaleY(leftScale);
                mContent.setPivotX(0);
                mContent.setPivotY(mContent.getHeight() * 1/2);
                mContent.setScaleX(rightScale);
                mContent.setScaleY(rightScale);
                mContent.setTranslationX(mMenu.getWidth() * v);
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {


            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    private void initView() {
        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        content_view = (FrameLayout) findViewById(R.id.content_view);
        dl_mydl = (DrawerLayout) findViewById(R.id.dl_mydl);

        //初始化碎片列表
        tabFragments.add(new TabFragment1());
        tabFragments.add(new TabFragment2());
        tabFragments.add(new TabFragment3());
        tabFragments.add(new TabFragment4());
        tabFragments.add(new TabFragment5());
        //界面显示fragment
        currentFragment = tabFragments.get(0);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content_view, currentFragment).commit();
    }

    /**
     * 切换主视图的fragment，避免重复实例化加载
     * @param position
     */
    public void switchFragment(int position)    {

        Fragment fragment = tabFragments.get(position);
        if (currentFragment != fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (fragment.isAdded()) {
                transaction.hide(currentFragment)
                        .show(fragment)
                        .commit();
            } else {
                transaction.hide(currentFragment)
                        .add(R.id.content_view, fragment)
                        .commit();
            }
            currentFragment = fragment;
        }
    }
}
