package net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.Correo;

import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.R;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.fragments.DetalleFragment;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.fragments.ListadoFragment;

/**
 * Permite ver como se trabaja con fragments dinámicos para mostrar el detalle
 * en los Layouts teneniendo 3 posibles
 * diseños de pantalla: pantalla pequeña, pantalla grande horizontal y pantalla grande Vertical
 */
public class MaestroDetalleDinamicoActivity extends AppCompatActivity {
    private boolean esPantallaGrande;
    ListadoFragment listadoFragment;
    DetalleFragment detalleFragmentDinamico;
    FrameLayout frameContenedorDinamico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maestro_detalle_dinamico);
        //comprobamos si estamos en una pantalla grande mirando si existe el frameLayout que contendrá el fragment
        frameContenedorDinamico = (FrameLayout) findViewById(R.id.frm_contenedorFrgDinamico);
        if (frameContenedorDinamico == null) {//pantalla pequeña
            esPantallaGrande = false;
        } else {
            esPantallaGrande = true;

        }
        //buscamos el fragment que contiene la lista
        listadoFragment = (ListadoFragment) getSupportFragmentManager().findFragmentById(R.id.frg_listado2);

        //Asignamos el evento de seleccion de correo
        listadoFragment.setOnListaCorreosListener
                (new ListadoFragment.OnListaCorreosListener() {
                     @Override
                     public void onCorreoSeleccionado(Correo correo) {
                         if (esPantallaGrande) {
                             //creamos el fragmento de forma dinámica
                             crearFragment(correo);

                         } else {
                             //si es pantalla pequeña, mostramos el correo en
                             //la actividad correspondiente
                             Intent i = new Intent(MaestroDetalleDinamicoActivity.this, CorreoActivity.class);
                             i.putExtra(CorreoActivity.EXTRA_CORREO, correo);
                             startActivity(i);
                         }
                     }
                 }
                );
    }

    /**
     * Crea un nuevo fragment detalle correo permitiendo la navegabilidad.
     *
     * @param correo
     */
    private void crearFragment(Correo correo) {
        //creamos un nuevo fragment enviandole el correo
        detalleFragmentDinamico = DetalleFragment.newInstance(correo);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_contenedorFrgDinamico, detalleFragmentDinamico);
        //permitimos la navegabilidad
        transaction.addToBackStack(null);
        //activamos el fragment nuevo
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //probamos la accion de añadir y borrar un nuevo elemento. Observar que tiene que ser el fragment
            //quien maneje la lista
            case R.id.action_add:
                listadoFragment.addCorreo(new Correo("nuevo@micorreo.com", "asunto nuevo", "contenido nuevo"));
                return true;
            case R.id.action_borrar:
                listadoFragment.delCorreo(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
