package com.example.tictacttoe;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;

import com.example.tictacttoe.ui.GameActivity;

import static com.example.tictacttoe.MainActivity.*;

public class SmsReceiver  extends BroadcastReceiver {
    SharedPreferences prefs ;

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String RECEIVE_BOOT_COMPLETED = "android.permission.RECEIVE_BOOT_COMPLETED";
    String senderNum = "";String message="";

    public static String _messageType = "";

    private String identifier = "%$$^";
    private String separator = "|";
    private String gameName = "TicTacToe";
    private String invite = "invite";
    private String accpeted = "accpeted";
    private String denied = "denied";
    private String selected = "selected";
    private static String player = "player";
    private static String winner = "winner";

    GameActivity gameActivity = ((GameActivity)GameActivity.gameActivityContext);

    public void onReceive(Context context, Intent intent) {

        if(gameOn != null){
            prefs = context.getSharedPreferences(
                    "com.example.tictocttoe", context.MODE_PRIVATE);

            System.out.println("dfffffffffffffffffffffffff");
            if (intent.getAction().equals(SMS_RECEIVED)) {

                // Retrieves a map of extended data from the intent.
                final Bundle bundle = intent.getExtras();

                try {

                    if (bundle != null) {

                        final Object[] pdusObj = (Object[]) bundle.get("pdus");

                        for (int i = 0; i < pdusObj.length; i++) {

                            SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                            String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                            senderNum = phoneNumber;
                            message += currentMessage.getDisplayMessageBody();


                            parseMessage(message,context);

                        }


                        // message variable has value of the current message
                        // parse the message

                    } // bundle is null

                } catch (Exception e) {

                }
            }
        }

    }


    public void parseMessage(String message, Context context){
        System.out.println(message);
        if (message.startsWith("%$$^")){
            if (message.contains("invite")){
                _messageType = "invite";
                message.lastIndexOf("|");
                String name =  message.substring(message.lastIndexOf("|"));
                System.out.println("you has been invited");


                gameActivity.showAlert("Invited" ,"You are invited by " + name);

                // trh karo ok woking :)
            }
            else if(message.contains(accpeted)){
                gameActivity.inviteAccpeted();
            }
            else if (message.contains(selected)){
                char x = message.charAt(message.indexOf("(") + 1);
                char y = message.charAt(message.indexOf(")") - 1);
                gameActivity.openentMove(Integer.parseInt(x+""),Integer.parseInt(y+""));
                gameActivity.yourTurn = true;
                gameActivity.ticTacToeView.setEnabled(true);

            }
            else if(message.contains(player)){
                char x = message.charAt(message.indexOf("(") + 1);
                char y = message.charAt(message.indexOf(")") - 1);
                gameActivity.playerMove(Integer.parseInt(x+""),Integer.parseInt(y+""));
                //gameActivity.ticTacToeView.setEnabled(false);
            }
            else if(message.contains(winner)){
                gameActivity.information.setText("Winner is " + PreferencesService.instance().getSecondPlayer());
                /*gameActivity.ticTacToeView.reset();*/
                /*gameActivity.ticTacToeView.animateWin(winPoints[0].i, winPoints[0].j, winPoints[2].i, winPoints[2].j);*/
                gameActivity.ticTacToeView.setEnabled(false);
                gameActivity.resetButton.setVisibility(View.VISIBLE);
                gameActivity.start.setVisibility(View.VISIBLE);
                gameActivity.cancel.setVisibility(View.VISIBLE);
            }

        }



    }



}
