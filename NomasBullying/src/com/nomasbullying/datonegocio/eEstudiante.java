package com.nomasbullying.datonegocio;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.nomasbullying.principalActivity;

public class eEstudiante {
	private int estudianteID;
	private String nombreCompleto;
	private String correo;
	private int telefono;
	private String fechaNacimiento;
	private int paraleloID;    
	
	private static String[] columnas = new String[] {"estudianteID", "nombreCompleto", "correo", "telefono", "fechaNacimiento"};

	public eEstudiante() {
		this.estudianteID = -1;
		this.nombreCompleto = null;
		this.correo = null;
		this.telefono = -1;
		this.fechaNacimiento = null;
		this.paraleloID = -1;
	}
	
	public eEstudiante(int directorID, String nombreCompleto,String correo, int telefono, String fechaNacimiento, int paraleloID) {
		super();
		this.estudianteID = directorID;
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.paraleloID = paraleloID;
	}
    
    public void insertEstudiante(){
	String insertSQL="";	
    	if (this.getEstudianteID()  == -1){
	    	insertSQL = "INSERT INTO estudiante VALUES (null, "
	                + "'"+ this.getNombreCompleto() + "', "
	                + "'"+ this.getCorreo() +"', "
	                +  this.getTelefono() +", "
	                + "'"+ this.getFechaNacimiento() +"', "
	                +  this.getParaleloID() + ");";
		}else{
	    	insertSQL = "INSERT INTO estudiante VALUES ("+ this.getEstudianteID() +", "
	                + "'"+ this.getNombreCompleto() + "', "
	                + "'"+ this.getCorreo() +"', "
	                +  this.getTelefono() +", "
	                + "'"+ this.getFechaNacimiento() +"', "
	                +  this.getParaleloID() + ");";
	    	}
    	
	principalActivity.db.execSQL(insertSQL);        
    }
    
    public void updateEstudiante(){
        String updateSQL = "update estudiante "
              + "set nombreCompleto = '" + this.getNombreCompleto() + "', "
              + "correo = '" + this.getCorreo() + "', "
              + "telefono = " + this.getTelefono() + ", "
              + "fechaNacimiento = '" + this.getFechaNacimiento() + "', "
              + "paraleloID = " + this.getParaleloID() + " "
              + "where estudianteID = " + this.getEstudianteID() + ";";
        
        principalActivity.db.execSQL(updateSQL);
    }
    
    public void deleteEstudiante(){
        String deleteSQL = "delete from estudiante where estudianteID = " + this.getEstudianteID() + ";";
        
        principalActivity.db.execSQL(deleteSQL);
    }

	public List<eEstudiante> getEstudiantes() {
		
		ArrayList<eEstudiante> entidad = new ArrayList<eEstudiante>();
		Cursor result = principalActivity.db.query("estudiante",columnas, null, null, null, null, null);
			if (result.moveToFirst()){
			    do {
			    	entidad.add(new eEstudiante(
					result.getInt(result.getColumnIndex("estudianteID")),
					result.getString(result.getColumnIndex("nombreCompleto")),
					result.getString(result.getColumnIndex("correo")),
					result.getInt(result.getColumnIndex("telefono")),					
					result.getString(result.getColumnIndex("fechaNacimiento")),
					result.getInt(result.getColumnIndex("paraleloID")))
					);
					
			    }while(result.moveToNext());
			}
		
		return entidad;
	}
	
	public static void deleteById(int id){
		String queryDelete = "DELETE FROM estudiante WHERE estudianteID = "+id;
		 principalActivity.db.execSQL(queryDelete);
	}	
	
	public void saveOrUpdate(){
		String query = "SELECT * FROM estudiante WHERE estudianteID = "+this.getEstudianteID();
		Cursor c = principalActivity.db.rawQuery(query, null);
		if(c.moveToFirst()){
			updateEstudiante();
		}else{
			insertEstudiante();
		}
	}
	
	public static eEstudiante getEstudiante(int estudianteID){   //  obtiene todos los datos de un Estudiante
		String query = "SELECT * FROM estudiante WHERE estudianteID = "+estudianteID;
		Cursor c = principalActivity.db.rawQuery(query, null);
		try {
			if(c.moveToFirst()){
				int idAux = c.getInt(0);
				String nombreAux = c.getString(1);
				String correoAux = c.getString(2);
				int phoneAux = c.getInt(3);
				String fechaNacimientoAux = c.getString(4);
				int paraleloIDAux = c.getInt(5);
				eEstudiante dir = new eEstudiante(idAux, nombreAux, correoAux, phoneAux, fechaNacimientoAux, paraleloIDAux);
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
	
	public static eEstudiante[] listEstudiantes(){    // hace una lista de todos los Estudiantes con datos 
		String query = "SELECT * FROM estudiante";
		Cursor c = principalActivity.db.rawQuery(query, null);
		eEstudiante[] estudiantes= null;
		try {
			if(c.moveToFirst()){
				estudiantes= new eEstudiante[c.getCount()];
				int m = 0;
				do{
					int idc = c.getInt(0);
					
					eEstudiante est = eEstudiante.getEstudiante(idc);
					estudiantes[m] = est;
					m++;					
					
				}while(c.moveToNext());			
				return estudiantes;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally{
			c.close();
		}
			
	}
	
	public static int countEstudiante(){    // cuenta a todos los clientes
		String query = "SELECT * FROM estudiante";
		Cursor c = principalActivity.db.rawQuery(query, null);
		int count = c.getCount();
		return count;
	}	
	
	public static  String[] ListNombresEstudiantes(){   // lista nombre de los Estudiantes
		String query = "SELECT * FROM estudiante";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] estudiantes= null;
		try {
			if(c.moveToFirst()){
				estudiantes= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(1);
					estudiantes[m] = nombre;                 
					m++;					
				}while(c.moveToNext());			
				return estudiantes;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
	}
	
	public static  String[] ListIDEstudiantes(){   // lista ID de los estudiantes
		String query = "SELECT * FROM estudiante";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] estudiantes= null;
		try {
			if(c.moveToFirst()){
				estudiantes= new String[c.getCount()];
				int m = 0;
				do{
					String id = String.valueOf(c.getInt(0));
					estudiantes[m] = id;
					m++;					
				}while(c.moveToNext());			
				return estudiantes;
			}else{
				return null;
			}
			
		} catch (Exception e) {
				return null;
		}finally{
			c.close();
		}
			
	}

	public static int getIdEstudiante(String nombre) {  //  obtiene el id del nombre lanzado
		String query = "SELECT * FROM estudiante";
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

	public int getEstudianteID() {
		return estudianteID;
	}

	public void setEstudianteID(int estudianteID) {
		this.estudianteID = estudianteID;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getParaleloID() {
		return paraleloID;
	}

	public void setParaleloID(int paraleloID) {
		this.paraleloID = paraleloID;
	}
	
}
