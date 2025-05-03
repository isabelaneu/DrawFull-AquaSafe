package com.aula.aquasafeapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;

public class InformacoesConta extends Fragment {

    private static final String PREFS_NAME = "InformacoesContaPrefs";
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private Uri imageUri;
    private ActivityResultLauncher<Uri> takePictureLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informacoes_conta, container, false);

        setupEdicaoCampos(view);
        setupCamera(view);

        return view;
    }

    private void setupEdicaoCampos(View view) {
        setupCampoEdicao(view, R.id.tv_nome, R.id.editar_nome, "nome_empresa", "Editar Nome da Empresa");
        setupCampoEdicao(view, R.id.tv_ramo, R.id.editar_ramo, "ramo_atuacao", "Editar Ramo de Atuação");
        setupCampoEdicao(view, R.id.tv_cnpj, R.id.editar_cnpj, "cnpj", "Editar CNPJ");
        setupCampoEdicao(view, R.id.tv_endereco, R.id.editar_endereco, "endereco", "Editar Endereço");
    }

    private void setupCampoEdicao(View view, int tvId, int btnId, String prefKey, String dialogTitle) {
        TextView textView = view.findViewById(tvId);
        ImageButton button = view.findViewById(btnId);

        // Carrega valor salvo ou mantém o padrão do layout
        String valorSalvo = carregarDado(prefKey);
        if (!valorSalvo.isEmpty()) {
            textView.setText(valorSalvo);
        }

        button.setOnClickListener(v -> abrirDialogoEdicao(textView, dialogTitle, prefKey));
    }

    private void abrirDialogoEdicao(final TextView textView, String titulo, final String chavePref) {
        TextInputLayout textInputLayout = new TextInputLayout(requireContext());
        textInputLayout.setPadding(
                getResources().getDimensionPixelOffset(R.dimen.dialog_padding),
                0,
                getResources().getDimensionPixelOffset(R.dimen.dialog_padding),
                0
        );

        EditText input = new EditText(requireContext());
        input.setText(textView.getText());
        textInputLayout.addView(input);
        textInputLayout.setHint(titulo.replace("Editar ", ""));

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext())
                .setTitle(titulo)
                .setView(textInputLayout)
                .setNegativeButton("Cancelar", null);

        // Validação especial para CNPJ
        if (textView.getId() == R.id.tv_cnpj) { // ID do campo CNPJ
            input.addTextChangedListener(new MaskUtil("##.###.###/####-##", input));
            builder.setPositiveButton("Salvar", (dialog, which) -> {
                String cnpj = input.getText().toString().replaceAll("[^0-9]", "");
                if (validarCNPJ(cnpj)) {
                    String cnpjFormatado = formatarCNPJ(cnpj);
                    textView.setText(cnpjFormatado);
                    salvarDado(chavePref, cnpjFormatado);
                } else {
                    Toast.makeText(requireContext(), "CNPJ inválido!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            builder.setPositiveButton("Salvar", (dialog, which) -> {
                String novoTexto = input.getText().toString();
                textView.setText(novoTexto);
                salvarDado(chavePref, novoTexto);
            });
        }

        builder.show();
    }

    private void setupCamera(View view) {
        ImageView fotoPerfil = view.findViewById(R.id.foto_perfil);
        String uriSalva = carregarDado("foto_perfil_uri");
        if (!uriSalva.isEmpty()) {
            fotoPerfil.setImageURI(Uri.parse(uriSalva));
        }
        ImageButton botaoFotoPerfil = view.findViewById(R.id.camera_info_conta);


        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                result -> {
                    if (result && imageUri != null) {
                        fotoPerfil.setImageURI(imageUri);
                        salvarDado("foto_perfil_uri", imageUri.toString());
                    }
                }
        );

        botaoFotoPerfil.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                abrirCamera();
            } else {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        REQUEST_CAMERA_PERMISSION);
            }
        });
    }

    private void abrirCamera() {
        try {
            File diretorio = new File(requireContext().getFilesDir(), "fotos");
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }
            File file = new File(diretorio, "foto_perfil.jpg");
            file.createNewFile();            file.deleteOnExit();
            imageUri = FileProvider.getUriForFile(requireContext(),
                    requireContext().getPackageName() + ".fileprovider", file);

            if (takePictureLauncher != null) {
                takePictureLauncher.launch(imageUri);
            }
        } catch (IOException e) {
            Toast.makeText(requireContext(), "Erro ao criar arquivo para foto", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(requireContext(), "Erro no FileProvider", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirCamera();
            } else {
                Toast.makeText(requireContext(),
                        "Permissão da câmera é necessária",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void salvarDado(String chave, String valor) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(chave, valor).apply();
    }

    private String carregarDado(String chave) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(chave, "");
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj != null && cnpj.length() == 14;
    }

    private String formatarCNPJ(String cnpj) {
        return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    // Classe interna para máscara
    private static class MaskUtil implements TextWatcher {
        private final String mask;
        private final EditText editText;
        private boolean isUpdating;
        private String oldValue = "";

        public MaskUtil(String mask, EditText editText) {
            this.mask = mask;
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String value = s.toString();
            if (isUpdating) {
                oldValue = value;
                isUpdating = false;
                return;
            }

            String maskedValue = applyMask(value);
            isUpdating = true;
            editText.setText(maskedValue);
            editText.setSelection(maskedValue.length());
        }

        @Override
        public void afterTextChanged(Editable s) {}

        private String applyMask(String value) {
            StringBuilder maskedValue = new StringBuilder();
            int maskIndex = 0;
            int valueIndex = 0;

            while (maskIndex < mask.length() && valueIndex < value.length()) {
                char maskChar = mask.charAt(maskIndex);
                char valueChar = value.charAt(valueIndex);

                if (maskChar == '#') {
                    maskedValue.append(valueChar);
                    valueIndex++;
                } else {
                    maskedValue.append(maskChar);
                }
                maskIndex++;
            }

            return maskedValue.toString();
        }
    }
}