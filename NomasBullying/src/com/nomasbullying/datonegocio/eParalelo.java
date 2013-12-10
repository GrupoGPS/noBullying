package com.nomasbullying.datonegocio;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.nomasbullying.principalActivity;

public class eParalelo {
	private int paraleloID;
	private String nombre;
	private int cursoID;    
	
	private static String[] columnas = new String[] {"paraleloID", "nombre", "cursoID"};

	public eParalelo() {
		this.paraleloID = -1;
		this.nombre = null;
		this.cursoID = -1;
	}
	
	public eParalelo(int paraleloID, String nombre, int cursoID) {
		super();
		this.paraleloID = paraleloID;
		this.nombre = nombre;
		this.cursoID = cursoID;
	}
    
    public void insertParalelo(){
	String insertSQL="";	
    	if (this.getParaleloID()  == -1){
	    	insertSQL = "INSERT INTO paralelo VALUES (null, "
	                + "'"+ this.getNombre() + "', "
	                +  this.getCursoID() + ");";
		}else{
	    	insertSQL = "INSERT INTO paralelo VALUES ("+ this.getParaleloID() +", "
	                + "'"+ this.getNombre() + "', "
	                +  this.getCursoID() + ");";
		}
    	
	principalActivity.db.execSQL(insertSQL);        
    }
    
    public void updateParalelo(){
        String updateSQL = "update paralelo "
              + "set nombre = '" + this.getNombre() + "', "
              + "cursoID = " + this.getCursoID() + " "
              + "where paraleloID = " + this.getParaleloID() + ";";
        
        principalActivity.db.execSQL(updateSQL);
    }
    
    public void deleteParalelo(){
        String deleteSQL = "delete from paralelo " + "where paraleloID = " + this.getParaleloID() + ";";
        
        principalActivity.db.execSQL(deleteSQL);
    }

	public List<eParalelo> getParalelos() {
		
		ArrayList<eParalelo> entidad = new ArrayList<eParalelo>();
		Cursor result = principalActivity.db.query("paralelo",columnas, null, null, null, null, null);
			if (result.moveToFirst()){
			    do {
			    	entidad.add(new eParalelo(
					result.getInt(result.getColumnIndex("paraleloID")),
					result.getString(result.getColumnIndex("nombre")),
					result.getInt(result.getColumnIndex("cursoID")))
					);
					
			    }while(result.moveToNext());
			}
		
		return entidad;
	}
	
	public static void deleteById(int id){
		String queryDelete = "DELETE FROM paralelo WHERE paraleloID = "+id;
		 principalActivity.db.execSQL(queryDelete);
	}	
	
	public void saveOrUpdate(){
		String query = "SELECT * FROM paralelo WHERE paraleloID = "+this.getParaleloID();
		Cursor c = principalActivity.db.rawQuery(query, null);
		if(c.moveToFirst()){
			updateParalelo();
		}else{
			insertParalelo();
		}
	}
	
	public static eParalelo getParalelo(int paraleloID){   //  obtiene todos los datos de un paralelo
		String query = "SELECT * FROM paralelo WHERE paraleloID = "+paraleloID;
		Cursor c = principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){
				int idAux = c.getInt(0);
				String nombreAux = c.getString(1);
				int cursoIDAux = c.getInt(2);
				eParalelo par = new eParalelo(idAux, nombreAux, cursoIDAux);
				return par;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
	}
	
	public static eParalelo[] listParalelos(){    // hace una lista de todos los paralelos con datos 
		String query = "SELECT * FROM estudiante";
		Cursor c = principalActivity.db.rawQuery(query, null);
		eParalelo[] paralelos= null;
		try {
			if(c.moveToFirst()){
				paralelos= new eParalelo[c.getCount()];
				int m = 0;
				do{
					int idc = c.getInt(0);
					
					eParalelo par = eParalelo.getParalelo(idc);
					paralelos[m] = par;
					m++;					
					
				}while(c.moveToNext());			
				return paralelos;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
			
	}
	
	public static int countParalelo(){    // cuenta a todos los paralelos
		String query = "SELECT * FROM paralelo";
		Cursor c = principalActivity.db.rawQuery(query, null);
		int count = c.getCount();
		return count;
	}	
	
	public static  String[] ListNombresParalelos(){   // lista nombre de los paralelos
		String query = "SELECT * FROM paralelo";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] paralelos= null;
		try {
			if(c.moveToFirst()){
				paralelos= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(1);
					paralelos[m] = nombre;                 
					m++;					
				}while(c.moveToNext());			
				return paralelos;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
	}
	
	public static  String[] ListIDParalelos(){   // lista ID de los paralelos
		String query = "SELECT * FROM paralelo";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] paralelos= null;
		try {
			if(c.moveToFirst()){
				paralelos= new String[c.getCount()];
				int m = 0;
				do{
					String id = String.valueOf(c.getInt(0));
					paralelos[m] = id;
					m++;					
				}while(c.moveToNext());			
				return paralelos;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
			
	}

	public static int getIdParalelo(String nombre) {  //  obtiene el id del nombre lanzado
		String query = "SELECT * FROM paralelo";
		Cursor c =principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){			
				do{
					String nombreAux = c.getString(1);   
					if(nombreAux.equals(nombre)){
						return c.getInt(0);
					}
				}while(c.moveToNext());
				return -1;
			}else{
				return -1;
			}			
		} catch (Exception e) {
			return -1;
		}finally{
			c.close();
		}
	}

	public int getParaleloID() {
		return paraleloID;
	}

	public void setParaleloID(int paraleloID) {
		this.paraleloID = paraleloID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCursoID() {
		return cursoID;
	}

	public void setCursoID(int cursoID) {
		this.cursoID = cursoID;
	}

}
