package multi.android.gotcha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kakao.auth.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import java.util.HashMap;
import java.util.Map;

import multi.android.gotcha.DB.Task;
import multi.android.gotcha.member.login.LoginActivity;
import multi.android.gotcha.member.login.MemberInfo;

public class MainActivity extends AppCompatActivity {
    MemberInfo memberInfo = new MemberInfo();
    Home home = new Home();
    String kakaoNo ="";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();
        kakaoNo = intent.getStringExtra("kakaoNo");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("strName", intent.getStringExtra("name"));
        bundle.putString("strProfile", intent.getStringExtra("profile"));
        bundle.putString("strEmail", intent.getStringExtra("email"));
        bundle.putString("strAgeRange", intent.getStringExtra("ageRange"));
        bundle.putString("strGender", intent.getStringExtra("gender"));
        bundle.putString("strBirthday", intent.getStringExtra("birthday"));
        memberInfo.setArguments(bundle);
/*
        Map<String,String> map = new HashMap<String,String>();
        map.put("content","홀홀");
        Task networkTask = new Task();
        networkTask.execute(map);
        Log.d("dbtest", "1번까지 성공");
*/

        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, home);
        transaction.commit();
    }

    public void onClickLogout(View v) {
        Toast.makeText(this, "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();

        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void onClickSignout(View v) {
        new AlertDialog.Builder(this)
                .setMessage("탈퇴하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
                            @Override
                            public void onFailure(ErrorResult errorResult) {
                                int result = errorResult.getErrorCode();
                                if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                                    Toast.makeText(MainActivity.this, "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "회원탈퇴에 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onSessionClosed(ErrorResult errorResult) {
                                Toast.makeText(MainActivity.this, "로그인 세션이 닫혔습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                MainActivity.this.fileList();
                            }

                            @Override
                            public void onNotSignedUp() {
                                Toast.makeText(MainActivity.this, "가입되지 않은 계정입니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                MainActivity.this.fileList();
                            }

                            @Override
                            public void onSuccess(Long result) {
                                Log.d("testtest","탈퇴"+kakaoNo);
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("method", "signDown");
                                map.put("kakaoNo", kakaoNo);
                                Task networkTask = new Task();
                                networkTask.execute(map);


                                Toast.makeText(MainActivity.this, "회원탈퇴에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);

                                MainActivity.this.fileList();
                            }
                        });
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

}
