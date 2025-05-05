package com.aula.aquasafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroAcompanhamentoConsumoActivity extends AppCompatActivity {

    private CheckBox checkFeliz, checkNormal, checkMal;
    private ImageButton voltar, passar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_acompanhamento_consumo);

        checkFeliz = findViewById(R.id.checkFeliz);
        checkNormal = findViewById(R.id.checkNormal);
        checkMal = findViewById(R.id.checkMal);

        setupCheckBoxListeners();

        voltar = findViewById(R.id.imageVoltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAcompanhamentoConsumoActivity.this, CadastroTempoAtivoActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        passar = findViewById(R.id.btnpassar);
        passar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAcompanhamentoConsumoActivity.this, CadastroEstimativaCustoActivity.class);
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
                    if (buttonView == checkFeliz) {
                        uncheckOthers(checkFeliz);
                    } else if (buttonView == checkNormal) {
                        uncheckOthers(checkNormal);
                    } else if (buttonView == checkMal) {
                        uncheckOthers(checkMal);
                    }
                }
            }
        };

        checkFeliz.setOnCheckedChangeListener(checkListener);
        checkNormal.setOnCheckedChangeListener(checkListener);
        checkMal.setOnCheckedChangeListener(checkListener);
    }

    private void uncheckOthers(CheckBox selectedCheckBox) {
        if (selectedCheckBox != checkFeliz) checkFeliz.setChecked(false);
        if (selectedCheckBox != checkNormal) checkNormal.setChecked(false);
        if (selectedCheckBox != checkMal) checkMal.setChecked(false);
    }

    public String getSelectedAcompanhamento() {
        if (checkFeliz.isChecked()) return "Sim, de forma detalhada";
        if (checkNormal.isChecked()) return "Sim, mas sem controle rigoroso";
        if (checkMal.isChecked()) return "Não acompanho";
        return "";
    }
}