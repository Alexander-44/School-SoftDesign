package com.softdesign.school;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.utils.Lg;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String VISIBLE_KEY = "visible";

    private CheckBox mCheckBox;
    private EditText mEditText;
    private EditText mEditText2;
    private Button mButtonBlue;
    private Button mButtonGreen;
    private Button mButtonRed;
    private Toolbar mToolbar;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "Create");

        setTitle("School lesson 1");

        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);

        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mButtonBlue = (Button) findViewById(R.id.btn_blue);
        mButtonBlue.setOnClickListener(this);
        mButtonGreen = (Button) findViewById(R.id.btn_green);
        mButtonGreen.setOnClickListener(this);
        mButtonRed = (Button) findViewById(R.id.btn_red);
        mButtonRed.setOnClickListener(this);

        setupToolbar();

   }

    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this,"Menu click",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.checkBox:
                Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
                if(mCheckBox.isChecked()){
                    mEditText2.setVisibility(View.INVISIBLE);
                } else {
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_blue:
                mToolbar.setBackgroundResource(R.color.blue);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = this.getWindow();
                 //   window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                 //   window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    window.setStatusBarColor(parseColor("#002137"));
                    window.setStatusBarColor(RED);
                    window.setStatusBarColor(Color.RED);
                    window.setStatusBarColor(this.getResources().getColor(R.color.green));
                 //   window.setStatusBarColor(this.getResources().getColor(R.color.green,this.getTheme()));
                }
                break;
            case R.id.btn_green:
                mToolbar.setBackgroundResource(R.color.green);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = this.getWindow();
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(this.getResources().getColor(android.R.color.holo_green_dark));


                }
                break;
            case R.id.btn_red:
                mToolbar.setBackgroundResource(R.color.red);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = this.getWindow();
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(parseColor("#8b0000"));
                }
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Lg.send_log(Lg.Loglvl.Info,this.getLocalClassName(), "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.send_log(Lg.Loglvl.Info,this.getLocalClassName(), "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.send_log(Lg.Loglvl.Info,this.getLocalClassName(), "Stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.send_log(Lg.Loglvl.Info,this.getLocalClassName(), "Restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "Destroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "onSaveInstanceState");
        outState.putBoolean(VISIBLE_KEY,mEditText2.getVisibility()==View.VISIBLE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "onRestoreInstanceState");
        int visibleState =  savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        mEditText2.setVisibility(visibleState);

    }
}
