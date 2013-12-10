package com.nomasbullying.datonegocio;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.nomasbullying.principalActivity;

public class eCurso {
	private int cursoID;
	private String nivel;
	private int grado;
	private int unidadEducativaID; 
	
	private static String[] columnas = new String[] {"cursoID", "nivel", "turno", "unidadEducativaID"};

	public eCurso() {
		this.cursoID = -1;
		this.nivel = null;
		this.grado = -1;
		this.unidadEducativaID = -1;
	}
	
	public eCurso(int cursoID, String nivel, int grado, int unidadEducativaID) {
		super();
		this.cursoID = cursoID;
		this.nivel = nivel;
		this.grado = grado;
		this.unidadEducativaID = -1;
	}
    
    public void insertCurso(){
	String insertSQL="";	
    	if (this.getCursoID()  == -1){
	    	insertSQL = "INSERT INTO curso VALUES (null, "
	                + "'"+ this.getNivel() + "', "
	                +  this.getGrado() +", "
	                +  this.getUnidadEducativaID() + ");";
		}else{
	    	insertSQL = "INSERT INTO curso VALUES ("+ this.getCursoID() +", "
	                + "'"+ this.getNivel() + "', "
	                +  this.getGrado() +", "
	                +  this.getUnidadEducativaID() + ");";}
	principalActivity.db.execSQL(insertSQL);        
    }
    
    public void updateCurso(){
        String updateSQL = "update curso "
              + "set nivel = '" + this.getNivel() + "', "
              + "grado = " + this.getGrado() + ", "
              + "unidadEducativaID = " + this.getUnidadEducativaID() + " "
              + "where cursoID = " + this.getCursoID() + ";";
        
        principalActivity.db.execSQL(updateSQL);
    }
    
    public void deleteCurso(){
        String deleteSQL = "delete from curso " + "where cursoID = " + this.getCursoID() + ";";
        
        principalActivity.db.execSQL(deleteSQL);
    }

	public List<eCurso> getUEs() {
		
		ArrayList<eCurso> entidad = new ArrayList<eCurso>();
		Cursor result = principalActivity.db.query("curso",columnas, null, null, null, null, null);
			if (result.moveToFirst()){
			    do {
			    	entidad.add(new eCurso(
					result.getInt(result.getColumnIndex("cursoID")),
					result.getString(result.getColumnIndex("nivel")),
					result.getInt(result.getColumnIndex("grado")),
					result.getInt(result.getColumnIndex("unidadEducativaID")))
					);
					
			    }while(result.moveToNext());
			}
		
		return entidad;
	}
	
	public static void deleteById(int id){
		String queryDelete = "DELETE FROM curso WHERE cursoID = "+id;
		 principalActivity.db.execSQL(queryDelete);
	}	
	
	public void saveOrUpdate(){
		String query = "SELECT * FROM curso WHERE cursoID = "+this.getCursoID();
		Cursor c = principalActivity.db.rawQuery(query, null);
		if(c.moveToFirst()){
			updateCurso();
		}else{
			insertCurso();
		}
	}
	
	public static eCurso getCurso(int cursoID){   //  obtiene todos los datos de un curso
		String query = "SELECT * FROM curso WHERE cursoID = "+cursoID;
		Cursor c = principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){
				int idAux = c.getInt(0);
				String nivelAux = c.getString(1);
				int gradoAux = c.getInt(2);
				int unidadEducativaIDAux = c.getInt(3);
				eCurso cur = new eCurso(idAux, nivelAux, gradoAux, unidadEducativaIDAux);
				return cur;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
	}
	
	public static eCurso[] listCursos(){    // hace una lista de todos los cursos con datos 
		String query = "SELECT * FROM curso";
		Cursor c = principalActivity.db.rawQuery(query, null);
		eCurso[] cursos= null;
		try {
			if(c.moveToFirst()){
				cursos= new eCurso[c.getCount()];
				int m = 0;
				do{
					int idc = c.getInt(0);
					eCurso ue = eCurso.getCurso(idc);
					cursos[m] = ue;
					m++;					
					
				}while(c.moveToNext());			
				return cursos;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
			
	}
	
	public static int countCurso(){    
		String query = "SELECT * FROM curso";
		Cursor c = principalActivity.db.rawQuery(query, null);
		int count = c.getCount();
		return count;
	}	
	
	public static  String[] ListGradosCursos(){   // lista grado de los cursos
		String query = "SELECT * FROM curso";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] ues= null;
		try {
			if(c.moveToFirst()){
				ues= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(2);
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
	
	public static  String[] ListIDCursos(){   // lista ID de los cursos
		String query = "SELECT * FROM curso";
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

	public static int getIdCurso(String nombre) {  //  obtiene el id del nombre lanzado
		String query = "SELECT * FROM curso";
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

	public int getCursoID() {
		return cursoID;
	}

	public void setCursoID(int cursoID) {
		this.cursoID = cursoID;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	public int getUnidadEducativaID() {
		return unidadEducativaID;
	}

	public void setUnidadEducativaID(int unidadEducativaID) {
		this.unidadEducativaID = unidadEducativaID;
	}
	
}
