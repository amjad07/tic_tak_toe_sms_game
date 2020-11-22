package com.example.tictacttoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class FirstActivity extends AppCompatActivity {
    EditText firstPlayerEdit, firstPlayerNum;
    Button start;
    ImageView exit, replay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        PreferencesService.init(this);

        init();
        listeners();
    }

    public void init() {

        firstPlayerEdit = findViewById(R.id.firstPlayerEdit);
        firstPlayerNum = findViewById(R.id.firstPlayerNum);
        exit = findViewById(R.id.exit);
        replay = findViewById(R.id.replay);
        start = findViewById(R.id.start);
        PreferencesService.init(FirstActivity.this);
    }

    public void listeners() {

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

      /*          firstPlayerEdit.setText("me");
                firstPlayerNum.setText("03065882572");*/

                if (firstPlayerEdit.getText().toString().length() == 0) {
                    firstPlayerEdit.setError("Empty Field!!");
                } else if (firstPlayerNum.getText().toString().length() == 0) {
                    firstPlayerNum.setError("Empty Field!!");
                } else {

                    PreferencesService.instance().setFirstPlayer(firstPlayerEdit.getText().toString());
                    PreferencesService.instance().setFirstNum(firstPlayerNum.getText().toString());

                    startActivity(new Intent(FirstActivity.this, SecondPlayer.class));
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitAppCode();
            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FirstActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        exitAppCode();
    }

    public void exitAppCode() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}