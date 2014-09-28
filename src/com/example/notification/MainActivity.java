package com.example.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.incoming_message);
		
		Button button = (Button) findViewById(R.id.notify_app);
        button.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    showAppNotification("server", "hello"); // test
                }
            });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	void showAppNotification(String from, String message) {
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent notifyIntent = new Intent(this, IncomingMessageView.class);
        notifyIntent.putExtra(IncomingMessageView.KEY_FROM, from);
        notifyIntent.putExtra(IncomingMessageView.KEY_MESSAGE, message);
        
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                this,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        
        Notification notif = new Notification.Builder(this)
        .setContentTitle("TWAIN Scanner Status ")
        .setContentText(message)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentIntent(pendingIntent)
        .setTicker(message)
        .build();
        
        notif.defaults = Notification.DEFAULT_ALL;
        
        nm.notify(R.string.imcoming_message_ticker_text, notif);
    }
}
