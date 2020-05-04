package multi.android.gotcha.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
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

    public String MLprice(String input_model,String input_old, String input_km) {
        Cursor cursor = db.query("fomular", new String[]{"model", "intercept", "old", "km"}, "model=?", new String[]{input_model}, null, null, null);
        String model;
        Double intercept=0d;
        Double old=0d;
        Double km=0d;
        Calendar now = Calendar.getInstance();
        int cy = now.get(Calendar.YEAR);
        int cm = now.get(Calendar.MONTH)+1;
        // input_old : yyyymmdd
        int y = Integer.parseInt(input_old.substring(0,4));
        int m = Integer.parseInt(input_old.substring(4,6));
        while (cursor.moveToNext()) {
            model = cursor.getString(0);
            intercept = Double.parseDouble(cursor.getString(1));
            old = Double.parseDouble(cursor.getString(2));
            km = Double.parseDouble(cursor.getString(3));
        }
        Double result = intercept + ((cy-y)*12+(cm-m))*old + km*Double.parseDouble(input_km);

        return result.intValue()+"";
    }
}