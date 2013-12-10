package com.nomasbullying.datonegocio;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.provider.MediaStore.Images;

import com.nomasbullying.principalActivity;

public class ePrueba {
	
	private int pruebaID;
	private String imagen;         // ver funcionalidad si no cambiar Images
	private int alertaID;    
	
	private static String[] columnas = new String[] {"pruebaID", "imagen", "alertaID"};

	public ePrueba() {
		this.pruebaID = -1;
		this.imagen = null;
		this.alertaID = -1;
	}
	
	public ePrueba(int pruebaID, String imagen, int alertaID) {
		super();
		this.pruebaID = pruebaID;
		this.imagen = imagen;
		this.alertaID = alertaID;
	}
    
    public void insertPrueba(){
	String insertSQL="";	
    	if (this.getPruebaID()  == -1){
	    	insertSQL = "INSERT INTO prueba VALUES (null, "
	                + "'"+ this.getImagen() +"', "
	                +  this.getAlertaID() + ");";
		}else{
	    	insertSQL = "INSERT INTO prueba VALUES ("+ this.getPruebaID() +", "
	                + "'"+ this.getImagen() +"', "
	                +  this.getAlertaID() + ");";
	    }
    	
	principalActivity.db.execSQL(insertSQL);        
    }
    
    public void updatePrueba(){
        String updateSQL = "update prueba "
              + "set imagen = '" + this.getImagen() + "', "
              + "alertaID = " + this.getAlertaID() + " "
              + "where pruebaID = " + this.getPruebaID() + ";";
        
        principalActivity.db.execSQL(updateSQL);
    }
    
    public void deletePrueba(){
        String deleteSQL = "delete from prueba " + "where pruebaID = " + this.getPruebaID() + ";";
        
        principalActivity.db.execSQL(deleteSQL);
    }

	public List<ePrueba> getPruebas() {
		
		ArrayList<ePrueba> entidad = new ArrayList<ePrueba>();
		Cursor result = principalActivity.db.query("prueba",columnas, null, null, null, null, null);
			if (result.moveToFirst()){
			    do {
			    	entidad.add(new ePrueba(
					result.getInt(result.getColumnIndex("pruebaID")),
					result.getString(result.getColumnIndex("imagen")),
					result.getInt(result.getColumnIndex("alertaID")))
					);
					
			    }while(result.moveToNext());
			}
		
		return entidad;
	}
	
	public static void deleteById(int id){
		String queryDelete = "DELETE FROM pruebas WHERE pruebasID = "+id;
		 principalActivity.db.execSQL(queryDelete);
	}	
	
	public void saveOrUpdate(){
		String query = "SELECT * FROM pruebas WHERE pruebasID = "+this.getPruebaID();
		Cursor c = principalActivity.db.rawQuery(query, null);
		if(c.moveToFirst()){
			updatePrueba();
		}else{
			insertPrueba();
		}
	}
	
	public static ePrueba getPruebas(int pruebaID){   //  obtiene todos los datos de una prueba
		String query = "SELECT * FROM prueba WHERE pruebaID = "+pruebaID;
		Cursor c = principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){
				int idAux = c.getInt(0);
				String imagenAux = c.getString(1);
				int alertaIDAux = c.getInt(2);
				ePrueba dir = new ePrueba(idAux, imagenAux, alertaIDAux);
				return dir;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
	}
	
	public static ePrueba[] listPruebas(){    // hace una lista de todos los pruebas con datos 
		String query = "SELECT * FROM prueba";
		Cursor c = principalActivity.db.rawQuery(query, null);
		ePrueba[] ues= null;
		try {
			if(c.moveToFirst()){
				ues= new ePrueba[c.getCount()];
				int m = 0;
				do{
					int idc = c.getInt(0);
					
					ePrueba ue = ePrueba.getPruebas(idc);
					ues[m] = ue;
					m++;					
					
				}while(c.moveToNext());			
				return ues;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
			
	}
	
	public static int countPrueba(){    // cuenta a todos los pruebas
		String query = "SELECT * FROM prueba";
		Cursor c = principalActivity.db.rawQuery(query, null);
		int count = c.getCount();
		return count;
	}	
	
	/*public static  String[] ListNombresLugares(){   // lista nombre de los lugares bullying
		String query = "SELECT distinct(p.lugar) FROM prueba p";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] ues= null;
		try {
			if(c.moveToFirst()){
				ues= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(0);
					ues[m] = nombre;                 
					m++;					
				}while(c.moveToNext());			
				return ues;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
	}
	
	public static  String[] ListNombresComportamientos(){   // lista nombre de los comportamientos bullying
		String query = "SELECT distinct(p.comportamiento) FROM prueba p";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] ues= null;
		try {
			if(c.moveToFirst()){
				ues= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(0);
					ues[m] = nombre;                 
					m++;					
				}while(c.moveToNext());			
				return ues;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
	}	
	
	public static  String[] ListNombresGravedad(){   // lista nombre de los gravedad bullying
		String query = "SELECT distinct(p.gravedad) FROM prueba p";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] ues= null;
		try {
			if(c.moveToFirst()){
				ues= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(0);
					ues[m] = nombre;                 
					m++;					
				}while(c.moveToNext());			
				return ues;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
	}	*/
	
	public static  String[] ListIDPruebas(){   // lista ID de los pruebas
		String query = "SELECT * FROM prueba";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] ues= null;
		try {
			if(c.moveToFirst()){
				ues= new String[c.getCount()];
				int m = 0;
				do{
					String id = String.valueOf(c.getInt(0));
					ues[m] = id;
					m++;					
				}while(c.moveToNext());			
				return ues;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
			
	}

	public static int getIdPrueba(String nombre) {  //  obtiene el id del nombre lanzado
		String query = "SELECT * FROM prueba";
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

	public int getPruebaID() {
		return pruebaID;
	}

	public void setPruebaID(int pruebaID) {
		this.pruebaID = pruebaID;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getAlertaID() {
		return alertaID;
	}

	public void setAlertaID(int alertaID) {
		this.alertaID = alertaID;
	}

}
