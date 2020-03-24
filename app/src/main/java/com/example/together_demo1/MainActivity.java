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


public class MainActivity extends AppCompatActivity {

    private Toolbar mytoolbar;
    private FrameLayout content_view;
    private DrawerLayout dl_mydl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initView();
        //设置标题栏
        setActionBar(mytoolbar);
        getActionBar().setTitle("智能交通"); // 不显示程序应用名
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
    }
}
