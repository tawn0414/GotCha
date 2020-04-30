package multi.android.gotcha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.kakao.auth.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import multi.android.gotcha.DB.CarMLVO;
import multi.android.gotcha.DB.DBHandler;
import multi.android.gotcha.DB.Task;
import multi.android.gotcha.member.login.LoginActivity;
import multi.android.gotcha.member.login.MemberInfo;

public class MainActivity extends AppCompatActivity {
    MemberInfo memberInfo = new MemberInfo();
    Home home = new Home();
    String kakaoNo = "";
    String name = "";
    String profile = "";
    String email = "";
    String ageRange = "";
    String gender = "";
    String birthday = "";
    Intent intent;
    DrawerLayout mainLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler handler;
        handler = DBHandler.open(this);
        /*
        List<CarMLVO> list = handler.searchAll("K3");
        Log.d("listtest",list.size()+"");
        */
        intent = getIntent();
        kakaoNo = intent.getStringExtra("kakaoNo");
        name = intent.getStringExtra("name");
        profile = intent.getStringExtra("profile");
        email = intent.getStringExtra("email");
        ageRange = intent.getStringExtra("ageRange");
        gender = intent.getStringExtra("gender");
        birthday = intent.getStringExtra("birthday");

        mainLayout = findViewById(R.id.main_container);
        toggle = new ActionBarDrawerToggle(this, mainLayout, R.string.open_str, R.string.close_str);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        final NavigationView navigationView = findViewById(R.id.main_drawer_view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, home);
        transaction.commit();


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.item1) {
                            FragmentTransaction transaction;
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment, home);
                            getSupportFragmentManager().popBackStackImmediate(null,getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                            transaction.commit();
                            mainLayout.closeDrawer(navigationView);
                        } else if (id == R.id.item2) {
                            getSupportFragmentManager().popBackStackImmediate(null,getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                            FragmentTransaction transaction;
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment, memberInfo);
                            Bundle bundle = new Bundle();
                            bundle.putString("strName", name);
                            bundle.putString("strProfile", profile);
                            bundle.putString("strEmail", email);
                            bundle.putString("strAgeRange", ageRange);
                            bundle.putString("strGender", gender);
                            bundle.putString("strBirthday", birthday);
                            bundle.putString("strKakaoNo",kakaoNo);
                            memberInfo.setArguments(bundle);
                            transaction.addToBackStack("memberInfo");
                            transaction.commit();
                            mainLayout.closeDrawer(navigationView);
                        } else if (id == R.id.item3) {

                        } else if (id == R.id.item4) {

                        } else if (id == R.id.item5) {

                        } else {

                        }

                        return false;
                    }
                });
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
                                Log.d("testtest", "탈퇴" + kakaoNo);
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

    public void onClickBuy(View v) {
        Toast.makeText(this, "내차사기", Toast.LENGTH_SHORT).show();
    }

    public void onClickSell(View v) {
        Toast.makeText(this, "내차팔기", Toast.LENGTH_SHORT).show();
    }

    public void onClickCommunity(View v) {
        Toast.makeText(this, "커뮤니티", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ImageView iv = findViewById(R.id.navigation_header_image);
        TextView tv = findViewById(R.id.navigation_header_id);
        tv.setText(name);
        if (profile != null)
            Glide.with(this).load(profile).into(iv);

        if (toggle.onOptionsItemSelected(item)) {

        }
        return super.onOptionsItemSelected(item);
    }

}
