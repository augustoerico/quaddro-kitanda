package br.com.erico.kitanda;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lsvListaCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsvListaCompras = (ListView) findViewById(R.id.lsvListaCompras);
        lsvListaCompras.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter adapter =
                new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, getAll());
        lsvListaCompras.setAdapter(adapter);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: save your shopping list

                Snackbar.make(view, "Lista de compras gravada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private List<Fruta> getAll() {
        ArrayList<Fruta> list = new ArrayList<>();

        InputStream stream = getResources().openRawResource(R.raw.catalogo_de_frutas);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line = new String();
            while ((line = reader.readLine()) != null) {
                Log.d("MainActivity.addAll", line);
                list.add(new Fruta(line));
            }

            reader.close();

        } catch (FileNotFoundException e) {
            Log.e("MainActivity.addAll", e.getMessage());
        } catch (IOException e) {
            Log.e("MainActivity.addAll", e.getMessage());
        }

        return list;
    }
}
