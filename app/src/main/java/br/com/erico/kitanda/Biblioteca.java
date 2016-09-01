package br.com.erico.kitanda;

import android.util.Log;

import java.text.Normalizer;

/**
 * Created by Mobile on 31/08/2016.
 */
public class Biblioteca {

    static String toImageName(String name) {
        String imageName = new String();

        if (name == null) {
            return null;
        }

        imageName = name
                        .trim()
                        .toLowerCase()
                        .replaceAll(" ", "_");

       imageName = Normalizer.normalize(imageName, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        Log.i("lib", imageName);

        return imageName;
    }

}
