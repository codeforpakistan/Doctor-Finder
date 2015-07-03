package com.example.helloworld2;

import java.util.ArrayList;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.BitmapDescriptorParcelable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LegendrowAdaptor extends ArrayAdapter<Item> {

    private ArrayList<Item> items;

    public LegendrowAdaptor(Context context, int textViewResourceId, ArrayList<Item> items) {
            super(context, textViewResourceId, items);
            this.items = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.legendrow, null);
            }
            Item o = items.get(position);
            if (o != null) {
                    TextView tt = (TextView) v.findViewById(R.id.toptext);
                    ImageView im= (ImageView) v.findViewById(R.id.icon);
                    //TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                    if (tt != null) {
                          tt.setText(o.text);                            
                          }
                    
                    final Item[] items=new Item[MainMapsActivity.jobList.size()];
                    float color=0;
           	      int ind=MainMapsActivity.jobList.indexOf(tt.getText());
           	    	  color=(ind+1)*30;
           	    	final BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    bmOptions.inSampleSize = 8;  //about the size of standard marker
                                    
           	    	  //BitmapDescriptor bit = BitmapDescriptorFactory.defaultMarker(color);
           	    	  //BitmapDescriptorFactory.
           	    	  //im.setImageBitmap((new MarkerOptions().icon(bit)).getIcon());
                    if(tt.getText().equals("Dentist"))
                    	im.setImageResource(R.drawable.dentist);
                    else if (tt.getText().equals("Cardiologist"))
                    	im.setImageResource(R.drawable.cardiologist);
                    else if (tt.getText().equals("Orthopedics"))
                    	im.setImageResource(R.drawable.orthopedics);
                    else if (tt.getText().equals("Eye Specialist"))
                    	im.setImageResource(R.drawable.eye_spec);
                    else if (tt.getText().equals("NeuroSurgeon"))
                    	im.setImageResource(R.drawable.neurosurgeon);
                    else
                    	im.setImageResource(R.drawable.other);
                    //tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);
           	    	  
           	    	  
           	      
                    //if(bt != null){
                      //    bt.setText("Status: "+ o.getOrderStatus());                    
            }
            return v;
    }
}