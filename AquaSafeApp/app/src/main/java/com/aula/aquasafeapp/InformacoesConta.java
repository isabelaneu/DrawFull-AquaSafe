package com.aula.aquasafeapp;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import android.Manifest;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformacoesConta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformacoesConta extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton botapFotoPerfil;
    private ImageView fotoPerfil;
    private Uri imageUri;
    private ActivityResultLauncher<Uri> takePictureLauncher;
    private static final int REQUEST_CAMERA_PERMISSION = 100;



    public InformacoesConta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformacoesConta.
     */
    // TODO: Rename and change types and number of parameters
    public static InformacoesConta newInstance(String param1, String param2) {
        InformacoesConta fragment = new InformacoesConta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informacoes_conta, container, false);

        botapFotoPerfil = view.findViewById(R.id.camera_info_conta);
        fotoPerfil = view.findViewById(R.id.foto_perfil);

        // Inicializa o launcher
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                result -> {
                    if (result) {
                        fotoPerfil.setImageURI(imageUri);
                    }
                }
        );
        botapFotoPerfil.setOnClickListener(v -> solicitarPermissaoCamera());
        return view;
    }
    private void abrirCamera() {
        try {
            File file = File.createTempFile("foto_perfil", ".jpg", requireContext().getCacheDir());
            imageUri = FileProvider.getUriForFile(requireContext(), requireContext().getPackageName() + ".fileprovider", file);
            takePictureLauncher.launch(imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solicitarPermissaoCamera() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            abrirCamera();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirCamera();
            } else {
                Toast.makeText(requireContext(), "Permissão da câmera negada", Toast.LENGTH_SHORT).show();
            }
        }
    }

}