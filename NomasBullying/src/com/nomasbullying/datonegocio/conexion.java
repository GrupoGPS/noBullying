package com.nomasbullying.datonegocio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class conexion extends SQLiteOpenHelper{

	private static String DB_PATH = "/data/data/com.nomasbullying/databases/";
	
	private static String DB_NAME = "bullydb.db";
	
	private SQLiteDatabase myDataBase;
	 
	private final Context myContext;	
	
	String estudiante ="CREATE TABLE IF NOT EXISTS estudiante ("+
			"estudianteID integer PRIMARY KEY AUTOINCREMENT NOT NULL ,"+
			"nombreCompleto TEXT NOT NULL,"+
			"correo TEXT NOT NULL,"+
			"telefono integer NOT NULL,"+
			"fechaNacimiento TEXT NOT NULL,"+
			"paraleloID integer NOT NULL," +
			"FOREIGN KEY (paraleloID) REFERENCES paralelo(paraleloID)" +
			");";
	
	String director ="CREATE TABLE IF NOT EXISTS director ("+
			"directorID integer PRIMARY KEY AUTOINCREMENT NOT NULL ,"+
			"nombreCompleto TEXT NOT NULL,"+
			"correo TEXT NOT NULL,"+
			"telefono integer NOT NULL"+
			");";	

	String registro ="CREATE TABLE IF NOT EXISTS registro ("+
			"registroID integer PRIMARY KEY AUTOINCREMENT NOT NULL ,"+
			"unidadeducativa TEXT NOT NULL"+
			");";	
	
	String alerta ="CREATE TABLE IF NOT EXISTS alerta ( "+
			"alertaID integer PRIMARY KEY AUTOINCREMENT NOT NULL ," +
			"descripcion TEXT NOT NULL," +
			"victimaID integer NOT NULL," +
			"agresorID integer NOT NULL," +
			"FOREIGN KEY (victimaID) REFERENCES estudiante(estudianteID)," +
			"FOREIGN KEY (agresorID) REFERENCES estudiante(estudianteID)" +
			");";
	
	String curso ="CREATE TABLE IF NOT EXISTS curso ( "+
			"cursoID integer PRIMARY KEY AUTOINCREMENT NOT NULL ," +
			"nivel TEXT NOT NULL," +
			"grado integer NOT NULL," +
			"unidadEducativaID integer NOT NULL," +
			"FOREIGN KEY (unidadeducativaID) REFERENCES unidadeducativa(unidadeducativaID)" +
			");";	
	
	String paralelo ="CREATE TABLE IF NOT EXISTS paralelo ( "+
			"paraleloID integer PRIMARY KEY AUTOINCREMENT NOT NULL , "+
			"nombre TEXT NOT NULL, "+
			"cursoID integer NOT NULL," +
			"FOREIGN KEY (cursoID) REFERENCES curso(cursoID)" +
			");";	

	String prueba ="CREATE TABLE IF NOT EXISTS prueba ( "+
			"pruebaID integer PRIMARY KEY AUTOINCREMENT NOT NULL , "+
			"imagen longblob NOT NULL,"+
			"alertaID integer NOT NULL," +
			"FOREIGN KEY (alertaID) REFERENCES alerta(alertaID)" +
			");";	
	
	String unidadeducativa ="CREATE TABLE IF NOT EXISTS unidadeducativa ( "+
			"unidadEducativaID integer PRIMARY KEY AUTOINCREMENT NOT NULL , "+
			"nombre TEXT NOT NULL, "+
			"distrito TEXT NOT NULL, "+
			"direccion TEXT, "+
			"directorID integer NOT NULL," +
			"FOREIGN KEY (directorID) REFERENCES director(directorID)" +
			");";		
	

	public conexion(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	public void createDataBase() throws IOException{
		 
	boolean dbExist = checkDataBase();
	 
		if(dbExist){

		}else{

		this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copiando Base de Datos");
			}
		}
	 
	}

	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){

		}
		if(checkDB != null){
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}
	 
	public void copyDataBase() throws IOException{

		InputStream myInput = myContext.getAssets().open(DB_NAME);
		 
		String outFileName = DB_PATH + DB_NAME;
		 
		OutputStream myOutput = new FileOutputStream(outFileName);
		 
		byte[] buffer = new byte[1024];
		int length;
		
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	 
	public void open() throws SQLException{
		try {
			createDataBase();
		} catch (IOException e) {
			throw new Error("Ha sido imposible crear la Base de Datos");
		}

		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	}
	 
	@Override
	public synchronized void close() {
		if(myDataBase != null)
		myDataBase.close();
		super.close();
	}	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		  db.execSQL(director);
		  db.execSQL(registro);
		  db.execSQL(unidadeducativa);
		  db.execSQL(curso);
		  db.execSQL(alerta);
		  db.execSQL(prueba);
		  db.execSQL(paralelo);
		  db.execSQL(estudiante);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS director");
		db.execSQL(director);
		db.execSQL("DROP TABLE IF EXISTS informante");
		db.execSQL(registro);
		db.execSQL("DROP TABLE IF EXISTS unidadeducativa");
		db.execSQL(unidadeducativa);
		db.execSQL("DROP TABLE IF EXISTS curso");
		db.execSQL(curso);
		db.execSQL("DROP TABLE IF EXISTS alerta");
		db.execSQL(alerta);
		db.execSQL("DROP TABLE IF EXISTS pruebas");
		db.execSQL(prueba);
		db.execSQL("DROP TABLE IF EXISTS paralelo");
		db.execSQL(paralelo);
		db.execSQL("DROP TABLE IF EXISTS estudiante");
		db.execSQL(estudiante);
	}

}
