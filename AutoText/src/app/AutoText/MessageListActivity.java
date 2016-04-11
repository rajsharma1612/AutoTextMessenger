package app.AutoText;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MessageListActivity extends Activity{
	ListView lvMessage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);
		lvMessage=(ListView)findViewById(R.id.lvMessages);
		DataHandler dh=new DataHandler(this);
		 final List<String> list=dh.SelectAll();
		ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,list);
		lvMessage.setAdapter(aa);
		lvMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent data=new Intent();
				//TextView tv=(TextView)view;
				//String s=tv.getText().toString();
				data.putExtra("SelectedMessage",list.get(position));
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}

}
