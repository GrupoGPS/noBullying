package com.nomasbullying.datonegocio;

import java.util.ArrayList;
import java.util.List;
import com.nomasbullying.principalActivity;

import android.database.Cursor;

public class eDirector {
	private int directorID;
	private String nombreCompleto;
	private String correo;
	private int telefono;   
	
	private static String[] columnas = new String[] {"directorID", "nombreCompleto", "correo", "telefono"};

	public eDirector() {
		this.directorID = -1;
		this.nombreCompleto = null;
		this.correo = null;
		this.telefono = -1;
	}
	
	public eDirector(int directorID, String nombreCompleto, String correo, int telefono) {
		super();
		this.directorID = directorID;
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
		this.telefono = telefono;
	}
    
    public void insertDirector(){
	String insertSQL="";	
    	if (this.getDirectorID()  == -1){
	    	insertSQL = "INSERT INTO director VALUES (null, "
	                + "'"+ this.getNombreCompleto() + "', "
	                + "'"+ this.getCorreo() +"', "
	                +  this.getTelefono() + ");";
		}else{
	    	insertSQL = "INSERT INTO director VALUES ("+ this.getDirectorID() +", "
	                + "'"+ this.getNombreCompleto() + "', "
	                + "'"+ this.getCorreo() +"', "
	                +  this.getTelefono() + ");";
		}
    	
	principalActivity.db.execSQL(insertSQL);        
    }
    
    public void updateDirector(){
        String updateSQL = "update director "
              + "set nombreCompleto = '" + this.getNombreCompleto() + "', "
              + "correo = '" + this.getCorreo() + "', "
              + "telefono = " + this.getTelefono() + " "
              + "where directorID = " + this.getDirectorID() + ";";
        
        principalActivity.db.execSQL(updateSQL);
    }
    
    public void deleteDirector(){
        String deleteSQL = "delete from director " + "where directorID = " + this.getDirectorID() + ";";
        
        principalActivity.db.execSQL(deleteSQL);
    }

	public List<eDirector> getDirectores() {
		
		ArrayList<eDirector> entidad = new ArrayList<eDirector>();
		Cursor result = principalActivity.db.query("director",columnas, null, null, null, null, null);
			if (result.moveToFirst()){
			    do {
			    	entidad.add(new eDirector(
					result.getInt(result.getColumnIndex("directorID")),
					result.getString(result.getColumnIndex("nombreCompleto")),
					result.getString(result.getColumnIndex("correo")),
					result.getInt(result.getColumnIndex("telefono")))
					);
					
			    }while(result.moveToNext());
			}
		
		return entidad;
	}
	
	public static void deleteById(int id){
		String queryDelete = "DELETE FROM director WHERE directorID = "+id;
		 principalActivity.db.execSQL(queryDelete);
	}	
	
	public void saveOrUpdate(){
		String query = "SELECT * FROM director WHERE directorID = "+this.getDirectorID();
		Cursor c = principalActivity.db.rawQuery(query, null);
		if(c.moveToFirst()){
			updateDirector();
		}else{
			insertDirector();
		}
	}
	
	public static eDirector getDirector(int directorID){   //  obtiene todos los datos de un director
		String query = "SELECT * FROM director WHERE directorID = "+directorID;
		Cursor c = principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){
				int idAux = c.getInt(0);
				String nombreAux = c.getString(1);
				String correoAux = c.getString(2);
				int phoneAux = c.getInt(3);
				eDirector dir = new eDirector(idAux, nombreAux, correoAux, phoneAux);
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
	
	public static eDirector[] listDirectores(){    // hace una lista de todos los directores con datos 
		String query = "SELECT * FROM director";
		Cursor c = principalActivity.db.rawQuery(query, null);
		eDirector[] directores= null;
		try {
			if(c.moveToFirst()){
				directores= new eDirector[c.getCount()];
				int m = 0;
				do{
					int idc = c.getInt(0);
					
					eDirector cli = eDirector.getDirector(idc);
					directores[m] = cli;
					m++;					
					
				}while(c.moveToNext());			
				return directores;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
			
	}
	
	public static int countCliente(){    // cuenta a todos los clientes
		String query = "SELECT * FROM director";
		Cursor c = principalActivity.db.rawQuery(query, null);
		int count = c.getCount();
		return count;
	}	
	
	public static  String[] ListNombresDirectores(){   // lista nombre de los directores
		String query = "SELECT * FROM director";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] directores= null;
		try {
			if(c.moveToFirst()){
				directores= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(1);
					directores[m] = nombre;                 
					m++;					
				}while(c.moveToNext());			
				return directores;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
	}
	
	public static  String[] ListIDDirectores(){   // lista ID de los Directores
		String query = "SELECT * FROM director";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] directores= null;
		try {
			if(c.moveToFirst()){
				directores= new String[c.getCount()];
				int m = 0;
				do{
					String id = String.valueOf(c.getInt(0));
					directores[m] = id;
					m++;					
				}while(c.moveToNext());			
				return directores;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
			
	}

	public static int getIdDirector(String nombre) {  //  obtiene el id del nombre lanzado
		String query = "SELECT * FROM director";
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

	public int getDirectorID() {
		return directorID;
	}

	public void setDirectorID(int directorID) {
		this.directorID = directorID;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
