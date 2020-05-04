package multi.android.gotcha.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "product.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("dbtest", "데이터베이스가 생성 되었습니다.");
        String sql = "CREATE TABLE fomular("
                + "model text not null, "
                + "intercept text not null,"
                + "old text not null, "
                + "km text not null)";
        db.execSQL(sql);


        String[] sqlarr = new String[]{"INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('EQ900', '7250.32906', '-32.49026', '-0.01639')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('G80', '5190', '-27.72', '-0.008168')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('K3', '1818', '-12.51', '-0.002188')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('K5', '2339', '-11.15', '-0.004233')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('K7', '3100', '-22.3', '-0.0002007')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('레이', '1293.69605', '-5.02521', '-0.00288')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('모닝', '1014', '-4.722', '-0.001768')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('모하비', '4018', '-16.15', '-0.005796')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('봉고3', '2317', '-9.281', '-0.004635')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('스포티지', '2237', '-10.75', '-0.002663')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('쏘렌토', '2955', '-13.15', '-0.003695')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('SM3', '1034', '-3.976', '-0.001928')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('SM5', '1135', '-4.465', '-0.001104')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('SM7', '1843', '-7.955', '-0.002398')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('크루즈', '1240', '-6.296', '-0.001688')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('말리부', '2235', '-15.27', '-0.002573')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('스파크', '916.96607', '-5.193396', '-0.001016')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('코란도', '1622', '-5.256', '-0.001364')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('렉스턴', '3172', '-16.65', '-0.003405')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('티볼리', '1843', '-9.206', '-0.003363')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('아반떼', '1581', '-7.079', '-0.002695')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('제네시스', '3899.94082', '-22.76823', '-0.00266')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('그랜져', '2788', '-15.88', '-0.001029')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('포터2', '2335', '-13.42', '-0.001227')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('싼타페', '3070', '-14.1', '-0.003937')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('쏘나타', '2184', '-9.801', '-0.003953')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('B3', '3140', '-6.0', '-0.009187')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('B5', '4610', '-21.24', '-0.002293')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('B7', '9439.81145', '-14.79167', '-0.03157')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('CLS', '8239.49906', '-27.81581', '-0.02134')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('C', '4771.68148', '-7.82624', '-0.01692')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('E', '5803.07859', '-23.0057', '-0.01036')",
                "INSERT INTO 'main'.'fomular' ('model', 'intercept', 'old', 'km') VALUES ('S', '13090', '-49.16', '-0.02349')",};
        for(int i=0; i<sqlarr.length; i++){
            db.execSQL(sqlarr[i]);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbtest", "데이터베이스가 업데이트 되었습니다.\noldVersion: " + oldVersion + "\nnewVersion: " + newVersion);
    }
}