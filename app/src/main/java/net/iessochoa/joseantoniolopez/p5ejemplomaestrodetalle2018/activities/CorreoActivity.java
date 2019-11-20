package net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.activities;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.Correo;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.R;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.fragments.DetalleFragment;

/**
 * Esta Actividad nos permite ver el detalle en pantallas pequeñas
 */
public class CorreoActivity extends AppCompatActivity {
    public static final String EXTRA_CORREO = "net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.activities.correoactivity.correo";
    private Correo correo;
    private DetalleFragment frgDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);
        correo = (Correo) getIntent().getParcelableExtra(EXTRA_CORREO);
        frgDetalle = (DetalleFragment) getSupportFragmentManager().findFragmentById(R.id.frg_detalle);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //mostramos el dia en pantalla
        frgDetalle.setCorreo(correo);
    }
}
