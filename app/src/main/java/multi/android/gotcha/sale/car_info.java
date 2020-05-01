package multi.android.gotcha.sale;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import multi.android.gotcha.R;

public class car_info extends Fragment implements View.OnClickListener {
    Button btnBrand, btnModel, btnFuel, btnTransmission, btnColor;
    String carNumber, from, brand = "", model = "", fuel = "", transmission = "", color = "", year = "", displacement = "", km = "", sago = "무사고";
    EditText yearInfo, displacementInfo, kmInfo;
    TextView carFromNumber;
    RadioButton Rsago1, Rsago2, Rsago3;

    public car_info() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car_info, container, false);
        btnBrand = v.findViewById(R.id.btnBrand);
        btnBrand.setOnClickListener(this);
        btnFuel = v.findViewById(R.id.btnFuel);
        btnFuel.setOnClickListener(this);
        btnTransmission = v.findViewById(R.id.btnTransmission);
        btnTransmission.setOnClickListener(this);
        btnColor = v.findViewById(R.id.btnColor);
        btnColor.setOnClickListener(this);
        btnModel = v.findViewById(R.id.btnModel);
        btnModel.setOnClickListener(this);
        yearInfo = v.findViewById(R.id.yearInfo);
        yearInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                year = yearInfo.getText().toString();
                yearInfo.setBackgroundResource(R.drawable.border_rectangle);
            }
        });
        displacementInfo = v.findViewById(R.id.displacementInfo);
        displacementInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                displacement = displacementInfo.getText().toString();
                displacementInfo.setBackgroundResource(R.drawable.border_rectangle);
            }
        });
        kmInfo = v.findViewById(R.id.kmInfo);
        kmInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                km = kmInfo.getText().toString();
                kmInfo.setBackgroundResource(R.drawable.border_rectangle);
            }
        });
        Rsago1 = v.findViewById(R.id.radioSago);
        Rsago1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sago = "사고";
                    Log.d("check", sago);
                }
            }
        });
        Rsago2 = v.findViewById(R.id.radioMusago);
        Rsago2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sago = "무사고";
                    Log.d("check", sago);
                }
            }
        });
        Rsago3 = v.findViewById(R.id.radioGyohwan);
        Rsago3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sago = "무사고(교환)";
                    Log.d("check", sago);
                }
            }
        });

        carFromNumber = v.findViewById(R.id.carFromNumber);

        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            carNumber = bundle.getString("carNum");
            from = bundle.getString("carFrom");
            brand = bundle.getString("brand");
            btnBrand.setText(brand);
            model = bundle.getString("model");
            btnModel.setText(model);
            fuel = bundle.getString("fuel");
            btnFuel.setText(fuel);
            transmission = bundle.getString("transmission");
            btnTransmission.setText(transmission);
            color = bundle.getString("color");
            btnColor.setText(color);
            year = bundle.getString("year");
            yearInfo.setText(year);
            displacement = bundle.getString("displacement");
            displacementInfo.setText(displacement);
            km = bundle.getString("km");
            kmInfo.setText(km);
            sago = bundle.getString("sago");
        }
        if (sago != null) {
            if (sago.equals("사고")) {
                Rsago1.setChecked(true);
            } else if (sago.equals("무사고")) {
                Rsago2.setChecked(true);
            } else if (sago.equals("무사고(교환)")) {
                Rsago3.setChecked(true);
            }
        }
        carFromNumber.setText(from + " / " + carNumber);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBrand:
                Log.d("check", "브랜드 고르기");
                model = "";
                Intent intentBrand = new Intent(getContext(), brand_list.class);
                intentBrand.putExtra("carNum", carNumber);
                intentBrand.putExtra("from", from);
                intentBrand.putExtra("model", model);
                intentBrand.putExtra("fuel", fuel);
                intentBrand.putExtra("transmission", transmission);
                intentBrand.putExtra("color", color);
                intentBrand.putExtra("year", year);
                intentBrand.putExtra("displacement", displacement);
                intentBrand.putExtra("km", km);
                intentBrand.putExtra("sago", sago);
                getActivity().startActivity(intentBrand);
<<<<<<< HEAD
                getActivity().finish();
=======
>>>>>>> upstream/master
                break;
            case R.id.btnModel:
                Log.d("check", "모델 고르기");
                if (brand.equals("현대")) {
                    Intent intentModel = new Intent(getContext(), model_hyundai.class);
                    intentModel.putExtra("carNum", carNumber);
                    intentModel.putExtra("from", from);
                    intentModel.putExtra("brand", brand);
                    intentModel.putExtra("fuel", fuel);
                    intentModel.putExtra("transmission", transmission);
                    intentModel.putExtra("color", color);
                    intentModel.putExtra("year", year);
                    intentModel.putExtra("displacement", displacement);
                    intentModel.putExtra("km", km);
                    intentModel.putExtra("sago", sago);
                    getActivity().startActivity(intentModel);
<<<<<<< HEAD
                    getActivity().finish();
=======
>>>>>>> upstream/master
                } else if (brand.equals("기아")) {
                    Intent intentModel = new Intent(getContext(), model_kia.class);
                    intentModel.putExtra("carNum", carNumber);
                    intentModel.putExtra("from", from);
                    intentModel.putExtra("brand", brand);
                    intentModel.putExtra("fuel", fuel);
                    intentModel.putExtra("transmission", transmission);
                    intentModel.putExtra("color", color);
                    intentModel.putExtra("year", year);
                    intentModel.putExtra("displacement", displacement);
                    intentModel.putExtra("km", km);
                    intentModel.putExtra("sago", sago);
                    getActivity().startActivity(intentModel);
<<<<<<< HEAD
                    getActivity().finish();
=======
>>>>>>> upstream/master
                } else if (brand.equals("벤츠")) {
                    Intent intentModel = new Intent(getContext(), model_bentz.class);
                    intentModel.putExtra("carNum", carNumber);
                    intentModel.putExtra("from", from);
                    intentModel.putExtra("brand", brand);
                    intentModel.putExtra("fuel", fuel);
                    intentModel.putExtra("transmission", transmission);
                    intentModel.putExtra("color", color);
                    intentModel.putExtra("year", year);
                    intentModel.putExtra("displacement", displacement);
                    intentModel.putExtra("km", km);
                    intentModel.putExtra("sago", sago);
                    getActivity().startActivity(intentModel);
<<<<<<< HEAD
                    getActivity().finish();
=======
>>>>>>> upstream/master
                } else if (brand.equals("BMW")) {
                    Intent intentModel = new Intent(getContext(), model_bmw.class);
                    intentModel.putExtra("carNum", carNumber);
                    intentModel.putExtra("from", from);
                    intentModel.putExtra("brand", brand);
                    intentModel.putExtra("fuel", fuel);
                    intentModel.putExtra("transmission", transmission);
                    intentModel.putExtra("color", color);
                    intentModel.putExtra("year", year);
                    intentModel.putExtra("displacement", displacement);
                    intentModel.putExtra("km", km);
                    intentModel.putExtra("sago", sago);
                    getActivity().startActivity(intentModel);
<<<<<<< HEAD
                    getActivity().finish();
=======
>>>>>>> upstream/master
                } else {
                    Toast.makeText(getContext(), "브랜드를 먼저 고르세요", Toast.LENGTH_SHORT).show();
                    btnBrand.setBackgroundResource(R.drawable.border_red);
                }
                break;
            case R.id.btnFuel:
                Log.d("check", "연료 고르기");
                Intent intentFuel = new Intent(getContext(), fuel_list.class);
                intentFuel.putExtra("carNum", carNumber);
                intentFuel.putExtra("from", from);
                intentFuel.putExtra("brand", brand);
                intentFuel.putExtra("model", model);
                intentFuel.putExtra("transmission", transmission);
                intentFuel.putExtra("color", color);
                intentFuel.putExtra("year", year);
                intentFuel.putExtra("displacement", displacement);
                intentFuel.putExtra("km", km);
                intentFuel.putExtra("sago", sago);
                getActivity().startActivity(intentFuel);
<<<<<<< HEAD
                getActivity().finish();
=======
>>>>>>> upstream/master
                break;
            case R.id.btnTransmission:
                Log.d("check", "변속기 고르기");
                Intent intentTransmission = new Intent(getContext(), transmission_list.class);
                intentTransmission.putExtra("carNum", carNumber);
                intentTransmission.putExtra("from", from);
                intentTransmission.putExtra("brand", brand);
                intentTransmission.putExtra("model", model);
                intentTransmission.putExtra("fuel", fuel);
                intentTransmission.putExtra("color", color);
                intentTransmission.putExtra("year", year);
                intentTransmission.putExtra("displacement", displacement);
                intentTransmission.putExtra("km", km);
                intentTransmission.putExtra("sago", sago);
                getActivity().startActivity(intentTransmission);
<<<<<<< HEAD
                getActivity().finish();
=======
>>>>>>> upstream/master
                break;
            case R.id.btnColor:
                Log.d("check", "색상 고르기");
                Intent intentColor = new Intent(getContext(), color_list.class);
                intentColor.putExtra("carNum", carNumber);
                intentColor.putExtra("from", from);
                intentColor.putExtra("brand", brand);
                intentColor.putExtra("model", model);
                intentColor.putExtra("fuel", fuel);
                intentColor.putExtra("transmission", transmission);
                intentColor.putExtra("year", year);
                intentColor.putExtra("displacement", displacement);
                intentColor.putExtra("km", km);
                intentColor.putExtra("sago", sago);
                getActivity().startActivity(intentColor);
<<<<<<< HEAD
                getActivity().finish();
=======
>>>>>>> upstream/master
                break;
        }
    }


}
