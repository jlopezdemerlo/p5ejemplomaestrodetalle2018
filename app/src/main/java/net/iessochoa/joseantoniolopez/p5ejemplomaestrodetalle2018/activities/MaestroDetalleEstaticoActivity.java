package net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.Correo;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.R;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.fragments.DetalleFragment;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.fragments.ListadoFragment;

/**
 * Permite ver como se trabaja con fragments estáticos en los Layouts teneniendo 3 posibles
 * diseños de pantalla: pantalla pequeña, pantalla grande horizontal y pantalla grande Vertical
 */
public class MaestroDetalleEstaticoActivity extends AppCompatActivity {
    //este flag nos permitirá saber en  si estamos en pantalla grande o pequeña
    private boolean esPantallaGrande;

    ListadoFragment listadoFragment;
    DetalleFragment detalleFragmentEstatico;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maestro_destalle_estatico);
        //comprobamos si estamos en una pantalla grande mirando si existe el fragment detalle
        detalleFragmentEstatico = (DetalleFragment) getSupportFragmentManager().findFragmentById(R.id.frg_detalle);
        if (detalleFragmentEstatico == null) {//pantalla pequeña
            esPantallaGrande = false;
        } else {
            esPantallaGrande = true;

        }
        //buscamos la lista
        listadoFragment = (ListadoFragment) getSupportFragmentManager().findFragmentById(R.id.frg_listado);

        //Asignamos el evento de seleccion de correo
        listadoFragment.setOnListaCorreosListener
                (new ListadoFragment.OnListaCorreosListener() {
                     @Override
                     public void onCorreoSeleccionado(Correo correo) {
                         if (esPantallaGrande) {
                                 //en este caso llamamos a un método del propio fragment que modifique
                                 //las propiedades
                                 detalleFragmentEstatico.setCorreo(correo);

                         } else {
                             //si es pantalla pequeña, mostramos el correo en
                             //la actividad correspondiente
                             Intent i = new Intent(MaestroDetalleEstaticoActivity.this, CorreoActivity.class);
                             i.putExtra(CorreoActivity.EXTRA_CORREO, correo);
                             startActivity(i);
                         }

                     }
                 }
                );
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
