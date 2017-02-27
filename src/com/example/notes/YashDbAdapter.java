package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class YashDbAdapter {
	Ydbhelper helper;public int c=0;
	public YashDbAdapter(Context context)
	{
		helper=new Ydbhelper(context);
	}
	public long insertdata(String title,String body)
	{
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues contentvalue=new ContentValues();
		contentvalue.put(Ydbhelper.TITLE,title);
		contentvalue.put(Ydbhelper.BODY,body);
		long id=db.insert(Ydbhelper.TABLE_NAME,null,contentvalue);
		return id;
		//Toast.makeText(this,"DATA INSERTED",Toast.LENGTH_LONG).show();
		
		
	}
	public String getalldata()
	{	SQLiteDatabase db=helper.getWritableDatabase();
	
		String[] columns = {Ydbhelper.UID,Ydbhelper.TITLE,Ydbhelper.BODY};
		Cursor c=db.query(Ydbhelper.TABLE_NAME,columns, null,null,null,null,null);
		StringBuffer buffer = new StringBuffer();
		while(c.moveToNext())
		{
			int cid=c.getInt(c.getColumnIndex(Ydbhelper.UID));
			String title=c.getString(c.getColumnIndex(Ydbhelper.TITLE));
			String body=c.getString(c.getColumnIndex(Ydbhelper.BODY));
			buffer.append(cid+" "+title+" "+body+"\n");
			
		}
		return buffer.toString();
	
	}
	public String[] gettitle()
	{	
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] columns={Ydbhelper.TITLE};
		Cursor cursor=db.query(Ydbhelper.TABLE_NAME,columns ,null,null,null,null,null);
		//int titleindex=cursor.getColumnIndex(Ydbhelper.TABLE_NAME);
		String[] titlenot=new String[10000];
		for(int i=0;cursor.moveToNext();i++)
		{	c++;
			titlenot[i]=cursor.getString(cursor.getColumnIndex(Ydbhelper.TITLE));
		}
		return titlenot;
	}

	public void updatenote(String oldbody,String newbody)
	{
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues newvalues=new ContentValues();
		newvalues.put(Ydbhelper.BODY,newbody);
		String[] whereArgs={oldbody};
		db.update(Ydbhelper.TABLE_NAME,newvalues,Ydbhelper.TITLE+" =? ", whereArgs);
	}
	
	
	
	public String getnotebody(String Indextitle)
	  {
		  
		  SQLiteDatabase db=helper.getWritableDatabase();
			
			String[] columns = {Ydbhelper.UID,Ydbhelper.TITLE,Ydbhelper.BODY};
			Cursor c=db.query(Ydbhelper.TABLE_NAME,columns,helper.TITLE+" = '"+Indextitle+"'",null,null,null,null);
			StringBuffer buffer = new StringBuffer();
			while(c.moveToNext())
			{
				String body=c.getString(c.getColumnIndex(Ydbhelper.BODY));
				buffer.append(body);
				
			}
			return buffer.toString();
				
	  }
	public void deleteRow(String indextitle)
	{	
		SQLiteDatabase db=helper.getWritableDatabase();
		String[] whereArgs = {indextitle};
		db.delete(Ydbhelper.TABLE_NAME,Ydbhelper.TITLE+" =? ",whereArgs);
		
	}
	static class Ydbhelper extends SQLiteOpenHelper
	{	private static final String DATABASE_NAME="yashdb.db";
	private static final String TABLE_NAME="Notetable";
	public static final String TITLE="Notetitle";
	private static final String BODY="Notebody";
	private static final String UID="_id";
	private static final int DATABASE_VERSION=3;
	private Context context;
	private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITLE+" VARCHAR(255), "+BODY+" VARCHAR(255));";
	private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
		public Ydbhelper(Context context) {
			// TODO Auto-generated constructor stub
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context=context;
			
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
				try {
					db.execSQL(CREATE_TABLE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DROP_TABLE);
				onCreate(db);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
