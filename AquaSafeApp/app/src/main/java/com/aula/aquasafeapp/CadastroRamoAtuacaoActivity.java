package com.aula.aquasafeapp;

import android.content.Intent;
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
        setContentView(R.layout.activity_cadastro_ramo_atuacao); // Certifique-se de que esta linha está presente

        checkAlimentacao = findViewById(R.id.checkAlimentacao);
        checkBeleza = findViewById(R.id.checkBeleza);
        checkLavanderia = findViewById(R.id.checkLavanderia);
        checkJardinagem = findViewById(R.id.checkJardinagem);
        checkOutro = findViewById(R.id.checkOutro);

        setupCheckBoxListeners();

        voltar = findViewById(R.id.imageVoltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroRamoAtuacaoActivity.this, CadastroInformacoesBasicasActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        passar = findViewById(R.id.btnpassar);
        passar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroRamoAtuacaoActivity.this, CadastroTempoAtivoActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private void setupCheckBoxListeners() {
        CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (buttonView == checkAlimentacao) {
                        uncheckOthers(checkAlimentacao);
                    } else if (buttonView == checkBeleza) {
                        uncheckOthers(checkBeleza);
                    } else if (buttonView == checkLavanderia) {
                        uncheckOthers(checkLavanderia);
                    } else if (buttonView == checkJardinagem) {
                        uncheckOthers(checkJardinagem);
                    } else if (buttonView == checkOutro) {
                        uncheckOthers(checkOutro);
                    }
                }
            }
        };

        checkAlimentacao.setOnCheckedChangeListener(checkListener);
        checkBeleza.setOnCheckedChangeListener(checkListener);
        checkLavanderia.setOnCheckedChangeListener(checkListener);
        checkJardinagem.setOnCheckedChangeListener(checkListener);
        checkOutro.setOnCheckedChangeListener(checkListener);
    }

    private void uncheckOthers(CheckBox selectedCheckBox) {
        if (selectedCheckBox != checkAlimentacao) checkAlimentacao.setChecked(false);
        if (selectedCheckBox != checkBeleza) checkBeleza.setChecked(false);
        if (selectedCheckBox != checkLavanderia) checkLavanderia.setChecked(false);
        if (selectedCheckBox != checkJardinagem) checkJardinagem.setChecked(false);
        if (selectedCheckBox != checkOutro) checkOutro.setChecked(false);
    }

    public String getSelectedRamoAtuacao() {
        if (checkAlimentacao.isChecked()) return "Alimentação";
        if (checkBeleza.isChecked()) return "Beleza";
        if (checkLavanderia.isChecked()) return "Lavanderia";
        if (checkJardinagem.isChecked()) return "Jardinagem";
        if (checkOutro.isChecked()) return "Outro";
        return "";
    }
}