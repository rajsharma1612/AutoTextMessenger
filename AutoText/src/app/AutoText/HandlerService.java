package app.AutoText;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class HandlerService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(getApplicationContext(), "Application activated", 0).show();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		//Toast.makeText(getApplicationContext(), "onCreate", 0).show();
		IntentFilter iff=new IntentFilter("android.intent.action.PHONE_STATE");
		registerReceiver(new PhoneStateReceiver(), iff);
		//Toast.makeText(getApplicationContext(), "Received", 0).show();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
		super.onDestroy();
		Toast.makeText(getApplicationContext(), "application deactivated", Toast.LENGTH_LONG).show();
	}

	
	
	
	

}
