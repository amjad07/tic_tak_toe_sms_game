package com.example.tictacttoe;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;

import com.example.tictacttoe.ui.GameActivity;

import java.util.ArrayList;
import java.util.List;

public class SendMessage {
    String temp_res;
    String temp_resD;
    int nMsgParts;
    int nMsgPartsD;
    int totalMessagesSent;
    int totalMessagesDelivered;
    public String SENT;
    public String DELIVER;
    private Context context;
    private int notification_id;

    private String identifier = "%$$^";
    private String separator = "|";
    private String gameName = "tictactoe";
    private String selected = "selected";
    private static String player = "player";

    GameActivity gameActivity = ((GameActivity)GameActivity.gameActivityContext);


    public SendMessage(Context context,String message, String number)
    {
        this.context = context;
        if (message.contains(selected)){
           // gameActivity.ticTacToeView.setEnabled(true);
        }
        else if (message.contains(player)){
           // gameActivity.ticTacToeView.setEnabled(false);

        }
        sendSMS(message,number,"12" );
    }

    public void sendSMS(final String messageBody,final String receiver,final String message_id)
    {
        SENT = "message.sent."+receiver+"."+message_id;
        DELIVER = "message.deliver."+receiver+"."+message_id;
        // db insert part
        // registering broadcasat

        // Receive when each part of the SMS has been sent
        BroadcastReceiver broadcastReceiverSent = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // We need to make all the parts succeed before we say we have succeeded.
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        temp_res = "RESULT_OK";
                        System.out.println(temp_res + " fddf");

                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        temp_res = "RESULT_ERROR_GENERIC_FAILURE";
                        System.out.println(temp_res + " fddf");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        temp_res = "RESULT_ERROR_NO_SERVICE";
                        System.out.println(temp_res + " fddf");
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        temp_res = "RESULT_ERROR_NULL_PDU";
                        System.out.println(temp_res + " fddf");
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        temp_res = "RESULT_ERROR_RADIO_OFF";
                        System.out.println(temp_res + " fddf");
                        break;
                }

                nMsgParts--;
                if (nMsgParts <= 0) {
                    totalMessagesSent--;
                    // Stop us from getting any other broadcasts (may be for other messages)
                    // Log.i(LOG_TAG, "All message part resoponses received, unregistering message Id: " + messageInfo.getMessageId());
                    context.unregisterReceiver(this);

                }
            }
        };


        context.registerReceiver(broadcastReceiverSent, new IntentFilter(SENT));
        //   context.registerReceiver(broadcastReceiverDeliver, new IntentFilter(DELIVER));


        SmsManager smsManager = SmsManager.getDefault();

        ArrayList<String> messageParts = smsManager.divideMessage(messageBody);
        ArrayList<PendingIntent> pendingIntentsSent = new ArrayList<PendingIntent>(messageParts.size());
        // ArrayList<PendingIntent> pendingIntentsDeliver = new ArrayList<PendingIntent>(messageParts.size());
        nMsgParts = messageParts.size();
        nMsgPartsD = messageParts.size();

        for (int i = 0; i < messageParts.size(); i++) {
            Intent sentIntent = new Intent(SENT);
            Intent deliverIntent = new Intent(DELIVER);

            pendingIntentsSent.add(PendingIntent.getBroadcast(context, 0, sentIntent, 0));
            //pendingIntentsDeliver.add(PendingIntent.getBroadcast(context, 1, deliverIntent, 0));
        }

        smsManager.sendMultipartTextMessage(receiver, null, messageParts, pendingIntentsSent, null);
    }

}
