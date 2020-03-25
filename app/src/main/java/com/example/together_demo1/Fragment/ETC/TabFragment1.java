package com.example.together_demo1.Fragment.ETC;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.together_demo1.Bean.MyCheck;
import com.example.together_demo1.Bean.User;
import com.example.together_demo1.R;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ECT账户管理
 */
public class TabFragment1 extends Fragment implements View.OnClickListener {
    private TextView tv_money;
    private Spinner sp_number;
    private EditText et_nummber;
    private Button btn_find;
    private Button btn_in;
    private Integer banlance;
    private String carid;
    String TAG = "ETC";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LitePal.getDatabase();
        //用户创建数据
       /* User admin = new User("admin", "1", 0);
        User admin1 = new User("admin", "2", 0);
        admin.save();
        admin1.save();*/


        init(view);
    }

    private void init(View rootView) {
        this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
        this.sp_number = (Spinner) rootView.findViewById(R.id.sp_number);
        this.et_nummber = (EditText) rootView.findViewById(R.id.et_nummber);
        this.btn_find = (Button) rootView.findViewById(R.id.btn_find);
        this.btn_in = (Button) rootView.findViewById(R.id.btn_in);

        btn_find.setOnClickListener(this);
        btn_in.setOnClickListener(this);

        //创建spinner
        List<User> carlist = LitePal.select("carid").find(User.class);
        List<String> spinnerlist = new ArrayList<>();
        for (int i = 0; i <carlist.size() ; i++) {
            spinnerlist.add(carlist.get(i).getCarid());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerlist);
        sp_number.setAdapter(spinnerAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find:
                //查询账户
                carid = sp_number.getSelectedItem().toString();
                List<User> users = LitePal.select("banlance").where("carid=?",carid).find(User.class);
                banlance = users.get(0).getBanlance();
                tv_money.setText(banlance.toString());
                Toast.makeText(getContext(),"查询成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_in:
                //充值功能

                User user = new User();
                user.setBanlance(banlance + Integer.valueOf(et_nummber.getText().toString()));
                user.updateAll("carid=?", carid);
                Date date = new Date();
                SimpleDateFormat sm = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                String datetime = sm.format(date);
                Log.d(TAG, "onClick: "+datetime);
                List<User> users2 = LitePal.select("name").where("carid=?",carid).find(User.class);
                String name = users2.get(0).getName();
                MyCheck myCheck = new MyCheck(carid, Integer.valueOf(et_nummber.getText().toString()), name, datetime);
                myCheck.save();
                et_nummber.setText("");
                Toast.makeText(getContext(),"充值成功",Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
