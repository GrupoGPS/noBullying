package com.nomasbullying;

import com.nomasbullying.datonegocio.eCurso;
import com.nomasbullying.datonegocio.eEstudiante;
import com.nomasbullying.datonegocio.eParalelo;
import com.nomasbullying.datonegocio.eUnidadEducativa;

import android.app.Activity;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class agresorActivity extends Activity implements OnItemSelectedListener {
	public static eUnidadEducativa currentUE;
	private Spinner spcursos, spparalelos, spestudiantes;
	private eCurso ecu; eParalelo epa; eEstudiante ees;
	
	Button cargar, enviar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agresor);

		this.cargar = (Button) findViewById(R.id.btncargaragresorID);
		this.enviar = (Button) findViewById(R.id.btnenviaragresorID);
		
		this.spcursos = (Spinner) findViewById(R.id.spncursoagresorID);
		this.spparalelos= (Spinner) findViewById(R.id.spnparaleloagresorID);
		this.spestudiantes = (Spinner) findViewById(R.id.spnagresorID);

		loadSpinnerProvincias();

	}

	/**
	 * Populate the Spinner.
	 */
	private void loadSpinnerProvincias() {

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		spcursos.setAdapter(new ArrayAdapter<String>(agresorActivity.this,android.R.layout.simple_spinner_item,ListGradosEstudiantes()));
		/*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.provincias, android.R.layout.simple_spinner_item);*/
		// Specify the layout to use when the list of choices appears
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		//this.spcursos.setAdapter(adapter);

		// This activity implements the AdapterView.OnItemSelectedListener
		this.spcursos.setOnItemSelectedListener(this);
		this.spparalelos.setOnItemSelectedListener(this);
		this.spestudiantes.setOnItemSelectedListener(this);

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		switch (parent.getId()) {
		case R.id.spncursoagresorID:

			
			/*spparalelos.setAdapter(new ArrayAdapter<String>(agresorActivity.this,android.R.layout.simple_spinner_item,ListParalelos()));
			TypedArray arrayLocalidades = getResources().obtainTypedArray(
					R.array.array_provincia_a_localidades);
			CharSequence[] localidades = arrayLocalidades.getTextArray(pos);
			arrayLocalidades.recycle();

			ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
					this, android.R.layout.simple_spinner_item,
					android.R.id.text1, localidades);

			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			this.spcursos.setAdapter(adapter);*/

			spparalelos.setAdapter(new ArrayAdapter<String>(agresorActivity.this,android.R.layout.simple_spinner_item,ListParalelos()));
			
			break;

		case R.id.spnparaleloagresorID:
			
			spestudiantes.setAdapter(new ArrayAdapter<String>(agresorActivity.this,android.R.layout.simple_spinner_item,ListNombresEstudiantes()));

			break;
			
		case R.id.spnagresorID:
			  	
			
				
			break;			
		}
	}

	public  String[] ListGradosEstudiantes(){   // lista grados de los Estudiantes
		String query = "SELECT c.* FROM curso c, unidadeducativa ue " +
				       "where ue.unidadEducativaID = c.unidadEducativaID  and " +
				       "ue.unidadEducativaID = "+ currentUE.getUnidadEducativaID()  +" ";
		Cursor c = principalActivity.db.rawQuery(query, null);
		String[] estudiantes= null;
		try {
			if(c.moveToFirst()){
				estudiantes= new String[c.getCount()];
				int m = 0;
				do{
					String nombre = c.getString(2);
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
				       "ue.unidadEducativaID = "+ currentUE.getUnidadEducativaID() +" and p.cursoID = "+ ecu.getCursoID() +" ";
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
	public void onNothingSelected(AdapterView<?> parent) {
		// Callback method to be invoked when the selection disappears from this
		// view. The selection can disappear for instance when touch is
		// activated or when the adapter becomes empty.
	}

	/**
	 * Shows the selected strings from spinners.
	 * 
	 * @param v
	 *            The view that was clicked.
	 */
	public void showLocalidadSelected(View v) {
		/*Toast.makeText(
				getApplicationContext(),
				getString(R.string.message, spLocalidades.getSelectedItem()
						.toString(), spProvincias.getSelectedItem().toString()),
				Toast.LENGTH_LONG).show();*/
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}