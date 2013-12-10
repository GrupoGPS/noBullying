package com.nomasbullying;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

public class contacteseActivity extends Activity {

	private WebView webView1;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.contactese);
        
        webView1=(WebView)findViewById(R.id.webID);
        
        bundle=getIntent().getExtras();
        webView1.loadUrl("http://www.drsoft.hol.es");		
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
