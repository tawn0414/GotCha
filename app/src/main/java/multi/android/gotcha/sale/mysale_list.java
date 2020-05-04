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
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysale_list);
        mySaleList = findViewById(R.id.mySaleList);
        Map<String, String> map = new HashMap<>();
        map.put("method", "myList");
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
        mySaleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnDelete = view.findViewById(R.id.mySaleDelete);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("method", "myListDelete");
                        Task myListDelete = new Task();
                        myListDelete.execute(map);

                        Intent intent = new Intent(mysale_list.this, mysale_list.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
