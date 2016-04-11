package app.AutoText;

import java.util.List;
import java.util.StringTokenizer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneStateReceiver extends BroadcastReceiver implements Runnable {
	TelephonyManager tm;
	PhoneStateListener ps;
	Context context;
	String IncomingNumber;
	Cursor cursor = null;
	Thread t;
	int x;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		x = 1;
		this.context = context;
		tm = (TelephonyManager) this.context
				.getSystemService(Context.TELEPHONY_SERVICE);
		t = new Thread(this);
		t.start();
		// Toast.makeText(this.context, "received", 0).show();
		ps = new PhoneStateListener() {

			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				// TODO Auto-generated method stub
				// super.onCallStateChanged(state, incomingNumber);
				IncomingNumber = incomingNumber;

				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:
					// Toast.makeText(PhoneStateReceiver.this.context, "idle",
					// 0).show();
					if (x == 1) {
						cursor = PhoneStateReceiver.this.context
								.getContentResolver().query(
										Uri.parse("content://call_log/calls"),
										null, null, null,
										CallLog.Calls.DATE + " DESC");
						while (cursor.moveToNext()) {

							String callLogID = cursor
									.getString(cursor
											.getColumnIndex(android.provider.CallLog.Calls._ID));
							String callNumber = cursor
									.getString(cursor
											.getColumnIndex(android.provider.CallLog.Calls.NUMBER));
							String callDate = cursor
									.getString(cursor
											.getColumnIndex(android.provider.CallLog.Calls.DATE));
							String callType = cursor
									.getString(cursor
											.getColumnIndex(android.provider.CallLog.Calls.TYPE));
							String isCallNew = cursor
									.getString(cursor
											.getColumnIndex(android.provider.CallLog.Calls.NEW));
							if (Integer.parseInt(callType) == CallLog.Calls.MISSED_TYPE
									&& Integer.parseInt(isCallNew) > 0) {
								//Toast.makeText(PhoneStateReceiver.this.context,"message received from "+IncomingNumber, Toast.LENGTH_LONG).show();
							ContactListHandler ch=new ContactListHandler(PhoneStateReceiver.this.context);
							List<String> list=ch.RetreiveContacts();
							for(int i=0;i<list.size();i++){
								String s=list.get(i);
								StringTokenizer st=new StringTokenizer(s,"()");
								while(st.hasMoreElements()){
									String name=st.nextToken();
									String number=st.nextToken();
									if(number.equals(IncomingNumber)){
										Toast.makeText(PhoneStateReceiver.this.context,"Missed call Detected from added contacts", Toast.LENGTH_LONG).show();
										try{
											String msg=AutoTextActivity.sp.getString("msg", "null");
											SmsManager smsm=SmsManager.getDefault();
										smsm.sendTextMessage(IncomingNumber, null,msg, null, null);
										Toast.makeText(PhoneStateReceiver.this.context,"Message Successfully Sent", Toast.LENGTH_LONG).show();	
										}
										catch(Exception e){
											Toast.makeText(PhoneStateReceiver.this.context,e.toString(), Toast.LENGTH_LONG).show();
										}
										}
									
									
								}
								
							}
							}
							x++;
							break;
						}
					}

				}
			}
		};
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tm.listen(ps, PhoneStateListener.LISTEN_CALL_STATE);
	}
}
