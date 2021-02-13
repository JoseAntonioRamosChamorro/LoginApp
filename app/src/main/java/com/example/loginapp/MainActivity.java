package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edNombre, edContra;
    Button b1;
    String usu, contra;
    Switch aSwitch;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;
    public static final String Nombre = "NombreKey";
    public static final String Contra = "Contrakey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNombre = findViewById(R.id.etNombre);
        edContra = findViewById(R.id.edPass);
        b1 = findViewById(R.id.buttonGuardar);
        aSwitch = findViewById(R.id.switchGuarar);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //informacion guardada
        String isShared = sharedPreferences.getString(Nombre, "");
        if (isShared != null) {
            edNombre.setText(sharedPreferences.getString(Nombre, ""));
            edContra.setText(sharedPreferences.getString(Contra, ""));
        }

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.switchGuarar) {
                    if (aSwitch.isChecked()) {
                        String n = edNombre.getText().toString();
                        String ph = edContra.getText().toString();

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Nombre, n);
                        editor.putString(Contra, ph);
                        editor.commit();
                        Toast.makeText(MainActivity.this, "Datos Guardados", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usu = edNombre.getText().toString();
                contra = edContra.getText().toString();
                //comparar
                if (usu.equals("joseantonio") && contra.equals("49123355R")) {
                    //cambiar activity
                    Intent intent = new Intent(v.getContext(),Login.class);
                    startActivityForResult(intent,0);
                    //guardando los datos
                } else {
                    Toast.makeText(getApplicationContext(), "Error contraseña", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}