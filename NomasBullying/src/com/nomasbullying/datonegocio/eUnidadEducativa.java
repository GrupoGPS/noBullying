package com.nomasbullying.datonegocio;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.nomasbullying.principalActivity;

public class eUnidadEducativa {
	
	private int unidadEducativaID;
	private String nombre;
	private String distrito;
	private String direccion;
	private int directorID;    
	
	private static String[] columnas = new String[] {"unidadEducativaID", "nombre", "distrito", "direccion", "directorID"};

	public eUnidadEducativa() {
		this.unidadEducativaID = -1;
		this.nombre = null;
		this.distrito = null;
		this.direccion = null;
		this.directorID = -1;
	}
	
	public eUnidadEducativa(int unidadEducativaID, String nombre, String distrito, String direccion, int directorID) {
		super();
		this.unidadEducativaID = unidadEducativaID;
		this.nombre = nombre;
		this.distrito = distrito;
		this.direccion = direccion;
		this.directorID = directorID;
	}
    
    public void insertUnidadEducativa(){
	String insertSQL="";	
    	if (this.getUnidadEducativaID()  == -1){
	    	insertSQL = "INSERT INTO unidadeducativa VALUES (null, "
	                + "'"+ this.getNombre() + "', "
	                + "'"+ this.getDistrito() +"', "
	                + "'"+ this.getDireccion() +"', "
	                +  this.getDirectorID() + ");";
		}else{
	    	insertSQL = "INSERT INTO unidadeducativa VALUES ("+ this.getUnidadEducativaID() +", "
	                + "'"+ this.getNombre() + "', "
	                + "'"+ this.getDistrito() +"', "
	                + "'"+ this.getDireccion() +"', "
	                +  this.getDirectorID() + ");";		}
    	
	principalActivity.db.execSQL(insertSQL);        
    }
    
    public void updateUnidadEducativa(){
        String updateSQL = "update unidadeducativa "
              + "set nombre = '" + this.getNombre() + "', "
              + "distrito = '" + this.getDistrito() + "', "
              + "direccion = '" + this.getDireccion() + "', "
              + "directorID = " + this.getDirectorID() + " "
              + "where unidadEducativaID = " + this.getUnidadEducativaID() + ";";
        
        principalActivity.db.execSQL(updateSQL);
    }
    
    public void deleteUnidadEducativa(){
        String deleteSQL = "delete from unidadeducativa " + "where unidadEducativaID = " + this.getUnidadEducativaID() + ";";
        
        principalActivity.db.execSQL(deleteSQL);
    }

	public List<eUnidadEducativa> getUnidadEducativas() {
		
		ArrayList<eUnidadEducativa> entidad = new ArrayList<eUnidadEducativa>();
		Cursor result = principalActivity.db.query("unidadeducativa",columnas, null, null, null, null, null);
			if (result.moveToFirst()){
			    do {
			    	entidad.add(new eUnidadEducativa(
					result.getInt(result.getColumnIndex("unidadEducativaID")),
					result.getString(result.getColumnIndex("nombre")),
					result.getString(result.getColumnIndex("distrito")),
					result.getString(result.getColumnIndex("direccion")),
					result.getInt(result.getColumnIndex("directorID")))
					);
					
			    }while(result.moveToNext());
			}
		
		return entidad;
	}
	
	public static void deleteById(int id){
		String queryDelete = "DELETE FROM unidadeducativa WHERE unidadEducativaID = "+id;
		 principalActivity.db.execSQL(queryDelete);
	}	
	
	public void saveOrUpdate(){
		String query = "SELECT * FROM unidadeducativa WHERE unidadEducativaID = "+this.getUnidadEducativaID();
		Cursor c = principalActivity.db.rawQuery(query, null);
		if(c.moveToFirst()){
			updateUnidadEducativa();
		}else{
			insertUnidadEducativa();
		}
	}
	
	public static eUnidadEducativa getUnidadEducativa(int ueID){   //  obtiene todos los datos de una UE unidad educativa
		String query = "SELECT * FROM unidadeducativa WHERE unidadEducativaID = "+ueID;
		Cursor c = principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){
				int idAux = c.getInt(0);
				String nombreAux = c.getString(1);
				String distritoAux = c.getString(2);
				String direccionAux = c.getString(3);
				int directorIDAux = c.getInt(4);
				eUnidadEducativa dir = new eUnidadEducativa(idAux, nombreAux, distritoAux, direccionAux, directorIDAux);
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
	
	public static eUnidadEducativa[] listUnidadEducativas(){    // hace una lista de todos los UE con datos 
		String query = "SELECT * FROM unidadeducativa";
		Cursor c = principalActivity.db.rawQuery(query, null);
		eUnidadEducativa[] ues= null;
		try {
			if(c.moveToFirst()){
				ues= new eUnidadEducativa[c.getCount()];
				int m = 0;
				do{
					int idc = c.getInt(0);
					
					eUnidadEducativa ue = eUnidadEducativa.getUnidadEducativa(idc);
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
	
	public static int countUE(){    // cuenta a todos los UEs
		String query = "SELECT * FROM unidadeducativa";
		Cursor c = principalActivity.db.rawQuery(query, null);
		int count = c.getCount();
		return count;
	}	
	
	public static  String[] ListNombresUnidadEducativas(){   // lista nombre de los UE
		String query = "SELECT * FROM unidadeducativa";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] ues= null;
		try {
			if(c.moveToFirst()){
				ues= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(1);
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
	
	public static  String[] ListIDUnidadEducativas(){   // lista ID de los UEs
		String query = "SELECT * FROM unidadeducativa";
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

	public static int getIdUnidadEducativa(String nombre) {  //  obtiene el id del nombre lanzado
		String query = "SELECT * FROM unidadeducativa";
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

	public int getUnidadEducativaID() {
		return unidadEducativaID;
	}

	public void setUnidadEducativaID(int unidadEducativaID) {
		this.unidadEducativaID = unidadEducativaID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getDirectorID() {
		return directorID;
	}

	public void setDirectorID(int directorID) {
		this.directorID = directorID;
	}
	
}
