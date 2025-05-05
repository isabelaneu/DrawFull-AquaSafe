package com.aula.aquasafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroRamoAtuacaoActivity extends AppCompatActivity {

    private CheckBox checkAlimentacao, checkBeleza, checkLavanderia, checkJardinagem, checkOutro;
    private ImageButton voltar, passar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ramo_atuacao);


        // Pega os dados das telas anteriores
        String nomeEmpresa = getIntent().getStringExtra("NOME_EMPRESA");
        String cnpj = getIntent().getStringExtra("CNPJ");
        String endereco = getIntent().getStringExtra("ENDERECO");

        checkAlimentacao = findViewById(R.id.checkAlimentacao);
        checkBeleza = findViewById(R.id.checkBeleza);
        checkLavanderia = findViewById(R.id.checkLavanderia);
        checkJardinagem = findViewById(R.id.checkJardinagem);
        checkOutro = findViewById(R.id.checkOutro);


        voltar = findViewById(R.id.imageVoltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroRamoAtuacaoActivity.this, CadastroInformacoesBasicasActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        ImageButton passar = findViewById(R.id.btnpassar);
        passar.setOnClickListener(v -> {
            String ramoAtuacao = getSelectedRamoAtuacao();

            SharedPreferences prefs = getSharedPreferences("DadosEmpresa", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("NOME_EMPRESA", nomeEmpresa);
            editor.putString("CNPJ", cnpj);
            editor.putString("ENDERECO", endereco);
            editor.putString("RAMO_ATUACAO", ramoAtuacao);
            editor.apply();

            Intent intent = new Intent(CadastroRamoAtuacaoActivity.this, InformacoesConta.class);
            startActivity(intent);
            finish();
        });
    }

    // Método para pegar o ramo selecionado
    private String getSelectedRamoAtuacao() {
        if (checkAlimentacao.isChecked()) return "Alimentação";
        if (checkBeleza.isChecked()) return "Beleza";
        if (checkLavanderia.isChecked()) return "Lavanderia";
        if (checkJardinagem.isChecked()) return "Jardinagem";
        if (checkOutro.isChecked()) return "Outro";
        return "";
}
}