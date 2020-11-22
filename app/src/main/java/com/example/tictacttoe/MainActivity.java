package com.example.tictacttoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tictacttoe.ui.GameActivity;

public class MainActivity extends AppCompatActivity {

    int PERMISSION_CODE = 100;

    public static String gameOn = "true";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
        PreferencesService.init(this);

        //System.out.println(PreferencesService.instance().getSecondNum().e + " fdfdfdfdfdf");


        runCode();

    }

    public void runCode(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,
                            Manifest.permission.INTERNET, Manifest.permission.RECEIVE_SMS,},
                    PERMISSION_CODE);
            runCode();

        }
        else{
            if(!PreferencesService.instance().getSecondNum().equals("")){
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        }
    }

    public void requestPermission(){
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
            }
        }
    }

    private boolean checkIfAlreadyhavePermission() {
        boolean result =ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED;
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,
                        Manifest.permission.INTERNET, Manifest.permission.RECEIVE_SMS,},
                PERMISSION_CODE);

    }







}