package multi.android.gotcha.member.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.kakao.auth.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import multi.android.gotcha.R;

public class MemberInfo extends Fragment {
    String name, profile, email,ageRange,gender,birthday;
    TextView tvNickname;
    ImageView ivProfile;
    TextView tvEmail;
    TextView tvAgeRange;
    TextView tvGender;
    TextView tvBirthday;
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
            tvNickname = getActivity().findViewById(R.id.tvNickname);
            ivProfile = getActivity().findViewById(R.id.ivProfile);
            btnLogout = getActivity().findViewById(R.id.btnLogout);
            btnSignout = getActivity().findViewById(R.id.btnSignout);
            //순서대로 각각 이메일, 나잇대, 성별, 생일을 보여주는 TextView 선언
            tvEmail = getActivity().findViewById(R.id.tvEmail);
            tvAgeRange = getActivity().findViewById(R.id.tvAgeRange);
            tvGender = getActivity().findViewById(R.id.tvGender);
            tvBirthday = getActivity().findViewById(R.id.tvBirthday);

            tvNickname.setText(name);
            Glide.with(this).load(profile).into(ivProfile);
            tvEmail.setText(email);
            tvAgeRange.setText(ageRange);
            tvGender.setText(gender);
            tvBirthday.setText(birthday);


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
                                        /*
                                        여기에 회원 탈퇴에 필요한 DB작업 추가
                                        */
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member_info, container, false);
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
