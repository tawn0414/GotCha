package multi.android.gotcha.sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import multi.android.gotcha.R;

public class fuel_list extends AppCompatActivity {
    String[] fuel= {"가솔린","디젤","LPG","CNG","가솔린+전기","LPG+전기","가솔린+LPG","가솔린+CNG","전기"};
    ListView listView;
    String carNum,from,brand,transmission,color,model,year,displacement,km,sago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_list);
        listView = findViewById(R.id.fuel_list);
        Intent receive = getIntent();
        carNum = receive.getStringExtra("carNum");
        from = receive.getStringExtra("from");
        brand = receive.getStringExtra("brand");
        transmission = receive.getStringExtra("transmission");
        color = receive.getStringExtra("color");
        model = receive.getStringExtra("model");
        year = receive.getStringExtra("year");
        displacement = receive.getStringExtra("displacement");
        km = receive.getStringExtra("km");
        sago = receive.getStringExtra("sago");
        ArrayAdapter adapter = new ArrayAdapter(this,
                                                android.R.layout.simple_list_item_1,
                                                fuel);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(fuel_list.this,car_regist.class);
                intent.putExtra("fuel",parent.getItemAtPosition(position).toString());
                intent.putExtra("carNum",carNum);
                intent.putExtra("from",from);
                intent.putExtra("brand",brand);
                intent.putExtra("transmission",transmission);
                intent.putExtra("color",color);
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
