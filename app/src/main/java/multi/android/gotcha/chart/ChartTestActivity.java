package multi.android.gotcha.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import multi.android.gotcha.R;

public class ChartTestActivity extends AppCompatActivity {
    private ScatterChart scatterChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_test);

        scatterChart = (ScatterChart)findViewById(R.id.scatterChart);
        ScatterDataSet scatterDataSet = new ScatterDataSet(getData(), "Inducesmile");
        scatterDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        ScatterData scatterData = new ScatterData(scatterDataSet);
        XAxis xAxis = scatterChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        scatterChart.setData(scatterData);
        scatterChart.animateXY(5000, 5000);
        scatterChart.invalidate();
    }
    private ArrayList getData(){
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 30));
        entries.add(new Entry(1, 80));
        entries.add(new Entry(2, 60));
        entries.add(new Entry(3, 50));
        entries.add(new Entry(4, 70));
        entries.add(new Entry(5, 60));
        return entries;
    }
}
