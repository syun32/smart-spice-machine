package com.example.ictproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitialActivity extends Activity {
    private final static String TAG="@@@InitialActivity";
    private Context mContext;
    private long pressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        mContext = this;
        final EditText[] condiment = {findViewById(R.id.condiment0), findViewById(R.id.condiment1)};

        Button button = findViewById(R.id.bt_sumbit);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String[] condiment_ = {condiment[0].getText().toString(),condiment[1].getText().toString()};

                if(condiment_[0].equals("")||condiment_[1].equals("")){
                    Toast.makeText(mContext, "입력을 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                PreferenceManager.setString(mContext, "condiment0", condiment_[0]);
                PreferenceManager.setString(mContext, "condiment1", condiment_[1]);
                finish();
            }
        });

        Button bt_ques = findViewById(R.id.bt_ques);
        bt_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InitialActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        if ( pressedTime == 0 ) {
            Toast.makeText(InitialActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                Toast.makeText(InitialActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
                pressedTime = 0 ;
            }
            else {
                super.onBackPressed();
                finish(); // app 종료 시키기
            }
        }
    }
}
