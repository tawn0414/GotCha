package multi.android.gotcha.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import multi.android.gotcha.DB.MemberVO;
import multi.android.gotcha.DB.Task;
import multi.android.gotcha.DB.CarVO;
import multi.android.gotcha.R;

public class search_km extends Fragment {
    ListView listView;
    public search_km() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_search_km, container, false);
        listView = v.findViewById(R.id.search_km);
        Context context = container.getContext();
        String km="km";
        final Map<String, String> map = new HashMap<String, String>();
        map.put("method", "kmSearch");
        map.put("km", km);
        final Task[] networkTask = {new Task()};
        networkTask[0].execute(map);
        SystemClock.sleep(300);
        final Gson gson = new Gson();
        final List<CarVO> carlist = gson.fromJson(networkTask[0].getResult(), new TypeToken<List<CarVO>>() {
        }.getType());
       CarAdapter carAdapter =
                new CarAdapter(context, R.layout.activity_buy_my_car_row, (ArrayList<CarVO>) carlist);
        listView.setAdapter(carAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("msg","ggggggggggggggggg");
                Intent intent = new Intent(getActivity(),ReadActivity.class);
                String kakaoNo=carlist.get(position).getUserId();
                Map<String, String> map = new HashMap<String, String>();
                Log.d("msg",kakaoNo);
                map.put("method", "searchIdInKakao");
                map.put("kakaoNo", kakaoNo);
                networkTask[0] = new Task();
                networkTask[0].execute(map);
                while(networkTask[0].getResult().equals("")){
                    SystemClock.sleep(10);
                }
                Log.d("msg", networkTask[0].getResult());
                MemberVO vo = gson.fromJson(networkTask[0].getResult(),MemberVO.class);

                intent.putExtra("company",carlist.get(position).getBrand());
                intent.putExtra("modelName",carlist.get(position).getModel());
                intent.putExtra("dpm",carlist.get(position).getCc());
                intent.putExtra("fuel",carlist.get(position).getFuel());
                intent.putExtra("mileage",carlist.get(position).getKm());
                intent.putExtra("email",vo.getMem_email());
                intent.putExtra("price",carlist.get(position).getPrice());
                intent.putExtra("carNum",carlist.get(position).getCc());
                intent.putExtra("old",carlist.get(position).getYear());
                intent.putExtra("fuel1",carlist.get(position).getFuel());
                intent.putExtra("transmission",carlist.get(position).getTransmission());
                intent.putExtra("dpm1",carlist.get(position).getCc());
                intent.putExtra("color",carlist.get(position).getColor());
                intent.putExtra("seller_pName",carlist.get(position).getUserId());
                intent.putExtra("seller_email",vo.getMem_email());
                intent.putExtra("seller_phone",vo.getMem_phoneno());
                intent.putExtra("accident",carlist.get(position).getSago());

                startActivity(intent);
            }
        });

        return v;
    }
}
