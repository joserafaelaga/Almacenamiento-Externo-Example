package com.example.eag.almacenamientoexterno;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    //¡¡¡NO OLVIDAR PERMISOS!!!
    /*
    *  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    *  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    * */

    //Los objetos File son para comprobar donde se guarda el archivo

    EditText nombre, contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.nombre);
        contenido = (EditText) findViewById(R.id.contenido);
    }

    //Método para guardar
    public void guardar(View view){

        //Recuperamos el nombre del archivo en el que queremos guardar y el contenido a guardar
        String nom_archivo = nombre.getText().toString();
        String cont = contenido.getText().toString();

        //Recuperamos la ruta donde está la tarjeta SD
        //File tarjetaSD = Environment.getExternalStorageDirectory();

        //Mostramos la ruta donde está la tarjeta SD
        //Toast.makeText(this, tarjetaSD.getPath(),Toast.LENGTH_SHORT).show();

        //Creamos el archivo
        //File rutaArchivo = new File (tarjetaSD.getPath(), nom_archivo);


        try {
            //Abrimos flujo de lectura a nuestro archivo
            OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(nom_archivo, Activity.MODE_PRIVATE));

            //Escribir archivo
            crearArchivo.write(cont);

            //Borramos buffer
            crearArchivo.flush();

            //Cerramos
            crearArchivo.close();

            Toast.makeText(this, "Fichero guardado correctamente", Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error.Fichero no guardado", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error.Fichero no guardado", Toast.LENGTH_SHORT).show();
        }

        nombre.setText("");
        contenido.setText("");

    }

    //Método para leer
    public void leer(View view){
        //Recuperamos nombre del archivo que queremos leer
        String nom_archivo = nombre.getText().toString();

        //Recuperamos la ruta donde está la tarjeta SD
        //File tarjetaSD = Environment.getExternalStorageDirectory();

        //Creamos el archivo
       // File rutaArchivo = new File (tarjetaSD.getPath(), nom_archivo);

        try {
            //Abrimos flujo de lectura a nuestro archivo
            InputStreamReader leerArchivo = new InputStreamReader(openFileInput(nom_archivo));

            //Leemos nuestro archivo
            BufferedReader br = new BufferedReader(leerArchivo);

            String linea="";
            String cont="";

            while((linea = br.readLine()) != null){
                cont += linea;
            }

            br.close();
            leerArchivo.close();

            //Mostramos el contenido
            contenido.setText(cont);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
