package multi.android.gotcha.sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import multi.android.gotcha.R;

public class brand_list extends AppCompatActivity {
    String[] brand_name= {"현대","기아","쉐보레","르노삼성","쌍용","BMW","도요타","벤츠","아우디"};
    ListView listView;
    String carNum,from,fuel,transmission,color,model,year,displacement,km,sago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list);
        listView = findViewById(R.id.brand_list);
        Intent receive = getIntent();
        carNum = receive.getStringExtra("carNum");
        from = receive.getStringExtra("from");
        fuel = receive.getStringExtra("fuel");
        transmission = receive.getStringExtra("transmission");
        color = receive.getStringExtra("color");
        model = receive.getStringExtra("model");
        year = receive.getStringExtra("year");
        displacement = receive.getStringExtra("displacement");
        km = receive.getStringExtra("km");
        sago = receive.getStringExtra("sago");
        ArrayAdapter adapter = new ArrayAdapter(this,
                                                android.R.layout.simple_list_item_1,
                                                brand_name);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(brand_list.this,car_regist.class);
                intent.putExtra("brand",parent.getItemAtPosition(position).toString());
                intent.putExtra("carNum",carNum);
                intent.putExtra("from",from);
                intent.putExtra("fuel",fuel);
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
