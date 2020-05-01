package multi.android.gotcha.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import multi.android.gotcha.DB.Task;
import multi.android.gotcha.DB.carVO;
import multi.android.gotcha.R;


public class searchpage extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;


    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    String[] titleList ={"제조사","연료","주행거리"};
    search_km  search_km = new search_km();
    search_fuel search_fuel = new search_fuel();
    search_brand search_brand = new search_brand();

    ListView listView;

    ArrayList<carVO> carlist;
    Button search_btn;
    EditText search_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.pager);

        fragmentArrayList.add(search_km);
        fragmentArrayList.add(search_fuel);
        fragmentArrayList.add(search_brand);

        //검색어 가져와서 검색하기
        search_text=findViewById(R.id.search_text);
        search_btn =findViewById(R.id.search_btn);

        //

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model = search_text.getText().toString();
                if (search_text.getText().toString().length() == 0) {
                    //공백일 때 처리할 내용
                    Intent intent = new Intent(getApplicationContext(),searchpage2.class);
                    startActivity(intent);
                } else {
                    //공백이 아닐 때 처리할 내용
                    //모델검색메소드 실행 검색어 일단받아는 옴

                    final Map<String, String> map = new HashMap<String, String>();
                    map.put("method", "searchmodel");
                    map.put("model", model);
                    Task networkTask = new Task();
                    networkTask.execute(map);
                    SystemClock.sleep(300);
                    Gson gson = new Gson();
                    List<carVO> data = gson.fromJson(networkTask.getResult(), new TypeToken<List<carVO>>(){}.getType());
                    listView=findViewById(R.id.search_model);
                    CarAdapter carAdapter =
                            new CarAdapter(searchpage.this,R.layout.activity_buy_my_car_row, (ArrayList<carVO>) data);
                    listView.setAdapter(carAdapter);
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(),fragmentArrayList.size());
        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    class FragAdapter extends FragmentStatePagerAdapter {

        public FragAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position){
            return titleList[position];
        }
    }



}
