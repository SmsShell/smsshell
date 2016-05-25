package com.smsshell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * BootupReceiver
 * 
 */
public class BootupReceiver extends BroadcastReceiver {

	private static final String TAG = "BootBroadcastReveiver";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			
	        Intent i = new Intent(context, MainActivity.class);
			i.putExtra("strRef","BOOT");
	        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        context.startActivity(i);

//			Toast.makeText(context, "ACTION_BOOT_COMPLETED", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "--------Boot start service-------------");
		}

	}

}