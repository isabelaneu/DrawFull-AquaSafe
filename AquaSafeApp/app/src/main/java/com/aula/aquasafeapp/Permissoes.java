package com.aula.aquasafeapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Permissoes extends Fragment {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 101;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 102;

    private Switch switchLocalizacao, switchCamera, switchGaleria;

    public Permissoes() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permissoes, container, false);

        switchLocalizacao = view.findViewById(R.id.switch5);
        switchCamera = view.findViewById(R.id.switch6);
        switchGaleria = view.findViewById(R.id.switch7);

        updateSwitchStates();
        setupSwitchListeners();

        return view;
    }

    private void updateSwitchStates() {
        boolean hasLocationPermission = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        switchLocalizacao.setChecked(hasLocationPermission);
        switchCamera.setChecked(ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);

        String storagePermission = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ?
                Manifest.permission.READ_MEDIA_IMAGES :
                Manifest.permission.READ_EXTERNAL_STORAGE;

        switchGaleria.setChecked(ContextCompat.checkSelfPermission(requireContext(),
                storagePermission) == PackageManager.PERMISSION_GRANTED);
    }

    private void setupSwitchListeners() {
        switchLocalizacao.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                requestLocationPermission();
            } else {
                showPermissionRevokeInstructions("localização");
                switchLocalizacao.setChecked(true);
            }
        });

        switchCamera.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                requestPermission(new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION_REQUEST_CODE);
            } else {
                showPermissionRevokeInstructions("câmera");
                switchCamera.setChecked(true);
            }
        });

        switchGaleria.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                String storagePermission = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ?
                        Manifest.permission.READ_MEDIA_IMAGES :
                        Manifest.permission.READ_EXTERNAL_STORAGE;
                requestPermission(new String[]{storagePermission},
                        STORAGE_PERMISSION_REQUEST_CODE);
            } else {
                showPermissionRevokeInstructions("galeria");
                switchGaleria.setChecked(true);
            }
        });
    }

    private void requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Android 12+ - request both fine and coarse location
            requestPermission(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            requestPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void requestPermission(String[] permissions, int requestCode) {
        boolean shouldShowRationale = false;
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission)) {
                shouldShowRationale = true;
                break;
            }
        }

        if (shouldShowRationale) {
            Toast.makeText(getContext(),
                    "Esta permissão é necessária para o funcionamento do recurso",
                    Toast.LENGTH_LONG).show();
        }

        ActivityCompat.requestPermissions(requireActivity(), permissions, requestCode);
    }

    private void showPermissionRevokeInstructions(String permissionName) {
        Toast.makeText(getContext(),
                "Para revogar a permissão de " + permissionName + ", vá em Configurações > Aplicativos > " +
                        getString(R.string.app_name) + " > Permissões",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }

            if (allGranted) {
                Toast.makeText(getContext(), "Permissão concedida", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Permissão negada", Toast.LENGTH_SHORT).show();
            }
        }
        updateSwitchStates();
    }
}