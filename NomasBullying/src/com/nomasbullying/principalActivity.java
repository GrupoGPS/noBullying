package com.nomasbullying;

import java.io.IOException;

import com.nomasbullying.datonegocio.conexion;
import com.nomasbullying.datonegocio.eUnidadEducativa;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;

public class principalActivity extends Activity {
	public static String title="BIENVENIDO...";	
	private MediaPlayer song;
	public static conexion cnx;
	public static SQLiteDatabase db;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        cnx = new conexion(this);
        try {
			cnx.createDataBase();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        db = cnx.getWritableDatabase();
               
        song= MediaPlayer.create(principalActivity.this, R.raw.fivestars);
        song.start();
        Thread timer= new Thread(){
        	@Override
        	public void run() {
        		try{
        			sleep(5000);
        		}catch(InterruptedException e){
        			e.printStackTrace();
        		}finally{
        			Intent intent=new Intent(principalActivity.this,unidadeducativaActivity.class); 
        			startActivity(intent);
        		}
        	}
        };
        timer.start();        
    }

    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	song.release();
    	finish();
    }
  
   public static void setCon(conexion con) {
	principalActivity.cnx = con;
   } 
    
    public static conexion getCon() {
		return cnx;
	}    
}