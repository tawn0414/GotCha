package multi.android.gotcha.sale;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import multi.android.gotcha.DB.colorItem;
import multi.android.gotcha.R;

public class color_list extends AppCompatActivity {
    ListView listView;
    String carNum,from,fuel,transmission,brand,model,year,displacement,km,sago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);
        listView = findViewById(R.id.color_list);
        List<colorItem> colorItems = new ArrayList<>();
        colorItems.add(new colorItem(Color.parseColor("#000000"),"검정색"));
        colorItems.add(new colorItem(Color.parseColor("#c0c0c0"),"은색"));
        colorItems.add(new colorItem(Color.parseColor("#ffffff"),"흰색"));
        colorItems.add(new colorItem(Color.parseColor("#FFFACD"),"진주색"));
        colorItems.add(new colorItem(Color.parseColor("#DCDCDC"),"명은색"));
        colorItems.add(new colorItem(Color.parseColor("#843900"),"갈색"));
        colorItems.add(new colorItem(Color.parseColor("#C37E00"),"금색"));
        colorItems.add(new colorItem(Color.parseColor("#00008B"),"청색"));
        colorItems.add(new colorItem(Color.parseColor("#7FFFD4"),"하늘색"));
        colorItems.add(new colorItem(Color.parseColor("#32CD32"),"녹색"));
        colorItems.add(new colorItem(Color.parseColor("#FF0000"),"빨간색"));
        colorItems.add(new colorItem(Color.parseColor("#ff7f00"),"주황색"));
        colorItems.add(new colorItem(Color.parseColor("#FFFF00"),"노란색"));
        colorItems.add(new colorItem(Color.parseColor("#800080"),"보라색"));
        colorItems.add(new colorItem(Color.parseColor("#FF69B4"),"분홍색"));
        colorAdapter colorAdapter = new colorAdapter(this,colorItems,listView);
        Intent receive = getIntent();
        carNum = receive.getStringExtra("carNum");
        from = receive.getStringExtra("from");
        fuel = receive.getStringExtra("fuel");
        transmission = receive.getStringExtra("transmission");
        brand = receive.getStringExtra("brand");
        model = receive.getStringExtra("model");
        year = receive.getStringExtra("year");
        displacement = receive.getStringExtra("displacement");
        km = receive.getStringExtra("km");
        sago = receive.getStringExtra("sago");
        listView.setAdapter(colorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String color = parent.getItemAtPosition(position).toString().split(",")[1];
                Intent intent = new Intent(color_list.this,car_regist.class);
                intent.putExtra("color",color);
                intent.putExtra("carNum",carNum);
                intent.putExtra("from",from);
                intent.putExtra("fuel",fuel);
                intent.putExtra("transmission",transmission);
                intent.putExtra("brand",brand);
                intent.putExtra("model",model);
                intent.putExtra("year",year);
                intent.putExtra("displacement",displacement);
                intent.putExtra("km",km);
                intent.putExtra("sago",sago);
                startActivity(intent);
                finish();
            }
        });
    }


}
