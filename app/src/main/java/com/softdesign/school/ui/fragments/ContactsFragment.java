package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.utils.Lg;

public class ContactsFragment extends Fragment {
    private NavigationView mNavigationView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_contacts,null,false);
        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));
        mNavigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
        Lg.send_log(Lg.Loglvl.Info, this.getClass().getSimpleName(), "FRAGMENT - onCreateView");
        return convertView;
    }

    @Override
    public void onDestroyView() {
        Lg.send_log(Lg.Loglvl.Info, this.getClass().getSimpleName(), "FRAGMENT - onDestroyView()");
        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(false);
        super.onDestroyView();
    }
}
