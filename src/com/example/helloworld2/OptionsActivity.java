package com.example.helloworld2;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OptionsActivity extends ActionBarActivity {
private static double longitude;
private static double latitude;
private static LocationListener locationListener;
private static Button button1;
private static Button button2;
private static String searchJob;
private static String searchGender;
private static EditText searchLongitude;
private static EditText searchLatitude;
private static TextView tex;
private static TextView tex2;
private static CheckBox check;
private static CheckBox check2;
private static CheckBox check3;
private static boolean manual;
private Intent intent;
private static Spinner spinner1;
private static Spinner spinner2;
private static String gendervalue;
private static List<Doctor> doctorList;
private static List<String> jobList;

AppLocationService appLocationService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 /*requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	            */				
		setContentView(R.layout.activity_options);
		searchLongitude=(EditText)findViewById(R.id.editText2);
		searchLatitude=(EditText)findViewById(R.id.editText3);
		searchLongitude.setVisibility(View.GONE);
		searchLatitude.setVisibility(View.GONE);
		spinner2 = (Spinner)findViewById(R.id.spinner2);

		check2=(CheckBox)findViewById(R.id.checkBox2);
		check3=(CheckBox)findViewById(R.id.checkBox3);
		check2.setChecked(true);
		check3.setChecked(true);
		if(check2.isChecked() && check3.isChecked())
		{
			searchGender="both";					
		}
		check2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(check2.isChecked() && check3.isChecked())
				{
					searchGender="both";					
				}
				else if(!(check3.isChecked()) && check2.isChecked())
				{
					searchGender="male";
				}
				else if(!(check2.isChecked()) && check3.isChecked())
				{
					searchGender="female";
				}
				else if(!(check2.isChecked()) && !check3.isChecked())
				{
					check2.setChecked(true);
					Toast.makeText(getApplicationContext(),
							"Both genders cannot be empty",
							Toast.LENGTH_LONG).show();
				}				
			}
		});
		check3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(check2.isChecked() && check3.isChecked())
				{
					searchGender="both";					
				}
				else if(!(check3.isChecked()) && check2.isChecked())
				{
					searchGender="male";
				}
				else if(!(check2.isChecked()) && check3.isChecked())
				{
					searchGender="female";
				}
				else if(!(check2.isChecked()) && !check3.isChecked())
				{
					check3.setChecked(true);
					Toast.makeText(getApplicationContext(),
							"Both genders cannot be empty",
							Toast.LENGTH_LONG).show();
				}				
			}
		});

		tex=(TextView)findViewById(R.id.textView3);
		tex2=(TextView)findViewById(R.id.textView4);		
		tex.setVisibility(View.GONE);
		tex2.setVisibility(View.GONE);
		appLocationService = new AppLocationService(OptionsActivity.this);
        DatabaseHandler db=new DatabaseHandler(this);
        db.getReadableDatabase();
        jobList=db.getJobList();
        doctorList = new ArrayList<Doctor>();	        
        doctorList=db.getAllContacts(longitude,latitude);
       db.close();

     
       
       // Loading spinner data from database
       ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_spinner_item, jobList);

       // Drop down layout style - list view with radio button
       dataAdapter
               .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // attaching data adapter to spinner
      spinner2.setAdapter(dataAdapter);
      spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
          public void onItemSelected(AdapterView parent, View view, int pos, long id) {
              searchJob = parent.getItemAtPosition(pos).toString();
          }
          public void onNothingSelected(AdapterView parent) {
              searchJob = null;
          }
      });
      //spinner2.setOnItemSelectedListener(listener);
      //spinner2.setOnItemClickListener(new OnItemSeletedListener(public void onItemSelected)) {	
       
        //spinner1 = (Spinner) findViewById(R.id.spinner1);            	
		//searchJob=(EditText)findViewById(R.id.editText1);
		
		tex=(TextView)findViewById(R.id.textView3);
		tex2=(TextView)findViewById(R.id.textView4);
		check=(CheckBox)findViewById(R.id.checkBox1);
		check.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(check.isChecked())
				{
					//System.out.println("hello");
					searchLongitude.setVisibility(View.VISIBLE);
					searchLatitude.setVisibility(View.VISIBLE);
					tex.setVisibility(View.VISIBLE);
					tex2.setVisibility(View.VISIBLE);
					manual=true;
					//check.setChecked(true);
				}
				else if(!(check.isChecked()))
				{
					//System.out.println("cat");
					searchLongitude.setVisibility(View.GONE);
					searchLatitude.setVisibility(View.GONE);
					tex.setVisibility(View.GONE);
					tex2.setVisibility(View.GONE);
					manual=false;					
				}
				
			}
		});
		
		button2=(Button)findViewById(R.id.button2);			
		button2.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent=new Intent(OptionsActivity.this,Listviewactivity.class);
				Button_performed();								
			}
		});
		button1=(Button)findViewById(R.id.button1);			
		button1.setOnClickListener(new View.OnClickListener() 		
		{						
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				searchJob = (String) spinner2.getSelectedItem();
				/*if(searchJob.equals("All"))
				{
					intent=new Intent(OptionsActivity.this,MainMapsActivity.class);
					startActivity(intent);
				}
				else*/				
					intent=new Intent(OptionsActivity.this,MapsActivity.class);
				Button_performed();								
			}
		});
	}

	
public void showSettingsAlert(String provider) {
	AlertDialog.Builder alertDialog = new AlertDialog.Builder(OptionsActivity.this);
	alertDialog.setTitle(provider + " SETTINGS");
	alertDialog.setMessage(provider + " is not enabled! Want to go to settings menu?");

	alertDialog.setPositiveButton("Settings",
	new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			OptionsActivity.this.startActivity(intent);
		}});

	alertDialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}});

	alertDialog.show();
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);				
		
	       return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			showSettings();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void showSettings() {
		Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
		OptionsActivity.this.startActivity(intent);			
	}

	public void Button_performed()
	{
		//searchGender=(String) spinner1.getSelectedItem();
		searchJob = (String) spinner2.getSelectedItem();
				
		boolean verify=true;
					
			//Intent intent2=new Intent(MainActivity.this,MapsActivity.class);
			if (manual==false)
			{
				Location gpsLocation = appLocationService.getLocation(LocationManager.NETWORK_PROVIDER);
				if (gpsLocation != null) 
				{
					latitude = gpsLocation.getLatitude();
					longitude = gpsLocation.getLongitude();
					/*Toast.makeText(getApplicationContext(),
					"Mobile Location (GPS): \nLatitude: " + jobList.get(0)
					+ "\nLongitude: " + jobList.get(1),
					Toast.LENGTH_LONG).show();*/
								
				}				
				else {
					Location gpsLoc = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
					if (gpsLoc != null) 
					{
						latitude = gpsLoc.getLatitude();
						longitude = gpsLoc.getLongitude();
						/*Toast.makeText(getApplicationContext(),
						"Mobile Location (GPS): \nLatitude: " + jobList.get(0)
						+ "\nLongitude: " + jobList.get(1),
						Toast.LENGTH_LONG).show();*/
										
					}	
					else
					{
						verify=false;			
					}
				}
			}
			else if (manual==true)
			{
				latitude=Double.valueOf(searchLatitude.getText().toString());
				longitude=Double.valueOf(searchLongitude.getText().toString());
				/*Toast.makeText(getApplicationContext(),
						"Mobile Location (GPS): \nLatitude: " + latitude
						+ "\nLongitude: " + longitude,
						Toast.LENGTH_LONG).show();
						*/	
			}
			if(verify==true)
			{											
				
				DatabaseHandler db=new DatabaseHandler(this);
				db.getReadableDatabase();
	        	        
	        //List<Doctor> doctorList = new ArrayList<Doctor>();	        
		        doctorList=db.getAllContacts(searchJob,searchGender,
		        		longitude, latitude);
		        if(doctorList!=null )
		        {	  	        
		        	Bundle bun=new Bundle();
		        	bun.putParcelableArrayList("mylist", (ArrayList<? extends Parcelable>) doctorList);
		        	intent.putExtras(bun);  
		        	
		        	intent.putExtra("longit", longitude);
		        	intent.putExtra("latit", latitude);
		        	
		        	this.startActivity(intent);
		        	//this.startActivity(intent2);	        		        			       
		        }
		        else if((doctorList==null) && (String.valueOf(searchJob).equals("All")))
		        {
		        	intent=new Intent(OptionsActivity.this,MainMapsActivity.class);
		        	this.startActivity(intent);
		        }
			    else
			    {
			       	Toast.makeText(this, "There is no doctor of this category ", Toast.LENGTH_LONG).show();			     
			    }
		        db.close();
			}
			else
				showSettingsAlert("GPS");		
	}
}
