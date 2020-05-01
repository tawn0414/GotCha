package multi.android.gotcha.member.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kakao.auth.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import java.util.HashMap;
import java.util.Map;

import multi.android.gotcha.DB.MemberVO;
import multi.android.gotcha.DB.Task;
import multi.android.gotcha.R;

public class MemberInfo extends Fragment {
    String name, profile, email, ageRange, gender, birthday, kakaoNo, nickName, phoneNo;
    TextView tvNickname;
    TextView tvName;
    ImageView ivProfile;
    TextView tvEmail;
    TextView tvGender;
    TextView tvPhoneNo;
    Button btnLogout;
    Button btnSignout;

    public MemberInfo() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("strName");
            profile = getArguments().getString("strProfile");
            email = getArguments().getString("strEmail");
            ageRange = getArguments().getString("strAgeRange");
            gender = getArguments().getString("strGender");
            birthday = getArguments().getString("strBirthday");
            kakaoNo = getArguments().getString("strKakaoNo");
            Map<String, String> map = new HashMap<String, String>();
            map.put("method", "searchIdInKakao");
            map.put("kakaoNo", kakaoNo);
            Task networkTask = new Task();
            networkTask.execute(map);
            while (networkTask.getResult().equals("")) {
                SystemClock.sleep(10);
            }
            Gson gson = new Gson();
            MemberVO data = gson.fromJson(networkTask.getResult(), MemberVO.class);
            email = data.getMem_email();
            name = data.getMem_name();
            gender = data.getMem_gender();
            nickName = data.getMem_nickname();
            birthday = data.getMem_birth();
            phoneNo = data.getMem_phoneno();


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_info, container, false);
        tvNickname = view.findViewById(R.id.tvNickname);
        ivProfile = view.findViewById(R.id.ivProfile);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnSignout = view.findViewById(R.id.btnSignout);
        //순서대로 각각 이메일, 나잇대, 성별, 생일을 보여주는 TextView 선언
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
        tvGender = view.findViewById(R.id.tvGender);
        tvPhoneNo = view.findViewById(R.id.tvPhoneNo);

        btnSignout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("탈퇴하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
                                    @Override
                                    public void onFailure(ErrorResult errorResult) {
                                        int result = errorResult.getErrorCode();
                                        if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                                            Toast.makeText(getContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getContext(), "회원탈퇴에 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onSessionClosed(ErrorResult errorResult) {
                                        Toast.makeText(getContext(), "로그인 세션이 닫혔습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getContext(), LoginActivity.class);
                                        startActivity(intent);
                                        getActivity().fileList();
                                    }

                                    @Override
                                    public void onNotSignedUp() {
                                        Toast.makeText(getContext(), "가입되지 않은 계정입니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getContext(), LoginActivity.class);
                                        startActivity(intent);
                                        getActivity().fileList();
                                    }

                                    @Override
                                    public void onSuccess(Long result) {

                                        Map<String, String> map = new HashMap<String, String>();
                                        map.put("method", "signDown");
                                        map.put("kakaoNo", kakaoNo);
                                        Task networkTask = new Task();
                                        networkTask.execute(map);

                                        Toast.makeText(getContext(), "회원탈퇴에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getContext(), LoginActivity.class);
                                        startActivity(intent);

                                        getActivity().fileList();
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
        });
        tvNickname.setText(nickName);
        Glide.with(this).load(profile).into(ivProfile);
        tvEmail.setText(email);
        tvName.setText(name);
        tvGender.setText(gender);
        tvPhoneNo.setText(phoneNo);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
