package app.AutoText;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactsActivity extends Activity {
	
	ListView lv;
	Cursor contacts;
	Cursor phone;
	int contactId;
	String[] numbers;
	String name;
	List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts);
		setContentView(R.layout.contacts);
		lv = (ListView) findViewById(R.id.lvContacts);

		
		/*  Cursor c=managedQuery(Phone.CONTENT_URI, new
		  String[]{Phone.NUMBER},Data.CONTACT_ID + "=?",
		  new String[]{String.valueOf(1)},Contacts.DISPLAY_NAME+" ASC"); String name=null;
		  if(c.moveToFirst()){ do{
		  name=c.getString(c.getColumnIndex(Phone.NUMBER));
		 //
		  list.add(name); } while(c.moveToNext()); }
		 */
		
//------------------------------------------------------------------------------		 
		int ContactId;
		Cursor rawc=managedQuery(RawContacts.CONTENT_URI, new String[]{RawContacts._ID}, null, null, null);
		int[] dataId=new int[rawc.getCount()];
		int x=0;
		if(rawc.moveToFirst()){
			do{
				contactId=rawc.getInt(rawc.getColumnIndex(RawContacts._ID));
				dataId[x]=contactId;
				x++;
			}
			while(rawc.moveToNext());
		}
		
		numbers=new String[rawc.getCount()];
		for(int z=0;z<dataId.length;z++){
		Cursor phones=managedQuery(Phone.CONTENT_URI, new String[]{Phone.NUMBER},Data.CONTACT_ID + "=?",new String[]{String.valueOf(dataId[z])},null);
		if(phones.moveToFirst()){
			do{
				numbers[z]=phones.getString(phones.getColumnIndex(Phone.NUMBER));
			}
			while(phones.moveToNext());
		}
		
		
		}
		
		
		
		String[] projection={Contacts.DISPLAY_NAME};
		for(int y=0;y<dataId.length;y++){
		Cursor c=managedQuery(Contacts.CONTENT_URI, projection,Contacts._ID + "=?",new String[]{String.valueOf(dataId[y])},null);
	
		if(c.moveToFirst()){
			do{
				name=c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME));
				list.add(name+"("+numbers[y]+")");
				
				
			}
			while(c.moveToNext());
		}}
		//---------------------------------------------------------------------

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, list);
		lv.setAdapter(aa);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		

	

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("Done");
		
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		ContactListHandler ch=new ContactListHandler(this);
		long[] sel=lv.getCheckItemIds();
		for(int i=0;i<sel.length;i++){
			
		String s=list.get((int)sel[i]);
		ch.AddContacts(s);
		
		}
		Toast.makeText(this,"Contacts Sussessfully Added",Toast.LENGTH_SHORT).show();
		
				return super.onOptionsItemSelected(item);
	}
	
	

	

	

}
