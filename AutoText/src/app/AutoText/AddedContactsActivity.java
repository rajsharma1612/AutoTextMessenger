package app.AutoText;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AddedContactsActivity extends Activity {
	ListView lvAddedCont;
	ContactListHandler ch;
	ArrayAdapter<String> aa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try{
		setContentView(R.layout.addedcontacts);
		lvAddedCont=(ListView)findViewById(R.id.lvAddedCont);
		
		 ch=new ContactListHandler(this);
		List<String> list=ch.RetreiveContacts();
		aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
		lvAddedCont.setAdapter(aa);
		}
		catch(Exception e){
		Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("Remove All Contacts");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		ch.sqdb.delete(ch.Table_name, null,null);
		aa.notifyDataSetChanged();
		
		
		return super.onOptionsItemSelected(item);
	}
	
	
}
