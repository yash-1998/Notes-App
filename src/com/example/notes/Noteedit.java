package com.example.notes;

import java.io.PrintWriter;




import com.example.notes.YashDbAdapter.Ydbhelper;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class Noteedit extends Activity {
	YashDbAdapter nhelper;
	EditText ntitle,nbody;
	Button savenote;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noteedit);
		setTitle("NEW NOTE");
		nhelper=new YashDbAdapter(this);
		ntitle=(EditText)findViewById(R.id.notetitleedittext);
		nbody=(EditText)findViewById(R.id.notebodyedittext);
		//ntitle.setText("");
		//nbody.setText("");
		savenote=(Button)findViewById(R.id.notesavebtn);
		savenote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String notitle=ntitle.getText().toString();
				String noody=nbody.getText().toString();
				if(notitle.isEmpty())
				{	return; }
				else
				{
					long id=nhelper.insertdata(notitle, noody);
					
					String[] notetitlearray = new String[10000];
					notetitlearray=nhelper.gettitle();
					int cn=nhelper.c;
					DATABASE.notesadapter.clear();
					for(int i=0;i<cn;i++)
					{	String notefor = notetitlearray[i];
						DATABASE.notesadapter.add(notefor);
						
					
					}
					try{
						
						PrintWriter pw = new PrintWriter(openFileOutput("Todo.txt",Context.MODE_PRIVATE));
						for(String tdo : DATABASE.noteslist)
						{
							pw.println(tdo);
						}
						pw.close();
					}catch(Exception e)
					{
						Log.i("ON BACK PRESSED",e.getMessage());
					}
					Intent b = new Intent(Noteedit.this,DATABASE.class);
					startActivity(b);
				
				}
			
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent back=new Intent(Noteedit.this,DATABASE.class);
		startActivity(back);
	}

}

	
	
