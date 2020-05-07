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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import multi.android.gotcha.DB.MemberVO;
import multi.android.gotcha.DB.SalePictureVO;
import multi.android.gotcha.DB.Task;
import multi.android.gotcha.DB.CarVO;
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

    Button search_btn;
    EditText search_text;

    List<CarVO> carlist;
    List<MemberVO> memberlist;
    List<SalePictureVO> imglist;
    String profile ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.pager);
        Intent intent = getIntent();
        profile=intent.getStringExtra("profile");
        fragmentArrayList.add(search_km);
        fragmentArrayList.add(search_fuel);
        fragmentArrayList.add(search_brand);

        //검색어 가져와서 검색하기
        search_text=findViewById(R.id.search_text);
        search_btn =findViewById(R.id.search_btn);

        listView=findViewById(R.id.search_model);


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
                    map.put("method", "modelSearch");
                    map.put("model", model);
                    final Task[] networkTask = {new Task()};
                    networkTask[0].execute(map);
                    SystemClock.sleep(300);
                    final Gson gson = new Gson();
                    carlist = gson.fromJson(networkTask[0].getResult(), new TypeToken<List<CarVO>>(){}.getType());
                    listView=findViewById(R.id.search_model);
                    CarAdapter carAdapter =
                            new CarAdapter(searchpage.this,R.layout.activity_buy_my_car_row, (ArrayList<CarVO>) carlist);
                    listView.setAdapter(carAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.d("msg","ggggggggggggggggg");

                            Intent intent = new Intent(searchpage.this,ReadActivity.class);
                            String kakaoNo=carlist.get(position).getUserId();
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("method", "searchIdInKakao");
                            map.put("kakaoNo", kakaoNo);
                            Log.d("msg",kakaoNo);
                            networkTask[0] = new Task();
                            networkTask[0].execute(map);
                            while(networkTask[0].getResult().equals("")){
                                SystemClock.sleep(10);
                            }
                            Log.d("msg",networkTask[0].getResult());
                            MemberVO memberlist = gson.fromJson(networkTask[0].getResult(),MemberVO.class);


                            String saleNo=carlist.get(position).getSaleNum();
                            Map<String, String> map2 = new HashMap<String, String>();
                            map2.put("method","findImageByCarSale");
                            map2.put("salenum",saleNo);
                            Log.d("msg",saleNo);
                            networkTask[0] = new Task();
                            networkTask[0].execute(map2);
                            while(networkTask[0].getResult().equals("")){
                                SystemClock.sleep(10);
                            }
                            Log.d("msg",networkTask[0].getResult());
                            SalePictureVO imglist = gson.fromJson(networkTask[0].getResult(),SalePictureVO.class);



                            intent.putExtra("company",carlist.get(position).getBrand());
                            intent.putExtra("modelName",carlist.get(position).getModel());
                            intent.putExtra("dpm",carlist.get(position).getCc());
                            intent.putExtra("fuel",carlist.get(position).getFuel());
                            intent.putExtra("mileage",carlist.get(position).getKm());
                            intent.putExtra("email",memberlist.getMem_email());
                            intent.putExtra("price",carlist.get(position).getPrice());
                            intent.putExtra("carNum",carlist.get(position).getCc());
                            intent.putExtra("old",carlist.get(position).getYear());
                            intent.putExtra("fuel1",carlist.get(position).getFuel());
                            intent.putExtra("transmission",carlist.get(position).getTransmission());
                            intent.putExtra("dpm1",carlist.get(position).getCc());
                            intent.putExtra("color",carlist.get(position).getColor());
                            intent.putExtra("seller_pName",carlist.get(position).getUserId());
                            intent.putExtra("seller_email",memberlist.getMem_email());
                            intent.putExtra("seller_phone",memberlist.getMem_phoneno());
                            intent.putExtra("accident",carlist.get(position).getSago());

                            intent.putExtra("pic1",imglist.getPicture1());
                            intent.putExtra("pic2",imglist.getPicture2());
                            intent.putExtra("pic3",imglist.getPicture3());
                            intent.putExtra("pic4",imglist.getPicture4());
                            startActivity(intent);
                        }
                    });


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
