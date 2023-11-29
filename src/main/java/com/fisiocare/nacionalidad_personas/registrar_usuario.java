package com.fisiocare.nacionalidad_personas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;


import androidx.appcompat.app.AppCompatActivity;

public class registrar_usuario extends AppCompatActivity {
    private EditText txtuser,txtpass;
    private Button btnregis;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_us);
        txtuser = (EditText) findViewById(R.id.txtuser);
        txtpass = (EditText) findViewById(R.id.txtpass);
        btnregis=findViewById(R.id.btnregistrar);
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Insertar_datos();
            }
        });
    }

    public void Insertar_datos(){
        try {
            AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd=admin.getWritableDatabase();
            String usuario=txtuser.getText().toString();
            String password=txtpass.getText().toString();
            ContentValues registro=new ContentValues();
            registro.put("usuario",usuario );
            registro.put("password",password );

            bd.insert("Usuario", null, registro);
            bd.close();
            txtuser.setText("");
            txtpass.setText("");

            Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();


        }catch(Exception e){
            Toast.makeText(this,"Error en database"+e.toString(),Toast.LENGTH_LONG).show();
        }


    }


}
