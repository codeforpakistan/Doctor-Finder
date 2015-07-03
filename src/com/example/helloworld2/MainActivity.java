package com.example.helloworld2;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {


AppLocationService appLocationService;
	
	private static ProgressDialog progress;
	private static DatabaseHandler db;
	private static ProgressBar prog;
	private static int progstatus = 0;
	private static int temp=0; 
	private static Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//getActionBar().setTitle("NAME OF APP");   
		//getSupportActionBar().setTitle("NAME OF APP"); 
		//this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		
		/* requestWindowFeature(Window.FEATURE_NO_TITLE);
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	           WindowManager.LayoutParams.FLAG_FULLSCREEN);
	           */	
		setContentView(R.layout.activity_main);
		appLocationService = new AppLocationService(MainActivity.this);
		db=new DatabaseHandler(this);
	
		//MainActivity.this.set
		prog= (ProgressBar)findViewById(R.id.progressBar1);
		prog.setKeepScreenOn(true);
		
		 new Thread(new Runnable() {
             public void run() {
            	 
                 while (progstatus < 100) {
                	 progstatus = doWork();                	
                     mHandler.post(new Runnable() {
                         public void run() {                        	 
                             prog.setProgress(progstatus);                             
                         }
                     });
                 }
                 progstatus=0;
                 Intent intent =new Intent(MainActivity.this,MainMapsActivity.class);
                 MainActivity.this.startActivity(intent);
             }
         }).start();       			
	}
	public int doWork()
	{
		db.getWritableDatabase();
        db.deleteTable(db.getWritableDatabase());                
        //33.711778, 73.057217  current location
        db.addContact("Ali", "Dentist", "male", 33.676477, 73.066024, "0344 553 2806","Shifa International","Specialist");  //Shifa
        db.addContact("Omer", "Dentist", "male", 33.702456, 73.053946, "0344 553 2806","PIMS","Specialist");  //PIMS
        db.addContact("Rabia", "Dentist", "female", 33.652332, 73.015801, "0344 553 2806","PAEC","Specialist");  //PAEC 
        db.addContact("Alia", "Cardiologist", "female", 33.649471, 73.017311, "0344 553 2806","NESCOM","Specialist"); //NESCOM
        db.addContact("Wamzay", "Orthopedics", "female", 33.580738, 73.047892, "0344 553 2806","CMH Rawalpindi","Specialist"); //CMH
        db.addContact("Usman", "Dentist", "male", 33.711385, 73.041675, "0344 553 2806","Islamabad Diagnostic","Specialist");  
        db.addContact("Nauman", "Eye Specialist", "male", 33.640906, 73.057455, "0344 553 2806","Holy Family Rwp","Specialist");  //Holy Family
        db.addContact("Irfan", "Eye Specialist", "male", 33.678234, 73.031512, "0344 553 2806","KRL Hospital","Specialist");  
        db.addContact("Bilal", "Eye Specialist", "male", 33.710528, 73.042057, "0344 553 2806","Ali Medical","Specialist");  
        db.addContact("Fatima", "Orthopedics", "female", 33.554166, 73.095568, "0344 553 2806","Fauji Foundation","Specialist");
        db.addContact("Zeeshan", "NeuroSurgeon", "male", 34.003330, 71.542214, "0344 553 2806","CMH Peshawar","Specialist"); //CMH Peshawar  
        db.close();
        temp+=5;
        return temp;
	}
}
