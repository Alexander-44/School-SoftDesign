package com.softdesign.school.ui.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mNavigationDrawer;
    private Fragment mFragment;
    private FrameLayout mFrameConteiner;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "Create");
       setTitle("School lesson 1");

       mNavigationDrawer=(DrawerLayout) findViewById(R.id.navigation_drawer);
       mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
       mToolbar = (Toolbar) findViewById(R.id.toolbar);

       setupToolbar();
       setupDrawer();

       if(savedInstanceState!=null){ //если запустили приложение первый раз, то подкладываем фрагмент профиля
       } else {
           getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_conteiner,new ProfileFragment()).commit();
       }
   }

    private void setupDrawer(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                        break;
                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                        break;
                    case R.id.drawer_tasks:
                        mFragment = new TasksFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_tasks).setChecked(true);
                        break;
                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                        break;
                    case R.id.drawer_setting:
                        mFragment = new SettingFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_setting).setChecked(true);
                        break;
                }
                if(mFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_conteiner,mFragment).addToBackStack(null).commit();
                }
                mNavigationDrawer.closeDrawers();
                return false;
            }
        });
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
     * Обработчик нажатия на кнопку меню
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
           mNavigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Обрабатывает нажатия
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
   /*   switch (id){
            case
            break;
        }
        */
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
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "onRestoreInstanceState");
    }
}
