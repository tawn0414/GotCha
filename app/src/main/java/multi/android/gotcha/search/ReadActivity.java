package multi.android.gotcha.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import multi.android.gotcha.R;

public class ReadActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_mycar_detail);
        ImageView image1 = findViewById(R.id.img1);
        ImageView image2 = findViewById(R.id.img2);
        ImageView image3 = findViewById(R.id.img3);
        ImageView image4 = findViewById(R.id.img4);

        TextView company = findViewById(R.id.comp1);
        TextView modelName = findViewById(R.id.modelName1);
        TextView dpm = findViewById(R.id.dpm1);
        TextView fuel = findViewById(R.id.fuel1);
        TextView mileage = findViewById(R.id.mileage1);
        TextView email = findViewById(R.id.email);
        TextView price = findViewById(R.id.price1);

        TextView carNum = findViewById(R.id.carNum);
        TextView old = findViewById(R.id.old);
        TextView fuel1 = findViewById(R.id.fuel2);
        TextView transmission = findViewById(R.id.transmission);
        TextView dpm1 = findViewById(R.id.dpm2);
        TextView color = findViewById(R.id.color);

        TextView regdate = findViewById(R.id.regdate);
        TextView seller_pName = findViewById(R.id.personName);
        TextView seller_email = findViewById(R.id.email1);
        TextView seller_phone = findViewById(R.id.phone);
        TextView accident = findViewById(R.id.accident);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();

        String company1 = intent.getStringExtra("company");
        String modelName1 = intent.getStringExtra("modelName");
        String dpm2 = intent.getStringExtra("dpm");
        String fuel2 = intent.getStringExtra("fuel");
        String mileage1 = intent.getStringExtra("mileage");
        String email1 = intent.getStringExtra("email");
        String price1 = intent.getStringExtra("price");

        String carNum1 = intent.getStringExtra("carNum");
        String old1 = intent.getStringExtra("old");
        String fuel3 = intent.getStringExtra("fuel1");
        String transmission1 = intent.getStringExtra("transmission");
        String dpm3 = intent.getStringExtra("dpm1");
        String color1 = intent.getStringExtra("color");

        String regdate1 = intent.getStringExtra("regdate");
        String seller_pName1 = intent.getStringExtra("seller_pName");
        String seller_email1 = intent.getStringExtra("seller_email");
        String seller_phone1 = intent.getStringExtra("seller_phone");
        String accident1 = intent.getStringExtra("accident");

        String pic1 = "http://"+getString(R.string.ip)+":8088/DBServer/images/"+intent.getStringExtra("pic1");
        String pic2 = "http://"+getString(R.string.ip)+":8088/DBServer/images/"+intent.getStringExtra("pic2");
        String pic3 = "http://"+getString(R.string.ip)+":8088/DBServer/images/"+intent.getStringExtra("pic3");
        String pic4 = "http://"+getString(R.string.ip)+":8088/DBServer/images/"+intent.getStringExtra("pic4");
        Log.d("testtest",pic1);
        Glide.with(this).load(pic1).into(image1);
        Glide.with(this).load(pic2).into(image2);
        Glide.with(this).load(pic3).into(image3);
        Glide.with(this).load(pic4).into(image4);

        //image.setImageResource(R.drawable.car2);
        company.setText(company1);
        modelName.setText(modelName1);
        dpm.setText(dpm2);
        fuel.setText(fuel2);
        mileage.setText("주행거리 "+mileage1+"km");
        email.setText(email1);
        price.setText(price1+"만원");

        carNum.setText(carNum1);
        old.setText(old1);
        fuel1.setText(fuel3);
        transmission.setText(transmission1);
        dpm1.setText(dpm3);
        color.setText(color1);

        regdate.setText(regdate1);
        seller_pName.setText(seller_pName1);
        seller_email.setText(seller_email1);
        seller_phone.setText(seller_phone1);
        accident.setText(accident1);
    }
}
