package app.AutoText;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AutoTextActivity extends Activity{
	public static final int delete=Menu.FIRST;
	Button SetMess,AddCont,Settings,Exit;
	EditText etSelMessage;
	CheckBox cbActivate;
	public static SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		cbActivate=(CheckBox)findViewById(R.id.cbActivate);
		
		etSelMessage=(EditText)findViewById(R.id.etMess);
		AddCont=(Button)findViewById(R.id.btnAddCont);
		AddCont.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent it=new Intent(AutoTextActivity.this,ContactsActivity.class);
			startActivity(it);
			}
		});
		
		Settings=(Button)findViewById(R.id.btnSettings);
		Settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(AutoTextActivity.this,SettingsActivity.class);
				startActivity(it);
			}
		});
		
		Exit=(Button)findViewById(R.id.btnExit);
		Exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(AutoTextActivity.this,HandlerService.class);
				if(cbActivate.isChecked()){
					// it=new Intent(AutoTextActivity.this,HandlerService.class);
					startService(it);
					//Toast.makeText(getApplicationContext(), "applicaton activated", Toast.LENGTH_SHORT).show();
					finish();
					
				}
				else{
					stopService(it);
				finish();
				}
			
			}
		});
		
		SetMess=(Button)findViewById(R.id.btnSetMess);
		SetMess.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Intent it=new Intent(AutoTextActivity.this,MessageListActivity.class);
				startActivityForResult(it, 0);
				//etSelMessage.setText("Hello");
			
			
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("Delete All Messages");
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		DataHandler dh=new DataHandler(this);
		dh.sqdb.delete(dh.Table_Name, null, null);
		
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(resultCode){
		case RESULT_OK:
			etSelMessage.setText(data.getStringExtra("SelectedMessage"));
			sp=getSharedPreferences("SelectedMessage", MODE_WORLD_WRITEABLE);
			Editor ed=sp.edit();
			ed.putString("msg", etSelMessage.getText().toString());
			ed.commit();
		}
	}
	
	
	
	
	
	
	

}
