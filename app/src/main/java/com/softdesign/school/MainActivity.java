package com.softdesign.school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.softdesign.school.utils.*;

public class MainActivity extends AppCompatActivity {

    private Lg log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        log.i(getLocalClassName(),"Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log.i(getLocalClassName(), "Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log.i(getLocalClassName(), "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log.i(getLocalClassName(), "Stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log.i(getLocalClassName(), "Restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.i(getLocalClassName(),"Destroy");
    }

}
