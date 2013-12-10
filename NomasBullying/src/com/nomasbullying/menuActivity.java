package com.nomasbullying;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class menuActivity extends Activity {
	
	public static String title="NO + BuLLYING...";
	
	Button salir, alertar, conocer;
	ImageButton configuracion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        alertar = (Button) findViewById(R.id.btnalertarID);
        alertar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
    			Intent intent=new Intent(menuActivity.this,alertarActivity.class); 
    			startActivity(intent);
    			finish();
			}
		});
        
        configuracion = (ImageButton) findViewById(R.id.imageButton1);
        configuracion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
    			Intent intent=new Intent(menuActivity.this,unidadeducativaActivity.class); 
    			startActivity(intent);
    			finish();
			}
		});        
        
        conocer = (Button) findViewById(R.id.btnconocerID);
        conocer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
    			Intent intent=new Intent(menuActivity.this,contacteseActivity.class); 
    			startActivity(intent);
    			finish();
			}
		});
        
    	salir = (Button) findViewById(R.id.btnsalirID);
    	salir.setOnClickListener(new OnClickListener() {
    		
    		public void onClick(View arg0) {
    			AlertDialog.Builder dialog = new AlertDialog.Builder(menuActivity.this);
    			 
    			dialog.setMessage("�Desea Salir de NomasBullying?");
    			dialog.setCancelable(false);
    			dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
    			 
    			  public void onClick(DialogInterface dialog, int which) {
    				  principalActivity.db.close();
    				  menuActivity.this.finish();
    				 // android.os.Process.killProcess(android.os.Process.myPid());
    			  }
    			});
    			dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
    			 
    			   public void onClick(DialogInterface dialog, int which) {
    			      dialog.cancel();
    			   }
    			});
    			dialog.show();
    		}
    	});        
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		 
	     if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	         return true;
	     }
	     
	     return super.onKeyDown(keyCode, event);
	}

}
