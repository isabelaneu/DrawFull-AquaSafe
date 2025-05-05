package com.aula.aquasafeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class GerenciarNotificacoes extends Fragment {

    private static final String PREFS_NAME = "NotificationPrefs";
    private static final String KEY_DAILY_REMINDER = "daily_reminder";
    private static final String KEY_WEEKLY_REPORT = "weekly_report";
    private static final String KEY_TIPS = "economy_tips";
    private static final String KEY_LOGIN_ALERT = "login_alert";

    private Switch switch1, switch2, switch3, switch4;
    private SharedPreferences sharedPreferences;

    public GerenciarNotificacoes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gerenciar_notificacoes, container, false);

        // Inicializa as views
        switch1 = view.findViewById(R.id.switch1);
        switch2 = view.findViewById(R.id.switch2);
        switch3 = view.findViewById(R.id.switch3);
        switch4 = view.findViewById(R.id.switch4);

        // Inicializa SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, 0);

        // Carrega os estados salvos
        loadSwitchStates();

        // Configura os listeners
        setupSwitchListeners();

        return view;
    }

    private void loadSwitchStates() {
        switch1.setChecked(sharedPreferences.getBoolean(KEY_DAILY_REMINDER, false));
        switch2.setChecked(sharedPreferences.getBoolean(KEY_WEEKLY_REPORT, false));
        switch3.setChecked(sharedPreferences.getBoolean(KEY_TIPS, false));
        switch4.setChecked(sharedPreferences.getBoolean(KEY_LOGIN_ALERT, false));
    }

    private void setupSwitchListeners() {
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit().putBoolean(KEY_DAILY_REMINDER, isChecked).apply();
        });

        switch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit().putBoolean(KEY_WEEKLY_REPORT, isChecked).apply();
        });

        switch3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit().putBoolean(KEY_TIPS, isChecked).apply();
        });

        switch4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit().putBoolean(KEY_LOGIN_ALERT, isChecked).apply();
        });
    }
}