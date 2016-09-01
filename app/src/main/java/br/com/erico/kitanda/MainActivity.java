package br.com.erico.kitanda;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lsvListaCompras;
    private EditText edtEmail;
    private List<Fruta> frutas;
    private List<Fruta> compras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frutas = getAll();
        compras = new ArrayList<>();

        lsvListaCompras = (ListView) findViewById(R.id.lsvListaCompras);
        lsvListaCompras.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        ArrayAdapter adapter =
//                new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, frutas);
        FrutaAdapter adapter =
                new FrutaAdapter(MainActivity.this, R.layout.fruta_detalhe, frutas);
        lsvListaCompras.setAdapter(adapter);

        edtEmail = (EditText) findViewById(R.id.edtEmail);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: save your shopping list

                String email = edtEmail.getEditableText().toString();
                email = email.trim().toLowerCase();

                if (email.isEmpty() || !email.contains("@")) {
                    String message = "Digite um email válido";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                    Integer interval = 250;
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(interval);

                } else {
                    edtEmail.setText(email);

                    /*
                    for (long item : lsvListaCompras.getCheckItemIds()) {
                        int position = (int) item;
                        compras.add(frutas.get(position));
                    }
                    */
                    for (Fruta fruta : frutas) {
                        if (fruta.getQuantidade() > 0) {
                            compras.add(fruta);
                        }
                    }

                    Database database = new Database(MainActivity.this);
                    database.gravarCompras(compras, email);
                    database.close();

                    String message = "Lista de compras gravadas. " + compras.size() + " items.";

                    compras.clear();
                    edtEmail.setText("");
                    lsvListaCompras.clearChoices();

                    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
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
                list.add(new Fruta(line, MainActivity.this));
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
