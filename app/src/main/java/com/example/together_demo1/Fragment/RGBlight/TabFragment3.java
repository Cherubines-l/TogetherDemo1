package com.example.together_demo1.Fragment.RGBlight;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.together_demo1.Bean.MyCheck;
import com.example.together_demo1.R;

import org.litepal.LitePal;

import java.util.List;

/**
 * 红绿灯模块
 */
public class TabFragment3 extends Fragment implements View.OnClickListener {
    private Spinner check_spinner;
    private Button bt_check_select;
    private TableLayout check_tablelayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teb_fragment3, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        check_spinner = view.findViewById(R.id.check_spinner);
        bt_check_select = view.findViewById(R.id.bt_check_select);
        check_tablelayout = view.findViewById(R.id.check_tablelayout);

        bt_check_select.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_check_select:
                check_tablelayout.removeAllViews();
                if (check_spinner.getSelectedItem().toString().equals("时间升序")) {
                    selects("desc");
                    Toast.makeText(getContext(),"查询成功",Toast.LENGTH_SHORT).show();
                } else {
                    selects("asc");
                    Toast.makeText(getContext(),"查询成功",Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }

    private void selects(String order) {
        List<MyCheck> checkList = LitePal.order("updatetime" + order).find(MyCheck.class);
        tableadd("序号","车号","充值金额(元)","操作人","充值时间");
        for (int i = 0; i < checkList.size(); i++) {
            tableadd(String.valueOf(i+1),checkList.get(i).getCarid(),checkList.get(i).getChangmoney().toString(),checkList.get(i).getOperator(),checkList.get(i).getUpdatetime());
        }

    }

    private void tableadd(String id, String carid, String  money, String opener, String datetime) {
        TableRow tableRow = new TableRow(getContext());
        TextView textView1 = new TextView(getContext());
        TextView textView2 = new TextView(getContext());
        TextView textView3 = new TextView(getContext());
        TextView textView4 = new TextView(getContext());
        TextView textView5 = new TextView(getContext());

        textView1.setText(id);
        textView2.setText(carid);
        textView3.setText(money);
        textView4.setText(opener);
        textView5.setText(datetime);

        textView1.setTextColor(Color.BLACK);
        textView2.setTextColor(Color.BLACK);
        textView3.setTextColor(Color.BLACK);
        textView4.setTextColor(Color.BLACK);
        textView5.setTextColor(Color.BLACK);

        textView1.setGravity(Gravity.CENTER_HORIZONTAL);
        textView2.setGravity(Gravity.CENTER_HORIZONTAL);
        textView3.setGravity(Gravity.CENTER_HORIZONTAL);
        textView4.setGravity(Gravity.CENTER_HORIZONTAL);
        textView5.setGravity(Gravity.CENTER_HORIZONTAL);

        textView1.setPadding(5,3,5,3);
        textView2.setPadding(5,3,5,3);
        textView3.setPadding(5,3,5,3);
        textView4.setPadding(5,3,5,3);
        textView5.setPadding(5,3,5,3);

        textView1.setTextSize(13f);
        textView2.setTextSize(13f);
        textView3.setTextSize(13f);
        textView4.setTextSize(13f);
        textView5.setTextSize(13f);
        tableRow.addView(textView1);
        tableRow.addView(textView2);
        tableRow.addView(textView3);
        tableRow.addView(textView4);
        tableRow.addView(textView5);

        check_tablelayout.addView(tableRow);

    }

}
