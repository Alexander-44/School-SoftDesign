package com.softdesign.school.ui.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
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
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Fragment mFragment;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.navigation_drawer)
    DrawerLayout mNavigationDrawer;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    @Bind(R.id.appbar_layout)
    AppBarLayout mAppBar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;




    private View mHeaderLayout;
    CoordinatorLayout mCordinator;

    AppBarLayout.LayoutParams params = null;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       ButterKnife.bind(this);
       Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "Create");
       mCordinator = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
       mHeaderLayout = mNavigationView.inflateHeaderView(R.layout.navigation_header);
       setupToolbar();
       setupDrawer();

      if(savedInstanceState!=null){ //если запустили приложение первый раз, то подкладываем фрагмент профиля
       } else {
          getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new ProfileFragment()).commit();
       }
   }

    /**
     * Вешает слушатель на кнопки меню, устанавливает фрагмент.
     */
    private void setupDrawer(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        break;
                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        break;
                    case R.id.drawer_tasks:
                        mFragment = new TasksFragment();
                        break;
                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        break;
                    case R.id.drawer_setting:
                        mFragment = new SettingFragment();
                        break;
                }
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, mFragment).addToBackStack(null).commit();
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
     * Обрабатывает нажатия клавиши назад
     */
    @Override
    public void onBackPressed(){
        Lg.send_log(Lg.Loglvl.Info,this.getLocalClassName(), "ONBACKPRESSED");
        int count = getSupportFragmentManager().getBackStackEntryCount();//считываем количество фрагментов в BackStack
        Lg.send_log(Lg.Loglvl.Info, this.getLocalClassName(), "count:" + count);
        if(count==0){//если фрагментов не осталось - выходим
            finish();
            System.exit(0);
        }else {
            getSupportFragmentManager().popBackStack();
        }
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

    public void lockAppBar(boolean collapse,String title) {

        params = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();
        mCollapsingToolbar.setTitle(title);

        if (collapse) {

            mAppBar.setExpanded(false);
            AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener(){
                @Override
                public void onOffsetChanged(AppBarLayout mAppBar, int verticalOffset) {
                   if (mCollapsingToolbar.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbar) + getStatusBarHeight()) {
                       // params.setScrollFlags(0);
                        mCollapsingToolbar.setLayoutParams(params);
                        mAppBar.removeOnOffsetChangedListener(this);
                    }
                }
            };
            mAppBar.addOnOffsetChangedListener(mListener);
        } else {
            mAppBar.setExpanded(true);
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
            mCollapsingToolbar.setLayoutParams(params);
        }

    }

    public int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
        if(resourceId > 0){
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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
