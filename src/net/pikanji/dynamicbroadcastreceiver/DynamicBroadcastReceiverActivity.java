
package net.pikanji.dynamicbroadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

public class DynamicBroadcastReceiverActivity extends Activity {
    private BroadcastReceiver mReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    // Usually it's better to register/unregister in onResume/onPause.
    // In this sample, put them in onStart/onStop because I want receivers
    // be working even when the application is invisible.
    @Override
    protected void onStart() {
        // Set up broadcast receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction("my.broadcast.intent");
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mReceiver = new DynamicBroadcastReceiver();
        registerReceiver(mReceiver, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mReceiver);
        super.onStop();
    }

    private class DynamicBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("DynamicBroadcastReceiver", "Received broadcast: " + intent.getAction());
        }
    }
}
