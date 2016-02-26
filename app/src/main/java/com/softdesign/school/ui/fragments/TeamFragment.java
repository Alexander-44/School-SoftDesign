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

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeamFragment extends Fragment {
    @Bind(R.id.navigation_view)
    private NavigationView mNavigationView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_team,null,false);
        getActivity().setTitle(getResources().getString(R.string.drawer_team));
        mNavigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
        Lg.send_log(Lg.Loglvl.Info, this.getClass().getSimpleName(), "FRAGMENT - onCreateView");
        ButterKnife.bind(this, getActivity());
        return convertView;
    }
    @Override
    public void onDestroyView() {
        Lg.send_log(Lg.Loglvl.Info, this.getClass().getSimpleName(), "FRAGMENT - onDestroyView()");
        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(false);
        super.onDestroyView();
    }
}