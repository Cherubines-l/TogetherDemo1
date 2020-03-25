package com.example.together_demo1.Fragment.Bus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.together_demo1.R;
import com.example.together_demo1.Uitl.OkhttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * 公交车查询
 */
public class TabFragment5 extends Fragment {
    String TAG = "BUS";
    private TextView textView5;
    private TextView tv_road;
    private ExpandableListView busstoplist;
    private List<String> stationlists = new ArrayList<>();
    private List<List<String>> buslists = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teb_fragment5, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

    }

    private void init(View view) {
        textView5=view.findViewById(R.id.textView5);
        tv_road=view.findViewById(R.id.tv_road);
        busstoplist=view.findViewById(R.id.busstoplist);
        getstop();
    }


    //获取车站信息
    private void getstop() {
        OkhttpUtil okhttpUtil = new OkhttpUtil();
        okhttpUtil.getDataAsyn("https://way.jd.com/jisuapi/transitLine?city=连云港&transitno=6&appkey=35e8d46212d4133eae672c715f9b9d56", new OkhttpUtil.NetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                String str_body = body.string();
                Log.d(TAG, "success: " + str_body);
                setData(str_body);
            }

            @Override
            public void failed(Call call, IOException e) {
                Log.d(TAG, "failed: " + e.toString());
            }
        });
    }

    private void setData(final String str_body) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setjson(str_body);
                //造车辆数据
               /* for (int i = 0; i <stationlists.size() ; i++) {
                    List<String> buss=new ArrayList<String>();
                    for (int j = 0; j <2 ; j++) {
                        buss.add(j + "号公交车");
                    }
                    buslists.add(buss);
                }*/
                List<String> buss=new ArrayList<String>();
                buss.add("一号公交车");
                buss.add("二号公交车");

                BusListAdapter busListAdapter = new BusListAdapter(stationlists, buss, getContext());
                busstoplist.setAdapter(busListAdapter);

            }
        });
    }

    private void setjson(String str_body) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(str_body);
        JsonObject result = (JsonObject) jsonObject.get("result");
        JsonArray resultarry = (JsonArray) result.get("result");
        JsonObject list = (JsonObject) resultarry.get(0);
//        String transitno = list.get("transitno").toString();
        String transitno = list.get("transitno").toString().replace("\"","");
        String startstation = list.get("startstation").toString().replace("\"","");
        String endstation = list.get("endstation").toString().replace("\"","");
        Log.d(TAG, "setjson: " + transitno + startstation + endstation);
        tv_road.setText(transitno +"\t"+ startstation+"————" + endstation);
        JsonArray stationlist = (JsonArray) list.get("list");
        for (int i = 0; i < stationlist.size(); i++) {
            JsonObject station = (JsonObject) stationlist.get(i);
            String s = station.get("station").toString().replace("\"","");
            Log.d(TAG, "setjson: "+i+s);
            stationlists.add(i + "." + s);
        }
    }
}
