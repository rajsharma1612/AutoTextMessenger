package app.AutoText;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends Activity implements Runnable {
    Thread t;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        t=new Thread(this);
        t.start();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		DataHandler dh=new DataHandler(this);
		List list=dh.SelectAll();
		if(list.contains("I AM BUSY")){
			startActivity(new Intent(this,AutoTextActivity.class));
			finish();
		}
		else{
		dh.Insert("I AM BUSY");
		dh.Insert("I AM DRIVING");
		dh.Insert("I AM IN A MEETING");
		startActivity(new Intent(this,AutoTextActivity.class));
		finish();
		}
	}
}