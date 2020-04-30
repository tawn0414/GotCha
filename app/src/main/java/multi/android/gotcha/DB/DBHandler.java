package multi.android.gotcha.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHandler {
    static DBHelper dbHelper;
    static SQLiteDatabase db;

    public static DBHandler open(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return new DBHandler();
    }

    public List<CarMLVO> searchAll(String str) {
        Cursor cursor = db.query("data2", new String[]{"company", "model", "option", "old","fuel","km","price","prediction","diff"}, "model=?", new String[]{str}, null, null, null);
        List<CarMLVO> list = new ArrayList<CarMLVO>();
        while (cursor.moveToNext()) {
            String company = cursor.getString(0);
            String model = cursor.getString(1);
            String option = cursor.getString(2);
            String old = cursor.getString(3);
            String fuel = cursor.getString(4);
            String km = cursor.getString(5);
            String price = cursor.getString(6);
            String prediction = cursor.getString(7);
            String diff = cursor.getString(8);
            CarMLVO car = new CarMLVO(company,model,option,old,fuel,km,price,prediction,diff);
            list.add(car);
        }
        return list;
    }
}