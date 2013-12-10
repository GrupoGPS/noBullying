package com.nomasbullying;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.nomasbullying.datonegocio.eAlerta;
import com.nomasbullying.datonegocio.ePrueba;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class cameraActivity extends Activity {

	//private String url="http://10.0.2.2/bully/Servicio/ServicioAlerta.php?wsdl";
	private String url="http://drsoft.hol.es/bully/Servicio/ServicioPrueba.php?wsdl";

	//private String soapAction="http://10.0.2.2/bully/Servicio/ServicioAlerta.php/";
	private String soapAction="http://drsoft.hol.es/bully/Servicio/ServicioPrueba.php/";
		
	//private String namespace="http://10.0.2.2";
	private String namespace="http://drsoft.hol.es";
		
	private SoapObject request;
	
	private String respuesta="";
		
	
	private SoapSerializationEnvelope envelope;
		
	private HttpTransportSE transport;
	
	private Toast toast;	
	
	private static int TAKE_PICTURE = 1;
	private static int SELECT_PICTURE = 2;
	
	public static eAlerta a;
	
	private String name = "";
	
	private String image; 
	
	Intent i;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camara);
        
        name = Environment.getExternalStorageDirectory() + "/test.jpg";

        Button btnAction = (Button)findViewById(R.id.btnPic);
        btnAction.setOnClickListener(new OnClickListener() {       		
       		@Override
       		public void onClick(View v) {
       			/**
       			 * Obtenemos los botones de imagen completa y de galer�a para revisar su estatus
       			 * m�s adelante
       			 */
       			RadioButton rbtnFull = (RadioButton)findViewById(R.id.radbtnFull);
       			RadioButton rbtnGallery = (RadioButton)findViewById(R.id.radbtnGall);
       			
       			/**
       			 * Para todos los casos es necesario un intent, si accesamos la c�mara con la acci�n
       			 * ACTION_IMAGE_CAPTURE, si accesamos la galer�a con la acci�n ACTION_PICK. 
       			 * En el caso de la vista previa (thumbnail) no se necesita m�s que el intent, 
       			 * el c�digo e iniciar la actividad. Por eso inicializamos las variables intent y
       			 * code con los valores necesarios para el caso del thumbnail, as� si ese es el
       			 * bot�n seleccionado no validamos nada en un if. 
       			 */
       			Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
       			int code = TAKE_PICTURE;
       			
       			/**
       			 * Si la opci�n seleccionada es fotograf�a completa, necesitamos un archivo donde
       			 * guardarla
       			 */
       			if (rbtnFull.isChecked()) {					
       				Uri output = Uri.fromFile(new File(name));
       		    	intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
       			/**
       			 * Si la opci�n seleccionada es ir a la galer�a, el intent es diferente y el c�digo
       			 * tambi�n, en la consecuencia de que est� chequeado el bot�n de la galer�a se hacen
       			 * esas asignaciones
       			 */       		    	
       			} else if (rbtnGallery.isChecked()){       				
       				intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
       				code = SELECT_PICTURE;
       			}
       			
       			/**
       			 * Luego, con todo preparado iniciamos la actividad correspondiente.
       			 */
       			startActivityForResult(intent, code);				
       		}
       	});
        Button btnContinuar = (Button)findViewById(R.id.btncontinuarID);
        btnContinuar.setOnClickListener(new OnClickListener() {       		
       		@Override
       		public void onClick(View v) {
       			
       			image = getImageBase64plantilla();
       			
       			insertarPrueba();
       			
       			ePrueba p = new ePrueba();
       			p.setImagen(image);
       			p.setAlertaID(a.getAlertaID());
       			
    			toast=Toast.makeText(getApplicationContext(), "Se inserto la prueba en la BD", Toast.LENGTH_LONG);
    			
    			toast.show(); 
    			
    			i=new Intent(cameraActivity.this,menuActivity.class);
    			startActivity(i);    		
       			
       		}
       	});        
        
    }
    
    void insertarPrueba(){
		request=new SoapObject(namespace, "insertarPrueba");
		
		request.addProperty("imagen", image);
		
		request.addProperty("alertaID", String.valueOf(a.getAlertaID()));
		
		envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.dotNet=false;
		
		envelope.bodyOut=request;
		
		transport=new HttpTransportSE(url);
		
		try
		{
			
			transport.call(soapAction+"insertarPrueba", envelope);
			
			toast=Toast.makeText(getApplicationContext(), "Se inserto la prueba", Toast.LENGTH_LONG);
			
			toast.show();
			
		} catch (Exception e) 
		{
			toast=Toast.makeText(getApplicationContext(), "No se pudo insertar el prueba", Toast.LENGTH_LONG);
			
			toast.show();
		} 
		
    }
    
    /**
     * Funci�n que se ejecuta cuando concluye el intent en el que se solicita una imagen
     * ya sea de la c�mara o de la galer�a
     */
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	/**
    	 * Se revisa si la imagen viene de la c�mara (TAKE_PICTURE) o de la galer�a (SELECT_PICTURE)
    	 */
    	if (requestCode == TAKE_PICTURE) {
    		/**
    		 * Si se reciben datos en el intent tenemos una vista previa (thumbnail)
    		 */
    		if (data != null) {
    			/**
    			 * En el caso de una vista previa, obtenemos el extra �data� del intent y 
    			 * lo mostramos en el ImageView
    			 */
    			if (data.hasExtra("data")) { 
    				ImageView iv = (ImageView)findViewById(R.id.imgView);
    				iv.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
    			}
    		/**
    		 * De lo contrario es una imagen completa
    		 */    			
    		} else {
    			/**
    			 * A partir del nombre del archivo ya definido lo buscamos y creamos el bitmap
    			 * para el ImageView
    			 */
    			ImageView iv = (ImageView)findViewById(R.id.imgView);
    			iv.setImageBitmap(BitmapFactory.decodeFile(name));
    			/**
    			 * Para guardar la imagen en la galer�a, utilizamos una conexi�n a un MediaScanner
    			 */
    			new MediaScannerConnectionClient() {
    				private MediaScannerConnection msc = null; {
    					msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
    				}
    				public void onMediaScannerConnected() { 
    					msc.scanFile(name, null);
    				}
    				public void onScanCompleted(String path, Uri uri) { 
    					msc.disconnect();
    				} 
    			};				
    		}
    	/**
    	 * Recibimos el URI de la imagen y construimos un Bitmap a partir de un stream de Bytes
    	 */
    	} else if (requestCode == SELECT_PICTURE){
    		Uri selectedImage = data.getData();
    		InputStream is;
    		try {
    			is = getContentResolver().openInputStream(selectedImage);
    	    	BufferedInputStream bis = new BufferedInputStream(is);
    	    	Bitmap bitmap = BitmapFactory.decodeStream(bis);            
    	    	ImageView iv = (ImageView)findViewById(R.id.imgView);
    	    	iv.setImageBitmap(bitmap);						
    		} catch (FileNotFoundException e) {}
    	}
    }
    
	public  String getImageBase64plantilla(){
        String encodedImage = null;
        
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),  R.id.imgView);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        	if (bmp != null){//Encuentra la imagen
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos); 
            byte[] b = baos.toByteArray();
            	try{
            		encodedImage = Base64.encodeToString(b,     Base64.DEFAULT);

            		return encodedImage;
            	}catch(Exception ex){
                	Log.d("BASE64", "Error codificacion en Base64");
            	}
        		}else{//NO encuentra la imagen
        			Log.d("BASE64", "bm is NULL");
        		}
        	return null;
    }    

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		 
	     if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

	    	 finish();
	         Intent i = new Intent(this, menuActivity.class );
	         startActivity(i);	    	 
	    	 
	     }
	     
	     return super.onKeyDown(keyCode, event);
	}	
    
}