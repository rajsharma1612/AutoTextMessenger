package app.AutoText;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHandler {
	public static String Database_Name="autotext.db";
	public static String Table_Name="messages";
	public static String AddContactTable="contacts";
	public static int Database_Version=1;
	SQLiteDatabase sqdb;
	Context context;
	
	
	public DataHandler(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		OpenHelper op=new OpenHelper(context);
		sqdb=op.getWritableDatabase();
	}
	
	public void Insert(String messages){
		ContentValues cv=new ContentValues();
		cv.put("messages", messages);
		sqdb.insert(Table_Name, null, cv);
		
		}
	
	/*
	public void AddContact(String number){
		ContentValues cv=new ContentValues();
		//cv.put("name", name);
		cv.put("number", number);
		sqdb.insert(AddContactTable, null, cv);
	}
	
	public List<String> RetreiveContactList(){
		List<String> list=new ArrayList<String>();
		Cursor c=sqdb.query(AddContactTable, new String[]{"number"}, null,null, null,null, null);
		if(c.moveToFirst()){
			do{
				//String name=c.getString(0);
			String number=c.getString(0);
			list.add(number);
			}
			while(c.moveToNext());
		}
		
		return list;
	}
	*/
	public List<String> SelectAll(){
		List<String> list=new ArrayList<String>();
		Cursor c=sqdb.query(Table_Name,new String[]{"messages"},null,null,null,null,null);
		if(c.moveToFirst()){
			do{
				String column=c.getString(0);
				list.add(column);
				
			}
			while(c.moveToNext());
		}
		return list;
	}
	
	class OpenHelper extends SQLiteOpenHelper{

		public OpenHelper(Context context){
			super(context,Database_Name,null,Database_Version);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table "+Table_Name+"(messages text)");
			//db.execSQL("create table "+AddContactTable+"(number text)");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("drop table if exists "+Table_Name);
			//db.execSQL("drop table if exists "+AddContactTable);
			onCreate(db);
		}
		
		
	}
	
}
