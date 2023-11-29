package com.fisiocare.nacionalidad_personas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registrar_2 extends AppCompatActivity {
private EditText txtnombre,txtapellido,txtcedula,txtpais,txtciudad,txtcorreo;
private RadioButton rbhombre,rbmujer;
private Button btningresar,btnbuscamiento,btnactualizar,btnborrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_nacionalidad);

        // Inicializar tus elementos de interfaz de usuario aquí
        txtnombre = findViewById(R.id.txtnombre);
        txtapellido = findViewById(R.id.txtapellido);
        txtcedula = findViewById(R.id.txtcedula);
        txtpais = findViewById(R.id.txtpais);
        txtciudad = findViewById(R.id.txtciudad);
        txtcorreo = findViewById(R.id.txtcorreo);
        rbhombre = findViewById(R.id.rbhombre);
        rbmujer = findViewById(R.id.rbmujer);

        btningresar = findViewById(R.id.btnregis);
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para registrar nacionalidad
                Registrar_nacionalidad();
            }
        });

        btnbuscamiento = findViewById(R.id.btnbuscamineto);
        btnbuscamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para el otro botón
                Intent intent;
                intent = new Intent(Registrar_2.this, Buscamiento_ced.class);
                startActivity(intent);
            }
        });

        btnactualizar = findViewById(R.id.btnactualizar);
        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para el otro botón
                actualizar();
            }
        });

        btnborrar = findViewById(R.id.btnborrar);
        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para el otro botón
                Borrar();
            }
        });


    }




    public void Registrar_nacionalidad() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = txtnombre.getText().toString();
        String apellido=txtapellido.getText().toString();
        String cedula = txtcedula.getText().toString();
        String pais = txtpais.getText().toString();
        String genero = "null";
        if (rbhombre.isChecked()) {
            genero = "Hombre";
        } else if (rbmujer.isChecked()) {
            genero = "Mujer";
        }

        String ciudad = txtciudad.getText().toString();
        String correo = txtcorreo.getText().toString();
        if (cedula.equals("")) {
            Toast.makeText(this, "Debes ingresar tu cédula", Toast.LENGTH_LONG).show();
        } else if (nombre.equals("")) {
            Toast.makeText(this, "Debes ingresar tus nombres", Toast.LENGTH_LONG).show();
        } else if (genero.equals("null")) {
            Toast.makeText(this, "Debes seleccionar tu genero", Toast.LENGTH_LONG).show();
        } else if (pais.equals("Seleccione su Pais")) {
            Toast.makeText(this, "Debes seleccionar tu pais", Toast.LENGTH_LONG).show();
        } else if (ciudad.equals("")) {
            Toast.makeText(this, "Debes ingresar tu provincia", Toast.LENGTH_LONG).show();
        } else if (correo.equals("")) {
            Toast.makeText(this, "Debes ingresar tu correo", Toast.LENGTH_LONG).show();
        } else {
            ContentValues datosPersona = new ContentValues();
            datosPersona.put("cedula", cedula);
            datosPersona.put("nombre", nombre);
            datosPersona.put("apellido",apellido);
            datosPersona.put("genero", genero);
            datosPersona.put("pais", pais);
            datosPersona.put("ciudad", ciudad);
            datosPersona.put("correo", correo);
            try {
                bd.insert("Nacionalidades", null, datosPersona);
                bd.close();
                txtnombre.setText("");
                txtciudad.setText("");
                txtpais.setText("");
                txtapellido.setText("");
                rbhombre.setChecked(false);
                rbmujer.setChecked(false);
                txtpais.setText("");
                txtcorreo.setText("");
                Toast.makeText(this, "Datos ingresados con exito" + genero, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void actualizar(){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String cedula=txtcedula.getText().toString();
        String nombre=txtnombre.getText().toString();
        String apellido=txtapellido.getText().toString();
        String genero="";
        if(rbhombre.isChecked()){
            genero="Hombre";

        }else if (rbmujer.isChecked()){
            genero="Mujer";
        }
        String pais=txtpais.getText().toString();
        String ciudad=txtciudad.getText().toString();
        String correo=txtcorreo.getText().toString();
        ContentValues datosPersona=new ContentValues();
        datosPersona.put("cedula",cedula);
        datosPersona.put("nombre",nombre);
        datosPersona.put("apellido",apellido);
        datosPersona.put("genero",genero);
        datosPersona.put("pais",pais);
        datosPersona.put("ciudad",ciudad);
        datosPersona.put("correo",correo);
        int cant=bd.update("Nacionalidades",datosPersona,"cedula='"+cedula+"'",null);
        bd.close();
        if(cant==1){
            Toast.makeText(this,"Se aplicaron los cambios",Toast.LENGTH_LONG).show();
            txtnombre.setText("");
            txtciudad.setText("");
            txtpais.setText("");
            txtapellido.setText("");
            rbhombre.setChecked(false);
            rbmujer.setChecked(false);
            txtpais.setText("");
            txtcorreo.setText("");
        }else{
            Toast.makeText(this,"No existe una persona con este numero de cédula",Toast.LENGTH_LONG).show();
        }

    }

    public void Borrar(){

        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String cedula=txtcedula.getText().toString();
        int cant = bd.delete("Nacionalidades","cedula='"+cedula+"'",null);
        bd.close();
        if(cant==1){
            Toast.makeText(this,"Se borro la persona con el numero de cedula",Toast.LENGTH_LONG).show();
            txtcedula.setText("");
            txtnombre.setText("");
            txtciudad.setText("");
            txtapellido.setText("");
            rbmujer.setChecked(false);
            rbhombre.setChecked(false);
            txtpais.setText("");
        }else{
            Toast.makeText(this,"No existe una persona con este numero de cédula",Toast.LENGTH_LONG).show();
        }
    }
}
