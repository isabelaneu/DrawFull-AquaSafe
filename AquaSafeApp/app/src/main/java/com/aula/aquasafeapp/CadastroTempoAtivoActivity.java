package com.aula.aquasafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroTempoAtivoActivity extends AppCompatActivity {

    private CheckBox checkMenos1ano, checkEntre1e3, checkEntre3e5, checkMais5anos;
    private ImageButton voltar, passar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tempo_ativo);

        checkMenos1ano = findViewById(R.id.checkMenos1ano);
        checkEntre1e3 = findViewById(R.id.checkEntre1e3);
        checkEntre3e5 = findViewById(R.id.checkEntre3e5);
        checkMais5anos = findViewById(R.id.checkMais5anos);

        setupCheckBoxListeners();

        voltar = findViewById(R.id.imageVoltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroTempoAtivoActivity.this, CadastroRamoAtuacaoActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        passar = findViewById(R.id.btnpassar);
        passar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroTempoAtivoActivity.this, CadastroAcompanhamentoConsumoActivity.class);
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
                    // Desmarcar todos os outros checkboxes quando um é selecionado
                    if (buttonView == checkMenos1ano) {
                        uncheckOthers(checkMenos1ano);
                    } else if (buttonView == checkEntre1e3) {
                        uncheckOthers(checkEntre1e3);
                    } else if (buttonView == checkEntre3e5) {
                        uncheckOthers(checkEntre3e5);
                    } else if (buttonView == checkMais5anos) {
                        uncheckOthers(checkMais5anos);
                    }
                }
            }
        };

        checkMenos1ano.setOnCheckedChangeListener(checkListener);
        checkEntre1e3.setOnCheckedChangeListener(checkListener);
        checkEntre3e5.setOnCheckedChangeListener(checkListener);
        checkMais5anos.setOnCheckedChangeListener(checkListener);
    }

    private void uncheckOthers(CheckBox selectedCheckBox) {
        if (selectedCheckBox != checkMenos1ano) checkMenos1ano.setChecked(false);
        if (selectedCheckBox != checkEntre1e3) checkEntre1e3.setChecked(false);
        if (selectedCheckBox != checkEntre3e5) checkEntre3e5.setChecked(false);
        if (selectedCheckBox != checkMais5anos) checkMais5anos.setChecked(false);
    }

    public String getSelectedTempoAtivo() {
        if (checkMenos1ano.isChecked()) return "Menos de 1 ano";
        if (checkEntre1e3.isChecked()) return "Entre 1 a 3 anos";
        if (checkEntre3e5.isChecked()) return "Entre 3 a 5 anos";
        if (checkMais5anos.isChecked()) return "Mais de 5 anos";
        return "";
    }
}