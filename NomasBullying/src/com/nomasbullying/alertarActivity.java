package com.nomasbullying;


import java.io.ByteArrayOutputStream;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.nomasbullying.datonegocio.eAlerta;
import com.nomasbullying.datonegocio.eEstudiante;
import com.nomasbullying.datonegocio.eUnidadEducativa;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class alertarActivity extends Activity implements OnItemSelectedListener{
	public static String title="Alerta";
	
	//private String url="http://10.0.2.2/bully/Servicio/ServicioAlerta.php?wsdl";
	private String url="http://drsoft.hol.es/bully/Servicio/ServicioAlerta.php?wsdl";

	//private String soapAction="http://10.0.2.2/bully/Servicio/ServicioAlerta.php/";
	private String soapAction="http://drsoft.hol.es/bully/Servicio/ServicioAlerta.php/";
		
	//private String namespace="http://10.0.2.2";
	private String namespace="http://drsoft.hol.es";
		
	private SoapObject request;
	
	private String respuesta="";
	
	public static eUnidadEducativa currentUE;
	public static eEstudiante currentvictima;
	public static eEstudiante currentagresor;
	
	private SoapSerializationEnvelope envelope;
		
	private HttpTransportSE transport;
	
	private Toast toast;
	
	TextView descripcion;

	Intent intent;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alertar);
		setTitle(title);
		
		descripcion = (EditText)findViewById(R.id.txtdescripcionID);
		
        
	}
	

	
	
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		/*int position = listaue.getSelectedItemPosition();
		eue = eUnidadEducativa.getUE(eUnidadEducativa.getIdUE(ues[position].toString()));*/
	}
	
	private void clear() {
		descripcion.setText("");
	}

	public void insertarAlerta()
	{
		
		request=new SoapObject(namespace, "insertarAlerta");
		
		request.addProperty("descripcion",descripcion.getText().toString());
		
		request.addProperty("victimaID", String.valueOf(1));
		
		request.addProperty("agresorID", String.valueOf(2));
		
		envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.dotNet=false;
		
		envelope.bodyOut=request;
		
		transport=new HttpTransportSE(url);
		
		try
		{
			
			transport.call(soapAction+"insertarAlerta", envelope);
			
			toast=Toast.makeText(getApplicationContext(), "Se inserto la alerta", Toast.LENGTH_LONG);
			
			toast.show();
			
		} catch (Exception e) 
		{
			toast=Toast.makeText(getApplicationContext(), "No se pudo insertar el alerta", Toast.LENGTH_LONG);
			
			toast.show();
		} 
		
		
	}	
	
	public void enviaralerta(View v) {
		
		insertarAlerta();		// enviar a la web
		
		eAlerta aa = new eAlerta();
		/*Camera.a.setDescripcion(descripcion.getText().toString());
		Camera.a.setVictimaID(1);
		Camera.a.setAgresorID(2);
		Camera.a.insertAlerta();*/  
		aa.setDescripcion(descripcion.getText().toString());
		aa.setVictimaID(1);
		aa.setAgresorID(2);
		aa.insertAlerta();

        Toast.makeText(alertarActivity.this, 
                "Su Alerta se guardo con EXITO", 
                Toast.LENGTH_LONG).show();
		finish();
		Intent intent=new Intent(alertarActivity.this,menuActivity.class);
		startActivity(intent);
		
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {

		
	} 

}
