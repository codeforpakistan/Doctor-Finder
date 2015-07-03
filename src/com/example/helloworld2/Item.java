package com.example.helloworld2;

import com.google.android.gms.maps.model.BitmapDescriptor;

public class Item{
    public final String text;
    public final BitmapDescriptor icon;
  /*  public Item(String text, Integer icon) {
        this.text = text;
       this.icon = icon;
    }*/
    public Item(String text2, BitmapDescriptor icon2) {
		// TODO Auto-generated constructor stub
    	this.text = text2;
        this.icon = icon2;
	}
	@Override
    public String toString() {
        return text;
    }
}