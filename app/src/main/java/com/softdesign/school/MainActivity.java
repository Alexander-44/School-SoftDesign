package com.softdesign.school;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String VISIBLE_KEY = "visible";
    public static final String TOOLBAR_COLOR_KEY = "toolbar_color_key";
    public static final String STATUSBAR_COLOR_KEY = "statusbar_color_key";

    private CheckBox mCheckBox;
    private EditText mEditText;
    private EditText mEditText2;
    private Button mButtonBlue;
    private Button mButtonGreen;
    private Button mButtonRed;
    private Toolbar mToolbar;
    private int mToolbarColor;
    private int mStatusbarColor;
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
        mButtonGreen = (Button) findViewById(R.id.btn_green);
        mButtonRed = (Button) findViewById(R.id.btn_red);
        mButtonBlue.setOnClickListener(this);
        mButtonGreen.setOnClickListener(this);
        mButtonRed.setOnClickListener(this);

       mToolbarColor = this.getResources().getColor(R.color.colorPrimary);
       mStatusbarColor = this.getResources().getColor(R.color.colorPrimaryDark);
       changeColor(mToolbarColor, mStatusbarColor);

       setupToolbar();
   }


    /**
     * Инициализирует toolbar
     */
    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this,"Menu click",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Обрабатывает нажатия
     * @param v
     */
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
                mToolbarColor = this.getResources().getColor(R.color.blue);
                mStatusbarColor = this.getResources().getColor(R.color.dark_blue);
                changeColor(mToolbarColor, mStatusbarColor);
                break;
            case R.id.btn_green:
                mToolbarColor = this.getResources().getColor(R.color.green);
                mStatusbarColor = this.getResources().getColor(R.color.dark_green);
                changeColor(mToolbarColor, mStatusbarColor);
                break;
            case R.id.btn_red:
                mToolbarColor = this.getResources().getColor(R.color.red);
                mStatusbarColor = this.getResources().getColor(R.color.dark_red);
                changeColor(mToolbarColor, mStatusbarColor);
                break;
        }
    }

    /**
     * Меняет цвет у toolbar and statusbar
     * @param toolbarColor
     * @param statusbarColor
     */
    private void changeColor(int toolbarColor, int statusbarColor){
        mToolbar.setBackgroundColor(toolbarColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(statusbarColor);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "Start");
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
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "Stop");
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
        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
            outState.putInt(TOOLBAR_COLOR_KEY, ((ColorDrawable) mToolbar.getBackground()).getColor());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                outState.putInt(STATUSBAR_COLOR_KEY, getWindow().getStatusBarColor());
            }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "onRestoreInstanceState");
        int visibleState =  savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        mEditText2.setVisibility(visibleState);
        changeColor(savedInstanceState.getInt(TOOLBAR_COLOR_KEY), savedInstanceState.getInt(STATUSBAR_COLOR_KEY));

    }
}
