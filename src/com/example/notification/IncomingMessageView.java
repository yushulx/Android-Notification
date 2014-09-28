package com.example.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class IncomingMessageView extends Activity {

    static final public String KEY_FROM = "from";
    static final public String KEY_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView view = new TextView(this);
        view.setText(getIntent().getCharSequenceExtra(KEY_FROM) + ": " + getIntent().getCharSequenceExtra(KEY_MESSAGE));
        
        setContentView(view);

        // look up the notification manager service
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // cancel the notification that we started in IncomingMessage
        nm.cancel(R.string.imcoming_message_ticker_text);
    }
}