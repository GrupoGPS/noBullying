package com.nomasbullying;

import com.nomasbullying.datonegocio.eCurso;
import com.nomasbullying.datonegocio.eEstudiante;
import com.nomasbullying.datonegocio.eParalelo;
import com.nomasbullying.datonegocio.eUnidadEducativa;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class victimaActivity extends Activity  implements OnItemSelectedListener{

	public static eUnidadEducativa currentUE;

	private Spinner listacurso;
	
	private String[] cursos;
	
	private eCurso ecu;
	
	private Spinner listaparalelo;
	
	private String[] paralelos;
	
	private eParalelo epa;
	
	private Spinner listaestudiante;
	
	private String[] estudiantes;
	
	private eEstudiante ee;
	
	
	int ueID=0,cID=0,pID=0;	
	Button enviar,cargar;
		
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.victima);
		
		listacurso = (Spinner) findViewById(R.id.spncursovictimaID);
		cursos = eCurso.ListGradosCursos();
        if(cursos==null){
        	cursos=new String[]{"No existe ningun grado"};
        }    
        listacurso.setAdapter(new ArrayAdapter<String>(victimaActivity.this,android.R.layout.simple_spinner_item,cursos));
        listacurso.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				arg2=listacurso.getSelectedItemPosition();
				ecu=eCurso.getCurso(eCurso.getIdCurso(cursos[arg2+1].toString()));
				//ecu = eCurso.getCurso(arg2+1);
				//paralelos = ListParalelos();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		}); 

        
        listaparalelo = (Spinner) findViewById(R.id.spnparalelovictimaID);
		paralelos = eParalelo.ListNombresParalelos();
        if(paralelos==null){
        	paralelos=new String[]{"No existe ningun paralelo"};
        }    
        listaparalelo.setAdapter(new ArrayAdapter<String>(victimaActivity.this,android.R.layout.simple_spinner_item,paralelos));
        listaparalelo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				position=listaparalelo.getSelectedItemPosition();
				//epa=eParalelo.getParalelo(eParalelo.getIdParalelo(paralelos[position+1].toString()));
				epa = eParalelo.getParalelo(position+1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
        	
		});

        
        enviar  = (Button) findViewById(R.id.btnenviarvictimaID);
        enviar.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View arg0) {
    			alertarActivity.currentvictima = eEstudiante.getEstudiante(ee.getEstudianteID());
    			Intent intent=new Intent(victimaActivity.this,agresorActivity.class); 
    			startActivity(intent);
    			finish();
    		}
    	});
//////ACA en ves de capturas listanombres tiene q acerlo con el curso y paralelo RESPECTIVO//////////////////////////////////////////////////////

        cargar  = (Button) findViewById(R.id.btncargarvictimaID);
        cargar.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View arg0) {
				listaestudiante = (Spinner) findViewById(R.id.spnvictimaID);
				estudiantes = ListNombresEstudiantes();
		        listaestudiante.setAdapter(new ArrayAdapter<String>(victimaActivity.this,android.R.layout.simple_spinner_item,estudiantes));
		        //listaestudiante.setOnClickListener(this);
    		}
    	});        
        
        
	}
	
	public  String[] ListNombresEstudiantes(){   // lista nombre de los Estudiantes
		String query = "SELECT e.* FROM estudiante e,curso c, paralelo p, unidadeducativa ue " +
				       "where e.paraleloID = p.paraleloID and p.cursoID = c.cursoID and ue.unidadEducativaID = c.unidadEducativaID  and " +
				       "ue.unidadEducativaID = "+ currentUE.getUnidadEducativaID()  +" and c.cursoID = "+ ecu.getCursoID() +" and p.paraleloID = "+ epa.getParaleloID() +"";
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
	
	public  String[] ListParalelos(){   // lista nombre de los Estudiantes
		String query = "select p.* from paralelo p, curso c, unidadEducativa ue " +
				       "where p.cursoID = c.cursoID and ue.unidadEducativaID = c.unidadEducativaID and " +
				       "ue.unidadEducativaID = "+ currentUE.getUnidadEducativaID() +" and p.cursoID = "+ 3 +" ";
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

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
