package com.example.notes;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class NOTES extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		setTitle("NOTES");
		Thread t=new Thread()
		{ public void run()
			{
			 try{
				sleep(2500);
				Intent i=new Intent(NOTES.this,DATABASE.class);
				startActivity(i);
			    }
			 catch(Exception e){}
			
			}			};
			
		t.start();
	
		}
	}

