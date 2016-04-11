package app.AutoText;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactListHandler {
	
	public static final String Database_name="MyContacts.db";
	public static final String Table_name="Contacts";
	public static final int Database_Version=1;
	SQLiteDatabase sqdb;
	Context context;
	
	public ContactListHandler(Context context){
		this.context=context;
		OpenHelper op=new OpenHelper(context);
		sqdb=op.getWritableDatabase();
	}
	
	public void AddContacts(String numbers){
		ContentValues cv=new ContentValues();
		cv.put("numbers", numbers);
		sqdb.insert(Table_name, null, cv);
	}
	
	public List<String> RetreiveContacts(){
		List<String> list=new ArrayList<String>();
		Cursor c=sqdb.query(Table_name, new String[]{"numbers"},null,null,null,null,null);
		if(c.moveToFirst()){
			do{
				String numbers=c.getString(0);
				list.add(numbers);
			}
			while(c.moveToNext());
		}
		
		return list;
	}
	
	class OpenHelper extends SQLiteOpenHelper{
		
		

		public OpenHelper(Context context) {
			super(context,Database_name,null,Database_Version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table "+Table_name+"(numbers text)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("drop table if exists "+Table_name);
			onCreate(db);
		}
	}

}
