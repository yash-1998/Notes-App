package com.example.notes;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;







import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.os.Build;

public class DATABASE extends Activity{
	public static ArrayList<String> noteslist;
	 public static ArrayAdapter<String> notesadapter;
	 YashDbAdapter nhelper;
	Button newbtn;//Button viewdb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		nhelper=new YashDbAdapter(this);
		noteslist = new ArrayList<String>();
		notesadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,noteslist);
		ListView alistnotes = (ListView)findViewById(R.id.notelist);
		alistnotes.setAdapter(notesadapter);
		registerForContextMenu(alistnotes);
		try{
			Scanner scanner = new Scanner (openFileInput("Todo.txt"));
			while(scanner.hasNextLine()){
				String toDo = scanner.nextLine();
				notesadapter.add(toDo);
				
			}
			scanner.close();
		}
		catch(Exception e)
		{}
		
		newbtn=(Button)findViewById(R.id.newnote);
		newbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(DATABASE.this,Noteedit.class);
				startActivity(i);
				
				
			}
		});
/*		viewdb=(Button)findViewById(R.id.viewdatabase);
		viewdb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(DATABASE.this,Dview.class);
				startActivity(i);
			}
		});
		*/

		}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Options");
		String [] options = {"View","Delete"};
		for(String Options : options )
		{
			menu.add(Options);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterView.AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int selectedIndex = info.position;
		if(item.getTitle().equals("Delete"))
		{
			String indextitle = noteslist.get(selectedIndex);
			nhelper.deleteRow(indextitle);
			noteslist.remove(selectedIndex);
			notesadapter.notifyDataSetChanged();
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
			
		}
		if(item.getTitle().equals("View"))
	      {	String indextitle = noteslist.get(selectedIndex);
		    Intent v=new Intent(DATABASE.this,Noteview.class);
			v.putExtra("TITLE",indextitle);
			startActivity(v);
			
      	}
		return true;


	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}


}

	



