package com.aula.aquasafeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.appbar.MaterialToolbar;

public class FundoRelatorio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fundo_relatorio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        NavController navController = navHost.getNavController();

        MaterialToolbar toolbar = findViewById(R.id.materialToolbar4);
        setSupportActionBar(toolbar);

// Ativa o botão de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

// Listener de navegação da toolbar
        toolbar.setNavigationOnClickListener(v -> {
            if (navController.getCurrentDestination() != null &&
                    navController.getCurrentDestination().getId() == R.id.menuPerfil) {
                // Estamos no primeiro fragmento -> volta para a MainActivity
                finish();
            } else {
                // Estamos em outro fragmento -> volta no stack do NavController
                navController.navigateUp();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.fragmentContainerView2).navigateUp() || super.onSupportNavigateUp();
    }
}