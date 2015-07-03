package com.example.helloworld2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Informationpage extends ActionBarActivity {
private static Button callbutton;
private static TextView text;
private static TextView text2;
private static String num;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informationpage);
		text=(TextView)findViewById(R.id.textView2);
		text2=(TextView)findViewById(R.id.textView1);
		Bundle b=this.getIntent().getExtras();
		String s=b.getString("name");		
		text.setText("Name : "+s);
		DatabaseHandler db=new DatabaseHandler(this);
        db.getWritableDatabase();
		String ab = db.getAbout(s)+" \nExperience : 10 years ";				
		text2.setText(ab);
		num=b.getString("phone");
	    callbutton=(Button)findViewById(R.id.button1);
	   callbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				String url = "tel:"+ num;
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
			    startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.informationpage, menu);
		return true;
	}

}
