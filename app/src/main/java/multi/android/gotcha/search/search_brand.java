package multi.android.gotcha.search;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import multi.android.gotcha.DB.Task;
import multi.android.gotcha.DB.CarVO;
import multi.android.gotcha.R;


public class search_brand extends Fragment {
    ListView listView;
    public search_brand() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_brand, container, false);
        listView = v.findViewById(R.id.search_brand);

        Context context = container.getContext();
        String brand="brand";
        final Map<String, String> map = new HashMap<String, String>();
        map.put("method", "brandSearch");
        map.put("brand", brand);
        Task networkTask = new Task();
        networkTask.execute(map);
        SystemClock.sleep(300);
        Gson gson = new Gson();
        List<CarVO> data = gson.fromJson(networkTask.getResult(), new TypeToken<List<CarVO>>() {
        }.getType());

      CarAdapter carAdapter =
                new CarAdapter(context, R.layout.activity_buy_my_car_row, (ArrayList<CarVO>) data);
        listView.setAdapter(carAdapter);

        return v;

    }

}
