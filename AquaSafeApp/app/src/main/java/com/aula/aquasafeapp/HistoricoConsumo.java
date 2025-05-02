package com.aula.aquasafeapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoricoConsumo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoricoConsumo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoricoConsumo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoricoConsumo.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoricoConsumo newInstance(String param1, String param2) {
        HistoricoConsumo fragment = new HistoricoConsumo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historico_consumo, container, false);

        // === GRÁFICO JANEIRO ===
        LineChart chart = view.findViewById(R.id.lineChart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 3));
        entries.add(new Entry(1, 2.5f));
        entries.add(new Entry(2, 4));
        entries.add(new Entry(3, 3.8f));
        entries.add(new Entry(4, 4.2f));

        LineDataSet dataSet = new LineDataSet(entries, "Consumo em Litros");
        dataSet.setColor(Color.rgb(87, 124, 141));
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setCircleColor(Color.rgb(87, 124, 141));
        dataSet.setLineWidth(2f);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.getDescription().setText("Consumo nos últimos dias");
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisRight().setEnabled(false);
        chart.animateY(1000);
        chart.invalidate();

        // === GRÁFICO DEZEMBRO ===
        LineChart chart2 = view.findViewById(R.id.lineChart2);

        ArrayList<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0, 2));
        entries2.add(new Entry(1, 2.2f));
        entries2.add(new Entry(2, 3.5f));
        entries2.add(new Entry(3, 3.0f));
        entries2.add(new Entry(4, 3.7f));

        LineDataSet dataSet2 = new LineDataSet(entries2, "Consumo em Litros");
        dataSet2.setColor(Color.rgb(87, 124, 141));
        dataSet2.setValueTextColor(Color.BLACK);
        dataSet2.setCircleColor(Color.rgb(87, 124, 141));
        dataSet2.setLineWidth(2f);

        LineData lineData2 = new LineData(dataSet2);
        chart2.setData(lineData2);
        chart2.getDescription().setText("Consumo nos últimos dias");
        chart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart2.getAxisRight().setEnabled(false);
        chart2.animateY(1000);
        chart2.invalidate();

        return view;
    }

}