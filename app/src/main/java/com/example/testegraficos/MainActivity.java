package com.example.testegraficos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BarChart barChart;
    PieChart pieChart;

    //usados par afazer a primeira adicao
    private String [] datas = {"11/07/2002","11/07/2062","11/07/2042","11/07/2022","11/07/2030","11/07/1999","11/07/2007","11/07/2012","11/07/2002","20/07/2002"};
    private String [] categorias = {"categoria1","categoria1","categoria2","categoria5","categoria7","categoria1","categoria4","categoria5","categoria4","categoria4"};
    private double [] valores = {10,20,5,6,7,8,9,0,3,11};
    private int [] turnos = {1,2,1,2,1,1,1,2,3,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DataBaseHelser baseHelser = new DataBaseHelser(this);

        //atribuindo valores a tabela
        //for (int j = 0;j < 10;j++) {
        //    baseHelser.insert(datas[j],categorias[j],valores[j],turnos[j]);
        //}

        ArrayList<Dado> dados =  baseHelser.getArray2();


        barChart = findViewById(R.id.grafico);
        pieChart = findViewById(R.id.grafico2);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //entidade e valor
        for (int i = 0; i <= 10;i++) {

            //colocar aq dado. get desc, getval
            BarEntry barEntry = new BarEntry(i,Integer.parseInt(dados.get(i).getValor()));

            PieEntry pieEntry = new PieEntry(i,Integer.parseInt(dados.get(i).getValor()));

            barEntries.add(barEntry);
            pieEntries.add(pieEntry);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"Graf 1");

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setDrawValues(false);

        barChart.setData(new BarData(barDataSet));

        barChart.animateY(6000);


        barChart.getDescription().setText("Descricao");
        barChart.getDescription().setTextColor(Color.BLUE);

        //barChart.invalidate();

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Grafico 2");

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setDrawValues(false);

        pieChart.setData(new PieData(pieDataSet));

        pieChart.animateY(5000);

        pieChart.getDescription().setText("Descricao dois");
        pieChart.getDescription().setTextColor(Color.BLUE);
    }
}