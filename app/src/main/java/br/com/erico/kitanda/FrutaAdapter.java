package br.com.erico.kitanda;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mobile on 26/08/2016.
 */
public class FrutaAdapter extends ArrayAdapter<Fruta> {

    private List<Fruta> frutas;
    private Context context;
    private FrutaHolder holder;
    private int resource;


    public FrutaAdapter(Context context, int resource, List<Fruta> frutas) {
        super(context, resource, frutas);

        this.frutas = frutas;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if(row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new FrutaHolder();
            holder.imvFruta = (ImageView) row.findViewById(R.id.imvFruta);
            holder.txvNome = (TextView) row.findViewById(R.id.txvNome);
            holder.txvPreco = (TextView) row.findViewById(R.id.txvPreco);
            holder.btnMenos = (Button) row.findViewById(R.id.btnMenos);
            holder.btnMais = (Button) row.findViewById(R.id.btnMais);
            holder.edtQuantidade = (EditText) row.findViewById(R.id.edtQuantidade);

            row.setTag(holder);

        } else {

            holder = (FrutaHolder) row.getTag();

        }

        Fruta fruta = frutas.get(position);

        holder.imvFruta.setImageResource(R.drawable.abacaxi);
        holder.txvNome.setText(fruta.getNome());
        holder.txvPreco.setText(String.valueOf(fruta.getPreco()));

        return row;
    }

    static class FrutaHolder {

        ImageView imvFruta;
        TextView txvNome;
        TextView txvPreco;
        Button btnMenos;
        Button btnMais;
        EditText edtQuantidade;



    }
}
