package com.example.testegraficos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BarChart barChart;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barChart = findViewById(R.id.grafico);
        pieChart = findViewById(R.id.grafico2);


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < 10;i++) {
            float value = (float) (i * 10.0);

            BarEntry barEntry = new BarEntry(i,value);

            PieEntry pieEntry = new PieEntry(i,value);

            barEntries.add(barEntry);
            pieEntries.add(pieEntry);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"Graf 1");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(false);

        barChart.setData(new BarData(barDataSet));

        barChart.animateY(5000);


    }
}