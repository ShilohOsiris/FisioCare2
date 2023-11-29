package com.fisiocare.nacionalidad_personas;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Buscamiento_ced extends AppCompatActivity {
    private EditText txtcorreo, txtciudad, txtpais, txtcedula_b, txtnombre, txtapellido;
    private RadioButton rbmujer, rbhombre;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_ced);
        txtapellido = (EditText) findViewById(R.id.TXT_APE_B);
        txtnombre = (EditText) findViewById(R.id.TXT_NOM_B);
        txtcorreo = (EditText) findViewById(R.id.TXT_CORREO_B);
        txtcedula_b = (EditText) findViewById(R.id.TXTCED_B);
        txtpais = (EditText) findViewById(R.id.TXT_PAIS_B);
        txtciudad = (EditText) findViewById(R.id.TXT_CIUDAD_B);
        rbmujer = (RadioButton) findViewById(R.id.RbMUJER);
        rbhombre = (RadioButton) findViewById(R.id.RBHOMBRE_B);
        btnBuscar=(Button) findViewById(R.id.BTN_BUSCAR_B);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            BuscarCed();
            }
        });
    }

    public void BuscarCed(){
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String cedula=txtcedula_b.getText().toString();

        Cursor fila=bd.rawQuery("Select cedula,nombre,apellido,genero,pais,ciudad,correo from Nacionalidades where cedula='"+cedula+
                "'",null);
        if(fila.moveToFirst()){
           rbmujer.setChecked(true);
            rbhombre.setText(fila.getString (0));
            txtnombre.setText(fila.getString(1));
            txtapellido.setText(fila.getString(2));
            if (fila.getString(3).equals("Mujer")) {
              rbmujer.setChecked(true);
            }else if(fila.getString(3).equals("Hombre")){
               rbhombre.setChecked(true);
            }
            txtpais.setText(fila.getString(4));
            txtciudad.setText(fila.getString(5));
            txtcorreo.setText(fila.getString(6));

        }else{
            Toast.makeText(this,"No existe una persona con este numero de Cedula",Toast.LENGTH_LONG).show();
            bd.close();
        }


    }
}