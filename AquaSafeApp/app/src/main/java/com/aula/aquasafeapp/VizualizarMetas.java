package com.aula.aquasafeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class VizualizarMetas extends AppCompatActivity {

    Button concluir_meta;
    TextView status_meta1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizar_metas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialToolbar toolbar = findViewById(R.id.materialToolbar3);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        concluir_meta = findViewById(R.id.button);
        status_meta1 = findViewById(R.id.status_meta1);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void concluir_meta(View view) {
        concluir_meta.setVisibility(View.INVISIBLE);
        status_meta1.setText("Concluída");
        status_meta1.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        status_meta1.setVisibility(View.VISIBLE);
    }
}

