package com.aula.aquasafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    ImageButton perfil;
    ImageButton historico;
    ImageButton dicas;
    ImageButton vizualizar_metas;
    Button fazer_relatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            View scrollView = ((ViewGroup) rootView).getChildAt(0);
            if (scrollView != null) {
                scrollView.setPadding(
                        scrollView.getPaddingLeft(),
                        systemBars.top,
                        scrollView.getPaddingRight(),
                        systemBars.bottom
                );
            }

            return insets;
        });

        perfil = findViewById(R.id.perfil);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FundoSplash.class);
                startActivity(intent);
            }
        });

        historico = findViewById(R.id.home_hist);
        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
                startActivity(intent);
            }
        });

        dicas = findViewById(R.id.home_dicas);
        dicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DicasConsumo.class);
                startActivity(intent);
            }
        });

        vizualizar_metas = findViewById(R.id.home_vizu_metas);
        vizualizar_metas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VizualizarMetas.class);
                startActivity(intent);
            }
        });

        fazer_relatorio = findViewById(R.id.fazer_relatorio);
        fazer_relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FundoRelatorio.class);
                startActivity(intent);
            }
        });
    }


}