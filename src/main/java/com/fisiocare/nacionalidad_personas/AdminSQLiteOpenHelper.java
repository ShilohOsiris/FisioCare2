package com.fisiocare.nacionalidad_personas;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table Nacionalidades(cedula interger primary key,nombre text,apellido text,pais text,correo text,genero text,ciudad text)");
        db.execSQL("create table Usuario(Usuario varchar primary key,password varchar)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists Nacionalidades");
        db.execSQL(" create table Nacionalidades(cedula interger primary key,nombre text,apellido text,pais text,correo text,genero text,ciudad text)");
        db.execSQL("drop table if exists Usuario");
        db.execSQL("create table Usuario(Usuario varchar primary key,password varchar)");

    }
}

