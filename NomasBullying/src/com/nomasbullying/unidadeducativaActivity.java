package com.nomasbullying;

import com.nomasbullying.datonegocio.eUnidadEducativa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class unidadeducativaActivity extends Activity implements OnItemSelectedListener,OnItemClickListener{
	public static String title="BIENVENIDO...";
	private ListView listaunidadeducativa;
	private String[] ues;
	int unidadeducativaID=0;
		
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unidadeducativa);
		
		listaunidadeducativa = (ListView) findViewById(R.id.listView1);
		ues = eUnidadEducativa.ListNombresUnidadEducativas();
		listaunidadeducativa.setAdapter(new ArrayAdapter<String>(unidadeducativaActivity.this, android.R.layout.simple_list_item_1,unidadeducativaID));
		listaunidadeducativa.setOnItemClickListener(this);        
	}

	public void onNothingSelected(AdapterView<?> arg0) {

		
	} 

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    	alertarActivity.currentUE = eUnidadEducativa.getUnidadEducativa(arg2+1);
    	//victimaActivity.currentUE = eUnidadEducativa.getUnidadEducativa(arg2+1); 
    	//agresorActivity.currentUE = eUnidadEducativa.getUnidadEducativa(arg2+1);
        Intent intent=new Intent(unidadeducativaActivity.this, menuActivity.class);
        startActivity(intent);  
        finish();
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
