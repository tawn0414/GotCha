package multi.android.gotcha.member.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import multi.android.gotcha.R;


public class SignUpFragment2 extends Fragment {
    String name;
    String gender;
    String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("strName");
            gender = getArguments().getString("strGender");
            email = getArguments().getString("strEmail");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up2, container, false);
        EditText ETname = view.findViewById(R.id.inputName);
        EditText ETemail = view.findViewById(R.id.inputEmail);
        RadioButton radioMale = view.findViewById(R.id.radioMale);
        RadioButton radioFemale = view.findViewById(R.id.radioFemale);

        ETname.setText(name);
        ETemail.setText(email);
        if (gender.equals("male")) {
            radioMale.setChecked(true);
            radioFemale.setChecked(false);
        } else {
            radioMale.setChecked(false);
            radioFemale.setChecked(true);
        }
        return view;
    }
}
