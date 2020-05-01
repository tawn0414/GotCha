package multi.android.gotcha.sale;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> upstream/master
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import multi.android.gotcha.DB.Task;
import multi.android.gotcha.MainActivity;
import multi.android.gotcha.R;

public class car_regist extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    String[] titleList ={"차량정보","가격정보","사진","차량등록"};
    car_info car_info = new car_info();
    car_price car_price = new car_price();
    car_pictures car_pictures = new car_pictures();
    car_finish car_finish = new car_finish();

    String carNum,from;

    boolean permission_state;
    String brand="",fuel="",transmission="",color="",model="",year="",displacement="",km="",sago="";
    String price="",detail="";
    String pic1="",pic2="",pic3="",pic4="";
    Button btnBrand,btnModel,btnFuel,btnTransmission,btnColor;
    EditText yearInfo;
    boolean finalCheck = false;

    //Task saleInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_regist);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.pager);
        btnBrand = findViewById(R.id.btnBrand);
        btnFuel = findViewById(R.id.btnFuel);
        btnTransmission = findViewById(R.id.btnTransmission);
        btnColor = findViewById(R.id.btnColor);
        btnModel = findViewById(R.id.btnModel);
        yearInfo = findViewById(R.id.yearInfo);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                permission_state = true;
                //printToast("권한이 설정되었습니다.");
            }
        } else {
            permission_state = false;
            printToast("권한을 설정해야 합니다.");
            //2. 권한이 없는 경우 권한을 설정하라는 메시지를 띄운다.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1000);//requestCode는 아무거나 준다.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1001);
        }

        Intent intent = getIntent();
        carNum = intent.getStringExtra("carNum");
        from = intent.getStringExtra("from");

        if (intent.getStringExtra("brand")!=null) {
            brand = intent.getStringExtra("brand");
            fuel = intent.getStringExtra("fuel");
            transmission = intent.getStringExtra("transmission");
            color = intent.getStringExtra("color");
            model = intent.getStringExtra("model");
            year = intent.getStringExtra("year");
            displacement = intent.getStringExtra("displacement");
            km = intent.getStringExtra("km");
            sago = intent.getStringExtra("sago");
        }
        Bundle bundle = new Bundle();
        bundle.putString("carNum",carNum);
        bundle.putString("carFrom",from);
        bundle.putString("brand",brand);
        bundle.putString("model",model);
        bundle.putString("fuel",fuel);
        bundle.putString("transmission",transmission);
        bundle.putString("color",color);
        bundle.putString("year",year);
        bundle.putString("displacement",displacement);
        bundle.putString("km",km);
        bundle.putString("sago",sago);
        bundle.putBoolean("permission",permission_state);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        fragmentArrayList.add(car_info);
        fragmentArrayList.add(car_price);
        fragmentArrayList.add(car_pictures);
        fragmentArrayList.add(car_finish);

        for (int i=0;i<fragmentArrayList.size();i++){
            fragmentArrayList.get(i).setArguments(bundle);
        }

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(),fragmentArrayList.size());
        viewPager.setAdapter(adapter);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void printToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void next(View view){
        switch (view.getId()){
            case R.id.btnNext1:
                year = car_info.year;
                displacement = car_info.displacement;
                km = car_info.km;
                sago = car_info.sago;
                if (brand.equals("")) {
                    Toast.makeText(car_regist.this, "필수항목 입력해주세요", Toast.LENGTH_SHORT).show();
                    car_info.btnBrand.setBackgroundResource(R.drawable.border_red);
                    car_info.btnBrand.requestFocus();
                }else if (model.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_info.btnModel.setBackgroundResource(R.drawable.border_red);
                    car_info.btnModel.requestFocus();
                }else if (fuel.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_info.btnFuel.setBackgroundResource(R.drawable.border_red);
                    car_info.btnFuel.requestFocus();
                }else if (transmission.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_info.btnTransmission.setBackgroundResource(R.drawable.border_red);
                    car_info.btnTransmission.requestFocus();
                }else if (color.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_info.btnColor.setBackgroundResource(R.drawable.border_red);
                    car_info.btnColor.requestFocus();
                }else if (year.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_info.yearInfo.setBackgroundResource(R.drawable.border_red);
                    car_info.yearInfo.requestFocus();
                }else if (displacement.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_info.displacementInfo.setBackgroundResource(R.drawable.border_red);
                    car_info.displacementInfo.requestFocus();
                }else if (km.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_info.kmInfo.setBackgroundResource(R.drawable.border_red);
                    car_info.kmInfo.requestFocus();
                }else {
                    viewPager.setCurrentItem(1);
                }
                break;
            case R.id.btnNext2:
                price = car_price.price;
                detail = car_price.detail;
                if (price.equals("")) {
                    Toast.makeText(car_regist.this, "필수항목 입력해주세요", Toast.LENGTH_SHORT).show();
                    car_price.salePrice.setBackgroundResource(R.drawable.border_red);
                }else if (detail.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_price.detailInfo.setBackgroundResource(R.drawable.border_red);
                }else {
                    viewPager.setCurrentItem(2);
                }
                break;
            case R.id.btnNext3:
                pic1 = car_pictures.pic1;
                pic2 = car_pictures.pic2;
                pic3 = car_pictures.pic3;
                pic4 = car_pictures.pic4;
                if (pic1.equals("")) {
                    Toast.makeText(car_regist.this, "필수항목 입력해주세요", Toast.LENGTH_SHORT).show();
                    car_pictures.sailImg1.setBackgroundResource(R.drawable.border_red);
                }else if (pic2.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_pictures.sailImg2.setBackgroundResource(R.drawable.border_red);
                }else if (pic3.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_pictures.sailImg3.setBackgroundResource(R.drawable.border_red);
                }else if (pic4.equals("")){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                    car_pictures.sailImg4.setBackgroundResource(R.drawable.border_red);
                }else {
                    viewPager.setCurrentItem(3);
                }
                break;
            case R.id.finish:
                if (!brand.equals("")){
                    if (!model.equals("")){
                        if (!fuel.equals("")){
                            if (!transmission.equals("")){
                                if (!color.equals("")){
                                    if (!car_info.year.equals("")){
                                        if (!car_info.displacement.equals("")){
                                            if (!car_info.km.equals("")){
                                                if (!car_price.price.equals("")){
                                                    if (!car_price.detail.equals("")){
                                                        finalCheck = true;
                                                        if (!car_pictures.pic1.equals("")){
                                                            if (!car_pictures.pic2.equals("")){
                                                                if (!car_pictures.pic3.equals("")){
                                                                    if (!car_pictures.pic4.equals("")){

                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (finalCheck==false){
                    Toast.makeText(car_regist.this,"필수항목 입력해주세요",Toast.LENGTH_SHORT).show();
                }else if (finalCheck==true){
                    Toast.makeText(car_regist.this,"입력 완료!",Toast.LENGTH_SHORT).show();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("method", "saleInsert");
                    map.put("carNumber", carNum);
                    map.put("brand", brand);
                    map.put("model", model);
                    map.put("fuel", fuel);
                    map.put("transmission", transmission);
                    map.put("color", color);
                    map.put("year", car_info.year);
                    map.put("cc", car_info.displacement);
                    map.put("km", car_info.km);
                    map.put("sago", car_info.sago);
                    map.put("price", car_price.price);
                    map.put("saleExplain", car_price.detail);
                    Task saleInsert = new Task();
                    saleInsert.execute(map);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
                break;
        }
    }
}
