package br.com.erico.kitanda;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by erico on 8/25/16.
 */
public class Database extends SQLiteOpenHelper {

    private final static String DATABASE = "kitanda.db";
    private final static Integer VERSAO = 1;
    private final static String TABELA_COMPRAS = "compras";

    public Database(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        StringBuffer comando = new StringBuffer();
        comando.append("CREATE TABLE " + TABELA_COMPRAS + " (");
        comando.append("id          INTEGER PRIMARY KEY, ");
        comando.append("codigo      TEXT,");
        comando.append("quantidade  INTEGER,");
        comando.append("data_compra TEXT,");
        comando.append("preco       REAL,");
        comando.append("total       REAL,");
        comando.append("email       TEXT");
        comando.append(");");

        sqLiteDatabase.execSQL(comando.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        StringBuffer comando = new StringBuffer();
        comando.append("DROP TABLE IF EXISTS " + TABELA_COMPRAS);

        sqLiteDatabase.execSQL(comando.toString());
        onCreate(sqLiteDatabase);
    }

    public void gravarCompras(List<Fruta> frutas, String email) {

        for(Fruta fruta : frutas) {

            ContentValues values = new ContentValues();
            values.put("codigo", fruta.getCodigo());
            values.put("quantidade", 1);
            values.put("data_compra", new Date().toString());
            values.put("preco", fruta.getPreco());
            values.put("total", fruta.getPreco() * 1);
            values.put("email", email);

            getWritableDatabase().insert(TABELA_COMPRAS, null, values);
        }

    }
}
