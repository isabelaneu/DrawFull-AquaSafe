package com.aula.aquasafeapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Configuracoes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Configuracoes extends Fragment {

    Dialog dialog;

    public Configuracoes() {
        // Required empty public constructor
    }

    public static Configuracoes newInstance(String param1, String param2) {
        Configuracoes fragment = new Configuracoes();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Fetch parameters if necessary
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);

        ImageButton gerenciarNot = view.findViewById(R.id.notificacoes);
        gerenciarNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navega para o fragmento de Gerenciar Notificações
                Navigation.findNavController(view).navigate(R.id.action_configuracoes_to_gerenciarNotificacoes);
            }
        });

        ImageButton permissoes = view.findViewById(R.id.permissoes_botao);
        permissoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navega para o fragmento de Permissões
                Navigation.findNavController(view).navigate(R.id.action_configuracoes_to_permissoes2);
            }
        });

        Button deletarConta = view.findViewById(R.id.deletar_conta);
        deletarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deletar_conta);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCancelable(false);

                Button btSim = dialog.findViewById(R.id.button_sim);
                Button btNao = dialog.findViewById(R.id.button_nao);

                btSim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent rota = new Intent(getContext(), TelaInicialActivity.class);
                        startActivity(rota);
                    }
                });

                btNao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }
}
