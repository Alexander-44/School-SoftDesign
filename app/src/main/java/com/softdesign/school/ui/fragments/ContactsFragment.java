package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.RecycleUsersAdapter;
import com.softdesign.school.ui.adapters.UsersAdapter;
import com.softdesign.school.utils.Lg;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    private NavigationView mNavigationView;
    ArrayList<User> mUsers = new ArrayList<User>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_contacts,null,false);
        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));

        ((MainActivity) getActivity()).lockAppBar(true,getResources().getString(R.string.drawer_contacts));
        mNavigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
        Lg.send_log(Lg.Loglvl.Info, this.getClass().getSimpleName(), "FRAGMENT - onCreateView");

        generateData();

        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleUsersAdapter(mUsers);
        mRecyclerView.setAdapter(mAdapter);

        return convertView;
    }

    private void generateData() {
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Виктор", "Серов"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Леонид", "Лебедев"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Станислав", "Васильков"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Ян", "Казаков"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Света", "Краснова"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Ева", "Ольховская"));
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        params.setAnchorId(R.id.coordinator_layout);
        params.anchorGravity = Gravity.BOTTOM | Gravity.END;
        mFloatingActionButton.setLayoutParams(params);
        mFloatingActionButton.setImageResource(R.drawable.ic_edit_24dp);
    }

    @Override
    public void onDestroyView() {
        Lg.send_log(Lg.Loglvl.Info, this.getClass().getSimpleName(), "FRAGMENT - onDestroyView()");
        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(false);
        super.onDestroyView();
    }
}
