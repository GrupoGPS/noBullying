package com.nomasbullying.datonegocio;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.nomasbullying.principalActivity;

public class eAlerta {
	
	private int alertaID;
	private String descripcion;
	private int victimaID;
	private int agresorID;
	
	private static String[] columnas = new String[] {"alertaID", "descripcion", "victimaID", "agresorID"};

	public eAlerta() {
		this.alertaID = -1;
		this.descripcion = null;
		this.victimaID = -1;
		this.agresorID = -1;
	}
	
	public eAlerta(int alertaID, String descripcion, int victimaID, int agresorID) {
		super();
		this.alertaID = alertaID;
		this.descripcion = descripcion;
		this.victimaID = victimaID;
		this.agresorID = agresorID;
	}
    
    public void insertAlerta(){
	String insertSQL="";	
    	if (this.getAlertaID()  == -1){
	    	insertSQL = "INSERT INTO alerta VALUES (null, "
	                + "'"+ this.getDescripcion() + "', "
	                +  this.getVictimaID() +", "
	                +  this.getAgresorID() + ");";
		}else{
	    	insertSQL = "INSERT INTO alerta VALUES ("+ this.getAlertaID() +", "
	                + "'"+ this.getDescripcion() + "', "
	                +  this.getVictimaID() +", "
	                +  this.getAgresorID() + ");";
	    }    	
	principalActivity.db.execSQL(insertSQL);        
    }
    
    public void updateAlerta(){
        String updateSQL = "update alerta "
              + "set descripcion = '" + this.getDescripcion() + "', "
              + "victimaID = " + this.getVictimaID() + ", "
              + "agresorID = " + this.getAgresorID() + " "
              + "where alertaID = " + this.getAlertaID() + ";";
        
        principalActivity.db.execSQL(updateSQL);
    }
    
    public void deleteAlerta(){
        String deleteSQL = "delete from alerta " + "where alertaID = " + this.getAlertaID() + ";";
        
        principalActivity.db.execSQL(deleteSQL);
    }

	public List<eAlerta> getAlertas() {
		
		ArrayList<eAlerta> entidad = new ArrayList<eAlerta>();
		Cursor result = principalActivity.db.query("alerta",columnas, null, null, null, null, null);
			if (result.moveToFirst()){
			    do {
			    	entidad.add(new eAlerta(
					result.getInt(result.getColumnIndex("alertaID")),
					result.getString(result.getColumnIndex("descripcion")),
					result.getInt(result.getColumnIndex("victimaID")),
			    	result.getInt(result.getColumnIndex("agresorID")))
					);
					
			    }while(result.moveToNext());
			}
		
		return entidad;
	}
	
	public static void deleteById(int id){
		String queryDelete = "DELETE FROM alerta WHERE alertaID = "+id;
		 principalActivity.db.execSQL(queryDelete);
	}	
	
	public void saveOrUpdate(){
		String query = "SELECT * FROM alerta WHERE alertaID = "+this.getAlertaID();
		Cursor c = principalActivity.db.rawQuery(query, null);
		if(c.moveToFirst()){
			updateAlerta();
		}else{
			insertAlerta();
		}
	}
	
	public static eAlerta getalerta(int uealerta){   //  obtiene todos los datos de una alerta
		String query = "SELECT * FROM alerta WHERE alertaID = "+uealerta;
		Cursor c = principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){
				int idAux = c.getInt(0);
				String descripcionAux = c.getString(1);
				int victimaIDAux = c.getInt(2);
				int agresorIDAux = c.getInt(3);
				eAlerta dir = new eAlerta(idAux, descripcionAux, victimaIDAux, agresorIDAux);
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
	
	public static eAlerta[] listAlertas(){    // hace una lista de todos las alertas con datos 
		String query = "SELECT * FROM alerta";
		Cursor c = principalActivity.db.rawQuery(query, null);
		eAlerta[] alertas= null;
		try {
			if(c.moveToFirst()){
				alertas= new eAlerta[c.getCount()];
				int m = 0;
				do{
					int idc = c.getInt(0);
					
					eAlerta a = eAlerta.getalerta(idc);
					alertas[m] = a;
					m++;					
					
				}while(c.moveToNext());			
				return alertas;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
			
	}
	
	public static int countAlerta(){    // cuenta a todas las alertas
		String query = "SELECT * FROM alerta";
		Cursor c = principalActivity.db.rawQuery(query, null);
		int count = c.getCount();
		return count;
	}	
	
	public static  String[] ListNombresAlertas(){   // lista nombre de las alertas
		String query = "SELECT * FROM alerta";
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
	
	public static  String[] ListIDAlertas(){   // lista ID de los alertas
		String query = "SELECT * FROM alerta";
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
    
	public static  String[] ListNombresEstudiantes(){   // lista nombre de las abusivos
			String query = "SELECT e.nombreCompleto FROM alerta a, estudiante e where a.victimaID=e.victimaID";
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

	public static int getIdAlerta(String nombre) {  //  obtiene el id del nombre lanzado
		String query = "SELECT * FROM alerta";
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

	public int getAlertaID() {
		return alertaID;
	}

	public void setAlertaID(int alertaID) {
		this.alertaID = alertaID;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getVictimaID() {
		return victimaID;
	}

	public void setVictimaID(int victimaID) {
		this.victimaID = victimaID;
	}

	public int getAgresorID() {
		return agresorID;
	}

	public void setAgresorID(int agresorID) {
		this.agresorID = agresorID;
	}
	
}
