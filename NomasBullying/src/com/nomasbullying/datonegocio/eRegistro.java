package com.nomasbullying.datonegocio;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.nomasbullying.principalActivity;

public class eRegistro {
	private int registroID;
	private String unidadeducativa;
	
	private static String[] columnas = new String[] {"registroID", "unidadeducativa"};

	public eRegistro() {
		this.registroID = -1;
		this.unidadeducativa = null;
	}
	
	public eRegistro(int registroID, String unidadeducativa) {
		super();
		this.registroID = registroID;
		this.unidadeducativa = unidadeducativa;
	}
    
    public void insertRegistro(){
	String insertSQL="";	
    	if (this.getregistroID()  == -1){
	    	insertSQL = "INSERT INTO registro VALUES (null, "
	                + "'"+ this.getUnidadeducativa() + ");";
		}else{
	    	insertSQL = "INSERT INTO registro VALUES ("+ this.getregistroID() +", "
	                + "'"+ this.getUnidadeducativa() + ");";
		}
    	
	principalActivity.db.execSQL(insertSQL);        
    }
    
    public void updateRegistro(){
        String updateSQL = "update registro "
              + "set unidadeducativa = '" + this.getUnidadeducativa() + "' "
              + "where registroID = " + this.getregistroID() + ";";
        
        principalActivity.db.execSQL(updateSQL);
    }
    
    public void deleteRegistro(){
        String deleteSQL = "delete from registro " + "where registroID = " + this.getregistroID() + ";";
        
        principalActivity.db.execSQL(deleteSQL);
    }

	public List<eRegistro> getRegistros() {
		
		ArrayList<eRegistro> entidad = new ArrayList<eRegistro>();
		Cursor result = principalActivity.db.query("registro",columnas, null, null, null, null, null);
			if (result.moveToFirst()){
			    do {
			    	entidad.add(new eRegistro(
					result.getInt(result.getColumnIndex("registroID")),
					result.getString(result.getColumnIndex("unidadeducativa")))
					);
					
			    }while(result.moveToNext());
			}
		
		return entidad;
	}
	
	public static void deleteById(int id){
		String queryDelete = "DELETE FROM registro WHERE registroID = "+id;
		 principalActivity.db.execSQL(queryDelete);
	}	
	
	public void saveOrUpdate(){
		String query = "SELECT * FROM registro WHERE registroID = "+this.getregistroID();
		Cursor c = principalActivity.db.rawQuery(query, null);
		if(c.moveToFirst()){
			updateRegistro();
		}else{
			insertRegistro();
		}
	}
	
	public static eRegistro getRegistro(int registroID){   //  obtiene todos los datos de un Informante
		String query = "SELECT * FROM registro WHERE registroID = "+registroID;
		Cursor c = principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){
				int idAux = c.getInt(0);
				String unidadeducativaAux = c.getString(1);
				eRegistro inf = new eRegistro(idAux, unidadeducativaAux);
				return inf;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
	}
	
	public static eRegistro[] listRegistros(){    // hace una lista de todos los directores con datos 
		String query = "SELECT * FROM registro";
		Cursor c = principalActivity.db.rawQuery(query, null);
		eRegistro[] informantes= null;
		try {
			if(c.moveToFirst()){
				informantes= new eRegistro[c.getCount()];
				int m = 0;
				do{
					int idc = c.getInt(0);
					
					eRegistro inf = eRegistro.getRegistro(idc);
					informantes[m] = inf;
					m++;					
					
				}while(c.moveToNext());			
				return informantes;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
			
	}
	
	public static int countRegistro(){    // cuenta a todos los registro
		String query = "SELECT * FROM registro";
		Cursor c = principalActivity.db.rawQuery(query, null);
		int count = c.getCount();
		return count;
	}	
	
	public static  String[] ListNombresRegistros(){   // lista nombre de los regsitro
		String query = "SELECT * FROM registro";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] informantes= null;
		try {
			if(c.moveToFirst()){
				informantes= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(1);
					informantes[m] = nombre;                 
					m++;					
				}while(c.moveToNext());			
				return informantes;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
	}
	
	public static  String[] ListIDRegistros(){   // lista ID de los registros
		String query = "SELECT * FROM registro";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] informantes= null;
		try {
			if(c.moveToFirst()){
				informantes= new String[c.getCount()];
				int m = 0;
				do{
					String id = String.valueOf(c.getInt(0));
					informantes[m] = id;
					m++;					
				}while(c.moveToNext());			
				return informantes;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
			
	}

	public static int getIdRegistro(String nombre) {  //  obtiene el id del nombre lanzado
		String query = "SELECT * FROM registro";
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

	public int getregistroID() {
		return registroID;
	}

	public void setregistroID(int registroID) {
		this.registroID = registroID;
	}

	public String getUnidadeducativa() {
		return unidadeducativa;
	}

	public void setUnidadeducativa(String unidadeducativa) {
		this.unidadeducativa = unidadeducativa;
	}
	
}
