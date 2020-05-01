package multi.android.gotcha.sale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import multi.android.gotcha.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class car_finish extends Fragment {
    String carNumber,from;
    TextView carFromNumber;

    public car_finish() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car_finish, container, false);
        // Inflate the layout for this fragment
        carFromNumber = v.findViewById(R.id.carFromNumber5);
        Bundle bundle = this.getArguments();
        if (bundle!=null){
            bundle = getArguments();
            carNumber = bundle.getString("carNum");
            from = bundle.getString("carFrom");
        }

        carFromNumber.setText(from+" / "+carNumber);
        return v;
    }
}
