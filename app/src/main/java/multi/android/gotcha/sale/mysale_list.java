package multi.android.gotcha.sale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import multi.android.gotcha.DB.Task;
import multi.android.gotcha.DB.mysaleVO;
import multi.android.gotcha.R;

public class mysale_list extends AppCompatActivity {
    ListView mySaleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysale_list);
        mySaleList = findViewById(R.id.mySaleList);
        Intent intent = getIntent();
        String userId = intent.getStringExtra("name");
        Map<String, String> map = new HashMap<>();
        map.put("method", "myList");
        map.put("userId",userId);
        Task myList = new Task();
        myList.execute(map);
        while (myList.getResult().equals("")) {
            SystemClock.sleep(10);
        }
        Log.d("check","myList.getResult() ======== "+myList.getResult());
        Gson gson = new Gson();
        List<mysaleVO> list = new ArrayList<>();
        mysaleVO[] array = gson.fromJson(myList.getResult(),mysaleVO[].class);
        list = Arrays.asList(array);

        mysaleAdapter adapter = new mysaleAdapter(this,list,mySaleList);
        mySaleList.setAdapter(adapter);
    }
}
