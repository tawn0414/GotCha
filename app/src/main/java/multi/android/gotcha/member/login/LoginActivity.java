package multi.android.gotcha.member.login;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

import java.util.HashMap;
import java.util.Map;

import multi.android.gotcha.DB.Task;
import multi.android.gotcha.MainActivity;
import multi.android.gotcha.R;
import multi.android.gotcha.member.PrivacyPolicy;
import multi.android.gotcha.member.Terms;


public class LoginActivity extends AppCompatActivity {
    private SessionCallback sessionCallback;
    public PrivacyPolicy privacyPolicy = new PrivacyPolicy();
    public Terms terms = new Terms();
    public LoginFragment loginFragment = new LoginFragment();
    public SignUpFragment1 signUpFragment1 = new SignUpFragment1();
    public SignUpFragment2 signUpFragment2 = new SignUpFragment2();
    String code = "";
    Task networkTask;
    String check = "";
    String kakaoNo ="";
    String phoneNo = "";
    String gender = "";
    String email = "";
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
        //다음번에 앱을 켰을 때 자동로그인
        Session.getCurrentSession().checkAndImplicitOpen();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.LFragment, loginFragment);
        transaction.commit();
    }

    //카카오 로그인 액티비티에서 결과값을 가져오려면 필요하다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
    }

    //콜백을 제거해줘야 한다. 다른 로그인 API를 같이 쓸 때 문제가 생길 수 있다.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();

                    if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다: " + errorResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(), "세션이 닫혔습니다. 다시 시도해 주세요: " + errorResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    String needsScopeAutority = ""; // 정보 제공이 허용되지 않은 항목의 이름을 저장하는 변수


                    // 이메일, 성별, 연령대, 생일 정보를 제공하는 것에 동의했는지 체크
                    if (result.getKakaoAccount().needsScopeAccountEmail()) {
                        needsScopeAutority = needsScopeAutority + "이메일";
                    }
                    if (result.getKakaoAccount().needsScopeGender()) {
                        needsScopeAutority = needsScopeAutority + ", 성별";
                    }
                    if (result.getKakaoAccount().needsScopeAgeRange()) {
                        needsScopeAutority = needsScopeAutority + ", 연령대";
                    }
                    if (result.getKakaoAccount().needsScopeBirthday()) {
                        needsScopeAutority = needsScopeAutority + ", 생일";
                    }

                    if (needsScopeAutority.length() != 0) { // 정보 제공이 허용되지 않은 항목이 있다면 -> 허용되지 않은 항목을 안내하고 회원탈퇴 처리
                        if (needsScopeAutority.charAt(0) == ',') {
                            needsScopeAutority = needsScopeAutority.substring(2);
                        }
                        Toast.makeText(getApplicationContext(), needsScopeAutority + "에 대한 권한이 허용되지 않았습니다. 개인정보 제공에 동의해주세요.", Toast.LENGTH_SHORT).show(); // 개인정보 제공에 동의해달라는 Toast 메세지 띄움

                        // 회원탈퇴 처리
                        UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
                            @Override
                            public void onFailure(ErrorResult errorResult) {
                                int result = errorResult.getErrorCode();

                                if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                                    Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "오류가 발생했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onSessionClosed(ErrorResult errorResult) {
                                Toast.makeText(getApplicationContext(), "로그인 세션이 닫혔습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNotSignedUp() {
                                Toast.makeText(getApplicationContext(), "가입되지 않은 계정입니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSuccess(Long result) {
                            }
                        });
                    } else { // 모든 항목에 동의했다면 -> 유저 정보를 가져와서 MainActivity에 전달하고 MainActivity 실행.
                        kakaoNo=result.getId()+"";
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("method", "searchIdInKakao");
                        map.put("kakaoNo", kakaoNo);
                        networkTask = new Task();
                        networkTask.execute(map);
                        while(networkTask.getResult().equals("")){
                            SystemClock.sleep(10);
                        }
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("name", result.getNickname());
                        name = result.getNickname();
                        intent.putExtra("profile", result.getProfileImagePath());
                        if (result.getKakaoAccount().hasEmail() == OptionalBoolean.TRUE) {
                            intent.putExtra("email", result.getKakaoAccount().getEmail());
                            email = result.getKakaoAccount().getEmail();
                        }
                        else
                            intent.putExtra("email", "none");
                        if (result.getKakaoAccount().hasAgeRange() == OptionalBoolean.TRUE)
                            intent.putExtra("ageRange", result.getKakaoAccount().getAgeRange().getValue());
                        else
                            intent.putExtra("ageRange", "none");
                        if (result.getKakaoAccount().hasGender() == OptionalBoolean.TRUE) {
                            intent.putExtra("gender", result.getKakaoAccount().getGender().getValue());
                            gender = result.getKakaoAccount().getGender().getValue();
                        }
                        else
                            intent.putExtra("gender", "none");
                        if (result.getKakaoAccount().hasBirthday() == OptionalBoolean.TRUE)
                            intent.putExtra("birthday", result.getKakaoAccount().getBirthday());
                        else
                            intent.putExtra("birthday", "none");
                        if (check.equals("")) {
                            check = networkTask.getResult();
                        }
                        Log.d("testtest",check);
                        //1324166902
                        Log.d("testtest", result.getId() + "");
                        if (check.equals("true")) {
                            intent.putExtra("kakaoNo",result.getId() + "");
                            startActivity(intent);
                            finish();
                        } else if(check.equals("false")) {
                            SignUp();
                        }
                    }
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void SignUp() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.LFragment, signUpFragment1);
        transaction.addToBackStack("signUpFragment");
        transaction.commit();

    }

    public void privacyPolicy(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.LFragment, privacyPolicy);
        transaction.addToBackStack("privacyPolicy");
        transaction.commit();
    }

    public void term(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.LFragment, terms);
        transaction.addToBackStack("terms");
        transaction.commit();
    }

    public void clickSendSMS(View v) {
        TelephonyManager telManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        code = "";
        for (int i = 0; i < 6; i++) {
            code += (int) (Math.random() * 10);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("sms_test", "권한주세요");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1000);
        } else {
            EditText et = signUpFragment1.getActivity().findViewById(R.id.inputPhoneNumber);
            phoneNo = et.getText().toString();
        }
        try {
            //전송
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, code, null, null);
            Toast.makeText(getApplicationContext(), "전송 완료!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        Button btn = signUpFragment1.getActivity().findViewById(R.id.btnAuthKeyNumber);
        btn.setEnabled(true);
    }

    public void clickCheckAuth(View v) {
        EditText et = signUpFragment1.getActivity().findViewById(R.id.inputAuthKeyNumber);
        String key = et.getText().toString();
        if (key.equals(code)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction;
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.LFragment, signUpFragment2);
            Bundle bundle = new Bundle();
            bundle.putString("strName", name);
            bundle.putString("strEmail", email);
            bundle.putString("strGender", gender);
            signUpFragment2.setArguments(bundle);
            transaction.addToBackStack("signUpFragment2");
            transaction.commit();
        } else {
            Log.d("sms_test", "인증실패!");
        }
    }
    public void clickDatePicker(View v) {
        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                , listener, 1990, 0, 1);
        dialog.getDatePicker().setSpinnersShown(true);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), year + "/" + monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            Button btn = signUpFragment1.getActivity().findViewById(R.id.datePickerOpen);
            btn.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
        }
    };

    public void checkBoxListener(View v) {
        CheckBox chk1 = signUpFragment2.getActivity().findViewById(R.id.check01);
        CheckBox chk2 = signUpFragment2.getActivity().findViewById(R.id.check02);
        CheckBox chk3 = signUpFragment2.getActivity().findViewById(R.id.check03);
        CheckBox chk4 = signUpFragment2.getActivity().findViewById(R.id.check04);
        Button btn = signUpFragment2.getActivity().findViewById(R.id.submit);
        if (chk4.isChecked()) {
            chk1.setChecked(true);
            chk2.setChecked(true);
            chk3.setChecked(true);
        }
        if (chk1.isChecked() && chk2.isChecked() && chk3.isChecked()) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }

    public void SignUpComplete(View v) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "signUpComplete");
        RadioButton RBmale = signUpFragment2.getActivity().findViewById(R.id.radioMale);
        RadioButton RBfemale = signUpFragment2.getActivity().findViewById(R.id.radioFemale);
        EditText name = signUpFragment2.getActivity().findViewById(R.id.inputName);
        EditText nickname = signUpFragment2.getActivity().findViewById(R.id.inputNickName);
        Button birth = signUpFragment2.getActivity().findViewById(R.id.datePickerOpen);
        EditText eMail = signUpFragment2.getActivity().findViewById(R.id.inputEmail);

        if(gender.equals("male")&&RBfemale.isChecked()||gender.equals("female")&&RBmale.isChecked())
            Toast.makeText(this,"정보를 정확히 입력해 주세요.", Toast.LENGTH_SHORT).show();
        else {
            map.put("kakaoNo", kakaoNo);
            map.put("name", name.getText().toString());
            map.put("nickname", nickname.getText().toString());
            map.put("birth", birth.getText().toString());
            map.put("gender", gender);
            map.put("eMail", eMail.getText().toString());
            map.put("phoneNo", phoneNo);
            networkTask = new Task();
            networkTask.execute(map);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            signUpFragment2.getActivity().startActivity(intent);
            finish();
        }
    }
}
