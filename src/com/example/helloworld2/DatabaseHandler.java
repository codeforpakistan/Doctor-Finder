package com.example.helloworld2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper{
		 
	    // All Static variables
	    // Database Version
	    private static final int DATABASE_VERSION = 1;
	 
	    // Database Name
	    private static final String DATABASE_NAME = "Doctor";
	 
	    // Contacts table name
	    private static final String TABLE_CONTACTS = "contacts";
	 
	    // Contacts Table Columns names	    
	    private static final String KEY_NAME = "name";
	    private static final String KEY_JOB = "job";
	    private static final String KEY_GENDER = "gender";
	    private static final String KEY_LONGITUDE = "longitude";
	    private static final String KEY_LATITUDE = "latitude";	    
	    private static final String KEY_PH_NO = "phone_number";
	    private static final String KEY_HOSP = "hospital";
	    private static final String KEY_ABOUT = "about";	    
	 
	    public DatabaseHandler(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	 
	    // Creating Tables
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
	                 + KEY_NAME + " TEXT," + KEY_JOB + " TEXT," 
	                 + KEY_GENDER + " TEXT," + KEY_LONGITUDE + " DOUBLE,"
	                 + KEY_LATITUDE + " DOUBLE,"  + KEY_PH_NO + " TEXT, " +
	                 KEY_HOSP + " TEXT, " +	                 
	                 KEY_ABOUT + " TEXT" +  ")";
	        db.execSQL(CREATE_CONTACTS_TABLE);	        
	    }
	    public void deleteTable(SQLiteDatabase db)
	    {
	    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);	   	 	 
	        onCreate(db);
	 
	    }
	    /*public void addContact(String name, String job, String gender, Double longit, Double lat, String phone) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        ContentValues values = new ContentValues();
	        values.put(KEY_NAME, name); // Contact Name
	        values.put(KEY_JOB, job);
	        values.put(KEY_GENDER, gender);
	        values.put(KEY_LONGITUDE, longit);
	        values.put(KEY_LATITUDE, lat);
	        values.put(KEY_PH_NO, phone);
	     
	        // Inserting Row
	        db.insert(TABLE_CONTACTS, null, values);
	    }*/
	    public void addContact(String name, String job, String gender, Double longit, Double lat,
	    		String phone,String Hosp,String Ab) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        ContentValues values = new ContentValues();
	        values.put(KEY_NAME, name); // Contact Name
	        values.put(KEY_JOB, job);
	        values.put(KEY_GENDER, gender);
	        values.put(KEY_LONGITUDE, longit);
	        values.put(KEY_LATITUDE, lat);
	        values.put(KEY_PH_NO, phone);	       
	        values.put(KEY_HOSP, Hosp);
	        values.put(KEY_ABOUT, Ab);
	        // Inserting Row
	        db.insert(TABLE_CONTACTS, null, values);
	    }
	    /*public Doctor getContact(String jobs) {
	        SQLiteDatabase db = this.getReadableDatabase();
	     
	        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {  KEY_NAME, 
	        		KEY_JOB,KEY_GENDER,KEY_LONGITUDE,KEY_LATITUDE,
	        		KEY_PH_NO, KEY_HOSP }, KEY_JOB + "=?",
	                new String[] { jobs }, null, null, null);
	        if (cursor != null)
	            cursor.moveToFirst();
	     
	        Doctor doc = new Doctor(cursor.getString(0), cursor.getString(1),cursor.getString(2),
	        		cursor.getDouble(3),cursor.getDouble(4),cursor.getString(5));
	        // return contact
	        return doc;
	    }
	    public Doctor getContact(String jobs,String gender) {
	        SQLiteDatabase db = this.getReadableDatabase();	           
	        
	        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {  KEY_NAME, 
	        		KEY_JOB,KEY_GENDER,KEY_LONGITUDE,KEY_LATITUDE,
	        		KEY_PH_NO,KEY_HOSP }, KEY_JOB + "=? "+ "and "+ KEY_GENDER +"=?",
	                new String[] { jobs,gender }, null, null, null);
	        if (cursor != null)
	        {
	            cursor.moveToFirst();
	     
	            Doctor doc = new Doctor(cursor.getString(0), cursor.getString(1),cursor.getString(2),
	        		cursor.getDouble(3),cursor.getDouble(4),cursor.getString(5));
	            
	        // return contact
	        return doc;
	        }
	        return null;
	    }
	     */
	    //FOR Seaching automatically 
	    /*public List<Doctor> getAllContacts(String jobs,String gend) {
	        String genders;
	    	List<Doctor> doctorList = new ArrayList<Doctor>();
	        if(gend.equals("male"))
	        	genders="('male')";
	        else if(gend.equals("female"))
	        	genders="('female')";
	        else if(gend.equals("both"))
	        	genders="('male','female')";
	        else 
	        	return null;

	       
	       String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " where " + KEY_JOB + 
	        		" = '" + jobs + "' and " + KEY_GENDER + " IN " + genders+"";	 	       
	        Cursor cursor = db.rawQuery(selectQuery, null);	 
	        // looping through all rows and adding to list
	        if(cursor.moveToFirst())
	        {
		        while (cursor != null)
		        { 	        		        		        		        		        	
	                Doctor doc = new Doctor(cursor.getString(0), cursor.getString(1),cursor.getString(2),
	    	        cursor.getDouble(3),cursor.getDouble(4),cursor.getString(5) );	                	                	               		          
	                doctorList.add(doc);
	                if(cursor.isLast())
	                	break;
	                if(!cursor.moveToNext())
	                	break;		        
		        }
		        return doctorList;
	        }
	        
	        return null;
	    }
	    */
	    ///To get about information
	    public String getAbout(String name) {	        
	    	List<Doctor> doctorList = new ArrayList<Doctor>();
	        

	        SQLiteDatabase db = this.getWritableDatabase();
	        /*Cursor cursor = db.query(TABLE_CONTACTS, new String[] {  KEY_NAME, 
	        		KEY_JOB,KEY_GENDER,KEY_LONGITUDE,KEY_LATITUDE,
	        		KEY_PH_NO }, KEY_JOB + "=? "+ "and "+ KEY_GENDER +"=?",
	                new String[] { jobs,genders }, null, null, null);
	       */
	       String selectQuery = "SELECT  "+  KEY_ABOUT+
	    		   " FROM " + TABLE_CONTACTS + " where " + KEY_NAME + 
	        		" = '" + name + "'";	 	       
	        Cursor cursor = db.rawQuery(selectQuery, null);	 
	        // looping through all rows and adding to list
	        if(cursor.moveToFirst())
	        {
	        	String ab="";
		        
		      	ab=cursor.getString(0);	                
		      
		        return ab;
	        }
	        
	        return null;
	    }

	    //for getting JOBLIST
	    public List<String> getJobList() {	        

	    	List<String> jobList=new ArrayList<String>();
	        SQLiteDatabase db = this.getWritableDatabase();
	        /*Cursor cursor = db.query(TABLE_CONTACTS, new String[] {  KEY_NAME, 
	        		KEY_JOB,KEY_GENDER,KEY_LONGITUDE,KEY_LATITUDE,
	        		KEY_PH_NO }, KEY_JOB + "=? "+ "and "+ KEY_GENDER +"=?",
	                new String[] { jobs,genders }, null, null, null);
	       */
	        //jobList.add("All");
	        String selectQuery = "SELECT  * "+  "FROM " + TABLE_CONTACTS + ""; 	       
	        Cursor cursor = db.rawQuery(selectQuery, null);	 
	        // looping through all rows and adding to list
	        if(cursor.moveToFirst())
	        {
		        while (cursor != null)
		        { 	        	
		        	if(!jobList.contains(cursor.getString(1)))
		        	{
			        	jobList.add(cursor.getString(1));           	               		          	                
		             
		        	}
		        	   if(cursor.isLast())
		                	break;
		                if(!cursor.moveToNext())
		                	break;
		        }
		        return jobList;
	        }
	        
	        return null;
	    }
	    	    
	    //for getting all doctors
	    public List<Doctor> getAllContacts(Double longit,Double latit) {
	        String genders;
	    	List<Doctor> doctorList = new ArrayList<Doctor>();	        
	        genders="('male','female')";	        
	        SQLiteDatabase db = this.getWritableDatabase();
	        /*Cursor cursor = db.query(TABLE_CONTACTS, new String[] {  KEY_NAME, 
	        		KEY_JOB,KEY_GENDER,KEY_LONGITUDE,KEY_LATITUDE,
	        		KEY_PH_NO }, KEY_JOB + "=? "+ "and "+ KEY_GENDER +"=?",
	                new String[] { jobs,genders }, null, null, null);
	       */
	       String selectQuery = "SELECT  * "+  "FROM " + TABLE_CONTACTS + "";	 	       
	        Cursor cursor = db.rawQuery(selectQuery, null);	 
	        // looping through all rows and adding to list
	        if(cursor.moveToFirst())
	        {
		        while (cursor != null)
		        { 	        		    
		        	Location l1=new Location("One");
					l1.setLatitude(latit);
					l1.setLongitude(longit);
					Location l2=new Location("two");
					l2.setLatitude(cursor.getDouble(3));
					l2.setLongitude(cursor.getDouble(4));
					double distance=l1.distanceTo(l2);	
					
	                Doctor doc = new Doctor(cursor.getString(0), cursor.getString(1),cursor.getString(2),
	    	        cursor.getDouble(3),cursor.getDouble(4),cursor.getString(5),distance,
	    	        cursor.getString(6),cursor.getString(7));	                	                	               		          
	                doctorList.add(doc);
	                if(cursor.isLast())
	                	break;
	                if(!cursor.moveToNext())
	                	break;		        
		        }
		        return doctorList;
	        }
	        
	        return null;
	    }

	    //for manual location
	    public List<Doctor> getAllContacts(String jobs,String gend,double longit,double latit) {
	        String genders;
	    	List<Doctor> doctorList = new ArrayList<Doctor>();
	        if(gend.equals("male"))
	        	genders="('male')";
	        else if(gend.equals("female"))
	        	genders="('female')";
	        else if(gend.equals("both"))
	        	genders="('male','female')";
	        else 
	        	return null;

	        SQLiteDatabase db = this.getWritableDatabase();
	        /*Cursor cursor = db.query(TABLE_CONTACTS, new String[] {  KEY_NAME, 
	        		KEY_JOB,KEY_GENDER,KEY_LONGITUDE,KEY_LATITUDE,
	        		KEY_PH_NO }, KEY_JOB + "=? "+ "and "+ KEY_GENDER +"=?",
	                new String[] { jobs,genders }, null, null, null);
	       */
	        String selectQuery= "SELECT  * "+  "FROM " + TABLE_CONTACTS + " where " + KEY_JOB + 
	        		" = '" + jobs + "' and " + KEY_GENDER + " IN " + genders+"";
	        		Cursor cursor = db.rawQuery(selectQuery, null);
	        /*if(jobs.equals("All"))
	        {
	        	selectQuery = "SELECT  * "+  "FROM " + TABLE_CONTACTS + " where " 
	        + KEY_GENDER + " IN " + genders + " ";
	        }*/
	        	 
	        // looping through all rows and adding to list
	        if(cursor.moveToFirst())
	        {
		        while (cursor != null)
		        { 	        		    
		        	Location l1=new Location("One");
					l1.setLatitude(latit);
					l1.setLongitude(longit);

					Location l2=new Location("two");
					l2.setLatitude(cursor.getDouble(3));
					l2.setLongitude(cursor.getDouble(4));

					double distance=l1.distanceTo(l2);
					

	                Doctor doc = new Doctor(cursor.getString(0), cursor.getString(1),cursor.getString(2),
	    	        cursor.getDouble(3),cursor.getDouble(4),cursor.getString(5),distance,
	    	        cursor.getString(6),cursor.getString(7));	                	                	               		          
	                doctorList.add(doc);
	                if(cursor.isLast())
	                	break;
	                if(!cursor.moveToNext())
	                	break;		        
		        }
		        return doctorList;
	        }
	        
	        return null;
	    }

	    // Upgrading database
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Drop older table if existed
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
	 
	        // Create tables again
	        onCreate(db);
	    }

		
}
