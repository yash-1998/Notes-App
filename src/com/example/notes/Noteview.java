package com.example.notes;




import com.example.notes.YashDbAdapter.Ydbhelper;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class Noteview extends Activity {
	Button nupdate,backbtn;
	EditText nvtitle,nvbody;
	YashDbAdapter nhelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noteview);
		setTitle("NOTE");
		nhelper=new YashDbAdapter(this);
		nupdate=(Button)findViewById(R.id.notesupdatebtn);
		nvtitle=(EditText)findViewById(R.id.notetitleviewtext);
		nvbody=(EditText)findViewById(R.id.notebodyviewtext);
		backbtn=(Button)findViewById(R.id.backbtn);
		Bundle bundle=getIntent().getExtras();
		String ntitlev=bundle.getString("TITLE");
		nvtitle.setText(ntitlev);
		String notsebody=nhelper.getnotebody(ntitlev);
		nvbody.setText(notsebody);
		
		
		
		
		//   note = nhelper.fetchNote(ntitlev);
          // startManagingCursor(note);
           //nvbody.setText(note.getString(
             //      note.getColumnIndexOrThrow(Ydbhelper.TITLE)));   
          backbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ba=new Intent(Noteview.this,DATABASE.class);
								startActivity(ba);
			}
		});
		nupdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String oldtitle=nvtitle.getText().toString();
				String newbody=nvbody.getText().toString();
				nhelper.updatenote(oldtitle, newbody);
				
			}
		});

		}
	
}

	
