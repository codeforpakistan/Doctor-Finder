package com.example.helloworld2;

import java.util.ArrayList;
import java.util.List;

import com.example.helloworld2.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.ViewGroup;

public class MainMapsActivity extends ActionBarActivity {
	private static double longitude;
	private static double latitude;
	private float distance;
	private static int buttonCount=0;
	private static CheckBox check;
	private static boolean manual;
	public static List<String> jobList = new ArrayList<String>();
	private static List<BitmapDescriptor>bitList=new ArrayList<BitmapDescriptor>();
	private static List<Marker> mark=new ArrayList<Marker>();
	AppLocationService appLocationService;
	private LatLngBounds.Builder bounds;
	private LegendrowAdaptor listadapter;
	private ToggleButton toggle;
	//private Doctor doctor;
	List<Doctor> doctorList = new ArrayList<Doctor>();
	List LatLngList =new ArrayList<LatLng>();
      //private Intent intent=new Intent(MainMapsActivity.this,MainActivity.class); 
   private GoogleMap googleMap;
   private GoogleMap google;
   private static ListView legend;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main_maps);
/*      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN);
  */  
      DatabaseHandler db=new DatabaseHandler(this);
	  db.getReadableDatabase();	  
	  distance=5;
      doctorList=db.getAllContacts(longitude,latitude);
      db.close();
      if(doctorList!=null)
      {
    	  for(int i=0;i<doctorList.size();i++)
	      {
    		  if(!jobList.contains(doctorList.get(i).getjob()))
          		 jobList.add(doctorList.get(i).getjob());
    		BitmapDescriptor bit;
    		float color= (jobList.indexOf(doctorList.get(i).getjob())+1)*30;
           	bit=BitmapDescriptorFactory.defaultMarker(color);             	
           	bitList.add(bit);
	      }
      }
      //showLegend(jobList,bitList);
      appLocationService = new AppLocationService(MainMapsActivity.this);
      bounds = new LatLngBounds.Builder();            
      try {
         if (googleMap == null) {
        	 googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();        	 
         }
         legend = (ListView) findViewById(R.id.listView1);
         googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
         check=(CheckBox) findViewById(R.id.checkBox1);
         //toggle=(ToggleButton) findViewById(R.id.toggleButton1);
         //check = (CheckBox) getActionBar().getCustomView().findViewById(R.id.legendcheckbox);
         check.setChecked(true);
         if(check.isChecked())
         {
       	    legend.setVisibility(View.VISIBLE);
         }
         else
         {
        	 legend.setVisibility(View.GONE);
         }
         check.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				if(check.isChecked())
 		         {
 		       	  legend.setVisibility(View.VISIBLE);
 		         }
 		         else
 		         {
 		        	 legend.setVisibility(View.GONE);
 		        	 //check.
 		         }				
 			}
 		});
         //if(toggle.)
         //googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);        
        /*
         * // googleMap.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(
        	//	  document.getElementById(R.id.legend));
         var legend = findViewById(R.id.legend);
         for (var style in styles) {
           var name = style.name;
           var icon = style.icon;
           var div = document.createElement('div');
           div.innerHTML = '<img src="' + icon + '"> ' + name;
           legend.appendChild(div);
         }*/
         
			//List<String>jobList2 = new ArrayList<String>();
			//jobList2=db.getJobList();
         //final Item[] items=new Item[jobList.size()];
         final ArrayList<Item> items=new ArrayList<Item>();
         float color=1;
	      for(int i=0;i<jobList.size();i++)
	      {
	    	  color=(i+1)*30;
	    	  BitmapDescriptor bit = BitmapDescriptorFactory.defaultMarker(color);
	    	  items.add(new Item(jobList.get(i), (new MarkerOptions().icon(bit)).getIcon()));
	    	  //listadapter.add(items[i]);	    	  
	      }
	      listadapter=new LegendrowAdaptor(this,R.layout.legendrow,items);
	      legend.setAdapter(listadapter);
         boolean verify=true; 		
         Location gpsLocation = appLocationService.getLocation(LocationManager.NETWORK_PROVIDER);
			if (gpsLocation != null) 
			{
				latitude = gpsLocation.getLatitude();
				longitude = gpsLocation.getLongitude();
				Toast.makeText(getApplicationContext(),
				"Mobile Location (GPS): \nLatitude: " + latitude 
				+ "\nLongitude: " + longitude ,
				Toast.LENGTH_LONG).show();				
			}				
			else {
				Location gpsLoc = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
				if (gpsLoc != null) 
				{
					latitude = gpsLoc.getLatitude();
					longitude = gpsLoc.getLongitude();
					Toast.makeText(getApplicationContext(),
					"Mobile Location (GPS): \nLatitude: " + latitude
					+ "\nLongitude: " + longitude,
					Toast.LENGTH_LONG).show();				
				}	
				else
				{
					verify=false;			
				}
			}
				if(verify==false)
					showSettingsAlert("GPS");                  
         if(doctorList!=null)
         {	  	                     
     		UiSettings sets=googleMap.getUiSettings();
     		sets.setCompassEnabled(true);
     		sets.setMyLocationButtonEnabled(true);
     		sets.setZoomControlsEnabled(true);
     		
     		googleMap.setMyLocationEnabled(true);
              List LatLng =new ArrayList<LatLng>();
              LatLng myLoc=new LatLng(latitude,longitude);         
              /*Marker currLoc = googleMap.addMarker(new MarkerOptions().
         	         position(myLoc).title("My Location").
         	         icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
         	  */
         	 
              CameraUpdate center=
             	        CameraUpdateFactory.newLatLng(new LatLng(latitude,
             	                                                 longitude));
             	    CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);

             	    googleMap.moveCamera(center);
             	    googleMap.animateCamera(zoom);
              bounds.include(new LatLng(latitude, longitude));
              for(int i=0;i<doctorList.size();i++)
              {
             	 LatLng lat=new LatLng(doctorList.get(i).getlongit(),doctorList.get(i).getlatit());
             	 LatLngList.add(lat);        	 
              }       
              float alpha=(float) 1.0;
              String asset="";
              
              List<Doctor> doctorList = new ArrayList<Doctor>();
              db=new DatabaseHandler(this);
        	  db.getReadableDatabase();
        	  distance=5;
              doctorList=db.getAllContacts(longitude,latitude);
              db.close();
              for(int i=0;i<doctorList.size();i++)
     	      {
             	 if((doctorList.get(i).getDistance()/1000)<distance)
             	 {
             		 alpha=(float) 1.0;
             	 }
             	 else
             		 alpha=(float) 0.5;
             	 BitmapDescriptor bit;             	             	
             	 color= (jobList.indexOf(doctorList.get(i).getjob())+1)*30;
             	if(doctorList.get(i).getjob().equals("Dentist"))
             		bit=BitmapDescriptorFactory.fromResource(R.drawable.dentist);
                else if(doctorList.get(i).getjob().equals("Cardiologist"))
                	bit=BitmapDescriptorFactory.fromResource(R.drawable.cardiologist);
                else if(doctorList.get(i).getjob().equals("Orthopedics"))
                	bit=BitmapDescriptorFactory.fromResource(R.drawable.orthopedics);
                else if(doctorList.get(i).getjob().equals("Eye Specialist"))
                	bit=BitmapDescriptorFactory.fromResource(R.drawable.eye_spec);                
                else if(doctorList.get(i).getjob().equals("NeuroSurgeon"))
                	bit=BitmapDescriptorFactory.fromResource(R.drawable.neurosurgeon);                
                else
                	bit=BitmapDescriptorFactory.fromResource(R.drawable.other);             	    
             	//bitList.add(bit);
             	
             	 /*if(doctorList.get(i).getjob().equals("Dentist"))
             	 {
             		 bit=BitmapDescriptorFactory.defaultMarker(color);        		 
             	 }
             	 else if(doctorList.get(i).getjob().equals("NeuroSurgeon"))
             		 bit=BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
             	 else if(doctorList.get(i).getjob().equals("Cardiologist"))
             		 bit=BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
             	 else 
             		 bit=BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
             	 */
             	 //"Distance: "+      		 String.format("%.2f",(doctorList.get(i).getDistance()/1000))
             	 String snippet="Phone: "+ doctorList.get(i).getphone()+" \nGender: "+
         		 doctorList.get(i).getgender();
             	 LatLng point=(com.google.android.gms.maps.model.LatLng) LatLngList.get(i);
             	 
             	 Marker TP = googleMap.addMarker(new MarkerOptions().
             	         position(point).title(doctorList.get(i).getname()+" -- "+
             	        		 doctorList.get(i).getjob()+" -- "+
             	        		 doctorList.get(i).gethospital()).snippet(snippet).alpha(alpha)
             	        		 .icon(bit) );
             	        		 //.icon(BitmapDescriptorFactory.fromResource(R.drawable.dentist)  ));
             	 bounds.include(new LatLng(doctorList.get(i).getlatit(), doctorList.get(i).getlongit()));
     	      }	        		      
              googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50));                           
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
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
			openSettings();
			return true;
		}
		if (id == R.id.legendcheckbox) {				
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void openSettings()
	{
		Intent intent = new Intent(MainMapsActivity.this,OptionsActivity.class);
		MainMapsActivity.this.startActivity(intent);
		
	}
	public void showLegend(List<String> jobs, List<BitmapDescriptor> bits) {
		String temp="";
		float color=(float) 1.0;
		final Item[] items=new Item[jobs.size()];
		
		for(int i=0;i<jobs.size();i++)
		{		
			color=(i+1)*30;
			temp+=jobs.get(i);
			//BitmapDescriptor bit;
			//bit=BitmapDescriptorFactory.fromResource(R.drawable.dentist);
			
			temp+="   "+color;
			temp+="\n";
		}
		for(int i=0;i<jobs.size();i++)
		{			 
			BitmapDescriptor bit = BitmapDescriptorFactory.defaultMarker(color); 
			//items[i]= new Item(jobs.get(i), R.drawable.dentist );				    				    				
		}
		ListAdapter adapter = new ArrayAdapter<Item>(
			    this,
			    android.R.layout.select_dialog_item,
			    android.R.id.text1,
			    items){
			        public View getView(int position, View convertView, ViewGroup parent) {
			            //User super class to create the View
			            View v = super.getView(position, convertView, parent);
			            TextView tv = (TextView)v.findViewById(android.R.id.text1);

			            //Put the image on the TextView
			            //tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);

			            //Add margin between image and text (support various screen densities)
			            int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
			            tv.setCompoundDrawablePadding(dp5);

			            return v;
			        }
			    };

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainMapsActivity.this);
		alertDialog.setTitle("Legend");
		
	    alertDialog.setAdapter(adapter, null);
		//alertDialog.setMessage(temp);		

		alertDialog.setNeutralButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}});

		alertDialog.show();
	}
   public void showSettingsAlert(String provider) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainMapsActivity.this);
		alertDialog.setTitle(provider + " SETTINGS");
		alertDialog.setMessage(provider + " is not enabled! Want to go to settings menu?");
		alertDialog.setPositiveButton("Settings",
		new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				MainMapsActivity.this.startActivity(intent);
			}});

		alertDialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}});
		alertDialog.show();
	}   
   @Override
   /*public void onBackPressed() {
       new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
               .setMessage("Are you Sure?")
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       Intent intent = new Intent(Intent.ACTION_MAIN);
                       intent.addCategory(Intent.CATEGORY_HOME);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent);
                       finish();
                   }
               }).setNegativeButton("No", null).show();
   } */
   public void onBackPressed()
   {
       if(buttonCount >= 1)
       {
           Intent intent = new Intent(Intent.ACTION_MAIN);
           intent.addCategory(Intent.CATEGORY_HOME);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(intent);
       }
       else
       {
           Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
           buttonCount++;
       }
   }
}

