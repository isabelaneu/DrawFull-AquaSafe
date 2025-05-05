package com.aula.aquasafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class CadastroInformacoesBasicasActivity extends AppCompatActivity {
    private TextInputEditText editTextCNPJ, editTextEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageButton voltar = findViewById(R.id.imageVoltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroInformacoesBasicasActivity.this, CadastroNomeEmpresaActivity.class);
                startActivity(intent);

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        String nomeEmpresa = getIntent().getStringExtra("NOME_EMPRESA");

        editTextCNPJ = findViewById(R.id.textInputEditText); // Campo CNPJ
        editTextEndereco = findViewById(R.id.textInputEditText5); // Campo Endereço

        Button avançar = findViewById(R.id.avançar);

        avançar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cnpj = editTextCNPJ.getText().toString();
                String endereco = editTextEndereco.getText().toString();

                Intent intent = new Intent(CadastroInformacoesBasicasActivity.this, CadastroRamoAtuacaoActivity.class);
                intent.putExtra("NOME_EMPRESA", nomeEmpresa);
                intent.putExtra("CNPJ", cnpj);
                intent.putExtra("ENDERECO", endereco);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}