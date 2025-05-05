package com.aula.aquasafeapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuPerfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Dialog dialog;

    public MenuPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuPerfil newInstance(String param1, String param2) {
        MenuPerfil fragment = new MenuPerfil();
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
        View view = inflater.inflate(R.layout.fragment_menu_perfil, container, false);

        ImageButton infoConta = view.findViewById(R.id.info_conta);
        infoConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(MenuPerfil.this);
                Navigation.findNavController(view).navigate(R.id.action_menuPerfil_to_informacoesConta);
            }
        });

        ImageButton histConsumo = view.findViewById(R.id.hist_consumo);
        histConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(MenuPerfil.this);
                Navigation.findNavController(view).navigate(R.id.action_menuPerfil_to_historicoConsumo);
            }
        });

        ImageButton config = view.findViewById(R.id.config);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(MenuPerfil.this);
                Navigation.findNavController(view).navigate(R.id.action_menuPerfil_to_configuracoes);
            }
        });

        ImageButton sairConta = view.findViewById(R.id.sair_conta);
        sairConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.sair_conta);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCancelable(false);
                Button btSim = dialog.findViewById(R.id.sair_sim);
                Button btNao = dialog.findViewById(R.id.sair_nao);

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