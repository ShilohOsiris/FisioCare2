package com.fisiocare.nacionalidad_personas;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText txtusuario,txtpassword;
    private Button btningresar,btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtpassword=(EditText) findViewById(R.id.txtpassword);
        txtusuario=(EditText) findViewById(R.id.txtusuario);
        btningresar=findViewById(R.id.btnIngresar);
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear una intención para ir a la nueva actividad
                ingresar();
            }
        });
        btnCrear=findViewById(R.id.BTNCREAR);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, registrar_usuario.class);
                startActivity(intent);
            }
        });

    }

    public void ingresar(){
        try {
            AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion",null,1);
            SQLiteDatabase bd=admin.getWritableDatabase();
            String usuario=txtusuario.getText().toString();
            String password=txtpassword.getText().toString();
            Cursor fila=bd.rawQuery("Select Usuario,password from Usuario where usuario='"+usuario+"' and password='"
                    +password+"'",null);

            if (fila.moveToFirst()) {
                // Usuario encontrado, iniciar otra actividad
                Intent intent;
                intent = new Intent(MainActivity.this, Registrar_2.class);
                startActivity(intent);
            } else {
                // Usuario no encontrado, mostrar mensaje
                Toast.makeText(this, "Usuario no encontrado o credenciales incorrectas", Toast.LENGTH_LONG).show();
            }

            bd.close();


         }catch(Exception e){
            Toast.makeText(this,"Error en database"+e.toString(),Toast.LENGTH_LONG).show();
        }


    }

}