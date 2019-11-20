package net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.Correo;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.ItemCorreoAdapter;
import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *Este fragment mantendrá la lista de correos. Es necesario que sea completamente autónomo y
 * se comunique con la activity mediante métodos e interface
 */
public class ListadoFragment extends Fragment {


    @BindView(R.id.lv_listaCorreos)
    ListView lvListaCorreos;
    ItemCorreoAdapter adaptador;
    ArrayList<Correo> listaCorreos;
    //contiene una referencia al listener del evento de seleccion de correo
    private OnListaCorreosListener listaCorreosListener;

    public ListadoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
        Para que el Fragment retenga los datos en una reconstrucción
        tenemos dos posibilidades:
        1-trabajar como ya sabéis con onSaveInstaceState
        2-Indicar al fragment setRetainInstance(true) y que los datos que
            se quieran guardar sean miembros de la clase, como en este caso listaCorreos
         */
        setRetainInstance(true);
        if(listaCorreos==null){//si no venimos de una reconstrucción
            crearCorreosPrueba();
        }
        adaptador=new ItemCorreoAdapter(getContext(),listaCorreos);
        lvListaCorreos.setAdapter(adaptador);
        //en caso de click sobre un correo, delegamos a la actividad que tiene que hacer
        lvListaCorreos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                listaCorreosListener.onCorreoSeleccionado(adaptador.getItem(i));
            }
        });

    }

    /**
     * Creamos unos datos de prueba para mostrar
     */
    private void crearCorreosPrueba() {

        listaCorreos = new ArrayList<Correo>();
        for (int i = 0; i <= 15; i++)
            listaCorreos.add(new Correo(i+"pepe@micorreo.com" , "Asunto el que sea-" + i, i + "- Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ac gravida nibh, at congue ipsum. Sed aliquam semper turpis sed ultricies. Vestibulum quis lacus lectus. Phasellus luctus metus at tincidunt suscipit. Proin vestibulum orci nec nisi commodo aliquam. Nulla porta accumsan ipsum, sed tincidunt arcu efficitur nec. Mauris eget fringilla leo. Maecenas lacinia scelerisque quam in vehicula. Praesent a mauris a nunc dignissim accumsan. "));

    }
    /**
     *Mediante esta interface comunicaremos el evento de selección de un correo
     */
    public interface OnListaCorreosListener{
        void onCorreoSeleccionado(Correo correo);
    }

    /**
     * Este método nos permite asignar el listener para el evento de seleccion de correo
     * @param listener
     */
    public void setOnListaCorreosListener(OnListaCorreosListener listener){
        listaCorreosListener=listener;
    }

    /**
     * Nos permite añadir un correo
     * @param correo
     */
    public void addCorreo(Correo correo){
        adaptador.addCorreo(correo);
    }

    /**
     * Nos permite eliminar un correo
     * @param pos
     */
    public void delCorreo(int pos){
        adaptador.delCorreo(pos);
    }

}
