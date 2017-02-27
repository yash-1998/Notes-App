package com.example.notes;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class Dview extends Activity {	
	TextView dbview;
	YashDbAdapter nhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dview);
		nhelper=new YashDbAdapter(this);
		dbview=(TextView)findViewById(R.id.dbview);
		dbview.setText(nhelper.getalldata());
		
	
	}
}