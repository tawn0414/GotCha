package multi.android.gotcha.sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import multi.android.gotcha.R;

public class transmission_list extends AppCompatActivity {
    String[] transmission_name= {"오토","수동","세미오토","CVT"};
    ListView listView;
    String carNum,from,brand,fuel,color,model,year,displacement,km,sago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmission_list);
        listView = findViewById(R.id.transmission_list);

        Intent receive = getIntent();
        carNum = receive.getStringExtra("carNum");
        from = receive.getStringExtra("from");
        brand = receive.getStringExtra("brand");
        fuel = receive.getStringExtra("fuel");
        color = receive.getStringExtra("color");
        model = receive.getStringExtra("model");
        year = receive.getStringExtra("year");
        displacement = receive.getStringExtra("displacement");
        km = receive.getStringExtra("km");
        sago = receive.getStringExtra("sago");
        ArrayAdapter adapter = new ArrayAdapter(this,
                                                android.R.layout.simple_list_item_1,
                                                transmission_name);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(transmission_list.this,car_regist.class);
                intent.putExtra("carNum",carNum);
                intent.putExtra("from",from);
                intent.putExtra("transmission",parent.getItemAtPosition(position).toString());
                intent.putExtra("brand",brand);
                intent.putExtra("fuel",fuel);
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
