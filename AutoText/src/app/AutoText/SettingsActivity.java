package app.AutoText;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	ListView lvSettings;
	public static final int Add_Message_Dialog=1;
	String[] tasks={"Add Your Own Message","Maintain Added Contacts List"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		lvSettings=(ListView)findViewById(R.id.lvSettings);
		ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tasks);
		lvSettings.setAdapter(aa);
		lvSettings.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(position){
				case 0:
				showDialog(Add_Message_Dialog);
				break;
				
				case 1:
					
					Intent it=new Intent(SettingsActivity.this,AddedContactsActivity.class);
					startActivity(it);
					
					break;
			}
				}
		});
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		
		switch(id){
		case Add_Message_Dialog:
			Dialog d;
			final EditText et=new EditText(getApplicationContext());
			et.setHint("Enter Message");
			AlertDialog.Builder bd=new AlertDialog.Builder(this);
			bd.setView(et);
			bd.setTitle("Add dialog");
			bd.setPositiveButton("Add", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					DataHandler dh=new DataHandler(SettingsActivity.this);
					dh.Insert(et.getText().toString());	
					Toast.makeText(getApplicationContext(), "Message Added Successfully",Toast.LENGTH_SHORT).show();
				}
			});
			bd.setNegativeButton("Cancel", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			
		AlertDialog ad=bd.create();
		d=ad;
		d.show();
		}
		return super.onCreateDialog(id);
	}
	
	

}
