package multi.android.gotcha.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, "product.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("dbtest", "데이터베이스가 생성 되었습니다.");
        String sql = "create table car_train("
                + "company text not null, "
                + "option text,"
                + "model text not null, "
                + "old text not null, "
                + "fuel text not null,"
                + "km text not null,"
                + "price text not null,"
                + "prediction text not null,"
                + "diff text not null)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbtest", "데이터베이스가 업데이트 되었습니다.\noldVersion: " + oldVersion + "\nnewVersion: " + newVersion);
    }
}