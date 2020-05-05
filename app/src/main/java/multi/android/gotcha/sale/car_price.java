package multi.android.gotcha.sale;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import multi.android.gotcha.R;

public class car_price extends Fragment {
    String carNumber,from,price="",detail="",predict;
    EditText salePrice,detailInfo;
    TextView carFromNumber,pricePredict;
    public car_price() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car_price, container, false);
        carFromNumber = v.findViewById(R.id.carFromNumber2);
        //입력받는 판매가
        salePrice = v.findViewById(R.id.salePrice);
        //보여줄 적정 판매가
        pricePredict = v.findViewById(R.id.pricePredict);
        detailInfo = v.findViewById(R.id.detailInfo);

        Bundle bundle = this.getArguments();
        if (bundle!=null){
            bundle = getArguments();
            carNumber = bundle.getString("carNum");
            from = bundle.getString("carFrom");
        }

        salePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                price = salePrice.getText().toString();
                Log.d("check",salePrice.getText().toString());
                salePrice.setBackgroundResource(R.drawable.border_rectangle);
            }
        });
        detailInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                detail = detailInfo.getText().toString();
                Log.d("check",detailInfo.getText().toString());
                detailInfo.setBackgroundResource(R.drawable.border_rectangle);
            }
        });
        pricePredict.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                predict = pricePredict.getText().toString();
                Log.d("check",pricePredict.getText().toString());
            }
        });

        carFromNumber.setText(from+" / "+carNumber);

        return v;
    }
}
