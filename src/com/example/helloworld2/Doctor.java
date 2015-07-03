package com.example.helloworld2;

import android.os.Parcel;
import android.os.Parcelable;

public class Doctor implements Parcelable{
	String name;
	String job,gender, phone;
	Double longit,latit;
	Double distance;
	String hospital;
	String About;
	Doctor()
	{
		
	}
	
	Doctor(String n,String j, String g,Double longitude,Double lat,String ph)
	{
		name=n;
		job=j;
		gender=g;
		longit=longitude;
		latit=lat;
		phone=ph;
	}
	Doctor(String n,String j, String g,Double longitude,Double lat,String ph,Double dist)
	{
		name=n;
		job=j;
		gender=g;
		longit=longitude;
		latit=lat;
		phone=ph;
		distance=dist;
	}
	Doctor(String n,String j, String g,Double longitude,Double lat,String ph,Double dist,String hosp)
	{
		name=n;
		job=j;
		gender=g;
		longit=longitude;
		latit=lat;
		phone=ph;
		distance=dist;
		hospital=hosp;
	}
	Doctor(String nam,String ab,String numb)
	{
		name=nam;
		About=ab;
		phone=numb;
	}
	Doctor(String n,String j, String g,Double longitude,Double lat,String ph,Double dist,String hosp,
			String Ab)
	{
		name=n;
		job=j;
		gender=g;
		longit=longitude;
		latit=lat;
		phone=ph;
		distance=dist;
		hospital=hosp;
		About=Ab;
	}
	void setname(String n)
	{
		name=n;
	}
	void setjob(String j)
	{
		job=j;
	}
	void setgender(String g)
	{
		gender=g;
	}
	String getabout()
	{
		return About;
	}
	Double getDistance()
	{
		return distance;
	}
	String gethospital()
	{
		return hospital;
	}
	String  getname()
	{
		return name;
	}
	String getjob()
	{
		return job;
	}

	String getgender()
	{
		return gender;	
	}
	String getphone()
	{
		return phone;
	}
	Double getlongit()
	{
		return longit;
	}
	Double getlatit()
	{
		return latit;
	}

	 @Override
     public int describeContents() {
         // TODO Auto-generated method stub
         return 0;
     }

     @Override
     public void writeToParcel(Parcel dest, int arg1) {
         // TODO Auto-generated method stub
         dest.writeString(name);
         dest.writeString(job);
         dest.writeString(gender);
         dest.writeDouble(longit);
         dest.writeDouble(latit);
         dest.writeString(phone);
         dest.writeDouble(distance);
         dest.writeString(hospital);
     }

     public Doctor(Parcel in) {         
         name = in.readString();
         job = in.readString();
         gender = in.readString();
         longit= in.readDouble();
         latit=in.readDouble();
         phone = in.readString();
         distance=in.readDouble();
         hospital=in.readString();
     }

     public static final Parcelable.Creator<Doctor> CREATOR = new Parcelable.Creator<Doctor>() {
         public Doctor createFromParcel(Parcel in) {
             return new Doctor(in);
         }

         public Doctor[] newArray(int size) {
             return new Doctor[size];
         }
     };
}
