package net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.Correo;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Este fragment muestra un correo. Nos permite comunicarnos con él mediante el envío de parámetros mediante:
  * creación mediante newInstance: en fragment dinámicos
 *  mediante setCorreo: En fragment estáticos.
 *  No debemos modificar los views del fragment desde la actividad contenedora.
 */
public class DetalleFragment extends Fragment {
    private static final String ARG_CORREO = "correo";
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_subject)
    TextView tvSubject;
    @BindView(R.id.tv_body)
    TextView tvBody;

    private Correo correo;

    public DetalleFragment() {
        // Required empty public constructor
    }

    public static DetalleFragment newInstance(Correo correo) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CORREO, correo);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        ButterKnife.bind(this, view);

        //indicamos que retenga los valores ante reconstrucciones
        //esto es una ventaja importante respecto a las actividades
        this.setRetainInstance(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            //si estamos creando el fragment de forma dinámica y tenemos
            //argumentos lo mostramo
            correo = getArguments().getParcelable(ARG_CORREO);
        } else {
            //si estamos creandolo de forma estática mostramos datos temporales
            correo = new Correo("sin correo", "", "");
        }
        visualizaCorreo();
    }

    private void visualizaCorreo() {
        tvFrom.setText(correo.getFrom());
        tvSubject.setText(correo.getSubject());
        tvBody.setText(correo.getBody());
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
        visualizaCorreo();
    }


}
