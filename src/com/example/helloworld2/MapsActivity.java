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
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MapsActivity extends ActionBarActivity {
	private static double longitude;
	private static double latitude;
	private float distance;
	
	private static CheckBox check;
	private static boolean manual;
	AppLocationService appLocationService;
	private LatLngBounds.Builder bounds;
	//private Doctor doctor;
	List<Doctor> doctorList = new ArrayList<Doctor>();
	List LatLngList =new ArrayList<LatLng>();
      
   private GoogleMap googleMap;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      /*requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
      setContentView(R.layout.activity_maps);
      
      appLocationService = new AppLocationService(MapsActivity.this);
      bounds = new LatLngBounds.Builder();
      distance=5;
      
      try {
         if (googleMap == null) {
        	 googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
         }
         googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
         //googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
         boolean verify=true;
                
         
 		/*if(manual==false)
 		{
         Location gpsLocation = appLocationService.getLocation(LocationManager.NETWORK_PROVIDER);
         if (gpsLocation != null) 
			{
				latitude = gpsLocation.getLatitude();
				longitude = gpsLocation.getLongitude();
				Toast.makeText(getApplicationContext(),
				"Mobile Location (GPS): \nLatitude: " + latitude
				+ "\nLongitude: " + longitude,
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
 		}
 		*/
// 		else
 		{
	 		Intent intent=getIntent();
	 		longitude=intent.getExtras().getDouble("longit");
	 		latitude=intent.getExtras().getDouble("latit");
	 		Toast.makeText(getApplicationContext(),
					"Mobile Location (GPS): \nLatitude: " + latitude
					+ "\nLongitude: " + longitude,
					Toast.LENGTH_LONG).show();	
 		}
         Bundle bundle = getIntent().getExtras();
			ArrayList<Doctor> doctorList = bundle.getParcelableArrayList("mylist");
     
         //Toast.makeText(this, "The doctor nearby is " + doctorList.get(0).getname() +"   ", Toast.LENGTH_LONG).show();
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
         float alpha;
         String asset="";
         List<String> jobList = new ArrayList<String>();
         for(int i=0;i<doctorList.size();i++)
	      {
        	 if((doctorList.get(i).getDistance()/1000)<distance)
        	 {
        		 alpha=(float) 1.0;
        	 }
        	 else
        		 alpha=(float) 0.5;
        	 BitmapDescriptor bit;
        	 jobList.add(doctorList.get(i).getjob());
          	 float color= (jobList.indexOf(doctorList.get(i).getjob())+1)*30;
          	 bit=BitmapDescriptorFactory.defaultMarker(color);
        	 /*if(doctorList.get(i).getjob().equals("Dentist"))
        	 {
        		 bit=BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);        		 
        	 }
        	 else if(doctorList.get(i).getjob().equals("NeuroSurgeon"))
        		 bit=BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
        	 else if(doctorList.get(i).getjob().equals("Cardiologist"))
        		 bit=BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
        	 else
        		 bit=BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
        	 */
        	 //"Distance: "+      		 String.format("%.2f",(doctorList.get(i).getDistance()/1000))
        	 String snippet="Phone: "+ doctorList.get(i).getphone()+" \nGender: "+"\n "+        			 
    		 doctorList.get(i).getgender();
        	 LatLng point=(com.google.android.gms.maps.model.LatLng) LatLngList.get(i);
        	 Marker TP = googleMap.addMarker(new MarkerOptions().
        	         position(point).title(doctorList.get(i).getname()+" -- "+
        	        		 doctorList.get(i).gethospital()).snippet(snippet).alpha(alpha)
        	        		 .icon(bit) );
        	        		 //.icon(BitmapDescriptorFactory.fromResource(R.drawable.dentist)  ));
        	 bounds.include(new LatLng(doctorList.get(i).getlatit(), doctorList.get(i).getlongit()));
	      }	        		      
         googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50));
        
         //Marker TP2 = googleMap.addMarker(new MarkerOptions().
         //position(TutorialsPoint2).title("Ali"));
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
		return super.onOptionsItemSelected(item);
	}
	public void openSettings()
	{
		Intent intent = new Intent(MapsActivity.this,OptionsActivity.class);
		MapsActivity.this.startActivity(intent);
		
	}
}




/*
public class MapsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maps, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
*/