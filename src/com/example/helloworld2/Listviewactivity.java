package com.example.helloworld2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listviewactivity extends ActionBarActivity {
private static ListView listview;
private ArrayAdapter<String> listadapter;
ArrayList<Doctor> doctorList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listviewactivity);
		//Bundle b=this.getIntent().getExtras();		  		
 		 Bundle bundle = getIntent().getExtras();
		 doctorList= bundle.getParcelableArrayList("mylist");
			
		//String [] array11=b.getStringArray("arraulisting");
		  listview=(ListView)findViewById(R.id.mainListView);		 
	      
	      listadapter=new ArrayAdapter<String>(this,R.layout.simplerow);
	      for(int i=0;i<doctorList.size();i++)
	      {	    	 
	    	  listadapter.add(doctorList.get(i).getname()+"\n"+
	          doctorList.get(i).gethospital()+"\n"+doctorList.get(i).getphone());	    	  
	      }
	  
	  //  listview.setBackgroundResource(R.layout.border1);
	      listview.setAdapter(listadapter);
	      
	      listview.setOnItemClickListener(new OnItemClickListener() {
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {	        	  
	          Intent intent = new Intent(Listviewactivity.this,Informationpage.class);
	          Bundle b = new Bundle();
	          b.putString("name", doctorList.get(position).getname());      
	          b.putString("about", doctorList.get(position).getabout());
	          b.putString("phone", doctorList.get(position).getphone());
	          intent.putExtras(b);
	          startActivity(intent);
	          // startActivity(newActivity);

	          }
	        });
		    }
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listviewactivity, menu);
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

