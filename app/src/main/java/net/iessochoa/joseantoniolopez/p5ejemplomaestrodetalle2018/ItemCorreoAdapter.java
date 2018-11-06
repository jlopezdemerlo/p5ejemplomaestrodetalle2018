package net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * En esta ocasión vamos a partir de un BaseAdapter para que veais como
 * se puede crear un adaptador sobreescribiendo cada uno de los métodos necesarios.
 * Para el ejemplo podíamos haber utilizado sin ningún problema un ArrayAdapter
 */
public class ItemCorreoAdapter extends BaseAdapter {

    private List<Correo> correoArrayList = new ArrayList<Correo>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemCorreoAdapter(Context context, List<Correo> lista) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        correoArrayList=lista;
    }

    /**
     * Tamañao de la lista que se muestra
     * @return
     */
    @Override
    public int getCount() {
        return correoArrayList.size();
    }

    /**
     * Devuelve un correo dada la posición
     * @param position
     * @return
     */
    @Override
    public Correo getItem(int position) {
        return correoArrayList.get(position);
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Crea cada elemento que se mostrará en el listView
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_correo, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Correo) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    /**
     * Nos permite asignar los valores a visualizar en la lista mediante el viewholder
     * @param correo
     * @param holder
     */
    private void initializeViews(Correo correo, ViewHolder holder) {
        holder.tvFrom.setText(correo.getFrom());
        holder.tvSubject.setText(correo.getSubject());
    }

    /**
     * Elimina un correo de la lista
     * @param correo
     */
    public void addCorreo(Correo correo) {
        correoArrayList.add(0,correo);
        this.notifyDataSetChanged();
    }

    /**
     * Crea un correo de la lista
     * @param pos
     */
    public void delCorreo(int pos){
        correoArrayList.remove(pos);
        notifyDataSetChanged();
    }

    /**
     * Definimos el patrón viewHolder por motivos de eficiencia
     */
    static class ViewHolder {
        @BindView(R.id.tv_from)
        TextView tvFrom;
        @BindView(R.id.tv_subject)
        TextView tvSubject;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
