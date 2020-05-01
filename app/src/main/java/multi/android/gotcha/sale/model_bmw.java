package multi.android.gotcha.sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import multi.android.gotcha.R;

public class model_bmw extends AppCompatActivity {
    String[] bmw= {"3시리즈","5시리즈"};
    ListView listView;
    String carNum,from,fuel,transmission,color,brand,year,displacement,km,sago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_bmx);
        listView = findViewById(R.id.modelBMW);
        Intent receive = getIntent();
        carNum = receive.getStringExtra("carNum");
        from = receive.getStringExtra("from");
        brand = receive.getStringExtra("brand");
        fuel = receive.getStringExtra("fuel");
        transmission = receive.getStringExtra("transmission");
        color = receive.getStringExtra("color");
        year = receive.getStringExtra("year");
        displacement = receive.getStringExtra("displacement");
        km = receive.getStringExtra("km");
        sago = receive.getStringExtra("sago");
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                bmw);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(model_bmw.this,car_regist.class);
                intent.putExtra("model",parent.getItemAtPosition(position).toString());
                intent.putExtra("carNum",carNum);
                intent.putExtra("from",from);
                intent.putExtra("brand",brand);
                intent.putExtra("fuel",fuel);
                intent.putExtra("transmission",transmission);
                intent.putExtra("color",color);
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
