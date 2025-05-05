package com.aula.aquasafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CadastroNomeEmpresaActivity extends AppCompatActivity {
    private EditText editTextNomeEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageButton voltar = findViewById(R.id.imageVoltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroNomeEmpresaActivity.this, TelaInicialActivity.class);
                startActivity(intent);

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        editTextNomeEmpresa = findViewById(R.id.editText);

        Button avançar = findViewById(R.id.avançar);
        avançar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeEmpresa = editTextNomeEmpresa.getText().toString();

                Intent intent = new Intent(CadastroNomeEmpresaActivity.this, CadastroInformacoesBasicasActivity.class);
                intent.putExtra("NOME_EMPRESA", nomeEmpresa);
                startActivity(intent);

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}