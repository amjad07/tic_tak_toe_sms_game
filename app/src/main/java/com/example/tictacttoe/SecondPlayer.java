package com.example.tictacttoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tictacttoe.ui.GameActivity;

public class SecondPlayer extends AppCompatActivity {
    ImageView exit,reload;
    Button start;
    EditText secondPlayerEdit,secondPlayerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_player);

        init();
        listeners();
    }

    public void init(){

        secondPlayerEdit=findViewById(R.id.secondPlayerEdit);
        secondPlayerNum=findViewById(R.id.secondPlayerNum);

        exit=findViewById(R.id.exit);
        reload=findViewById(R.id.reload);
        start =findViewById(R.id.start);

        PreferencesService.init(SecondPlayer.this);
    }

    public void listeners(){

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesService.instance().setSecondPlayer(secondPlayerEdit.getText().toString());

                startActivity(new Intent(SecondPlayer.this, GameActivity.class));

             /*   secondPlayerEdit.setText("he");
                secondPlayerNum.setText("03065882572");*/

                if (secondPlayerEdit.getText().toString().length()==0){
                    secondPlayerEdit.setError("Empty Field!!");
                }
                else if (secondPlayerNum.getText().toString().length()==0){
                    secondPlayerNum.setError("Empty Field!!");
                }
                else {

                    PreferencesService.instance().setSecondPlayer(secondPlayerEdit.getText().toString());
                    PreferencesService.instance().setSecondNum(secondPlayerNum.getText().toString());

                    startActivity(new Intent(SecondPlayer.this, GameActivity.class));
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondPlayer.this,MainActivity.class));
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SecondPlayer.this,MainActivity.class));
            }
        });
    }
}
