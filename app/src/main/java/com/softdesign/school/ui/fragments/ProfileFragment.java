package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.utils.Lg;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {
    @Bind(R.id.navigation_view)
    private NavigationView mNavigationView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_profile,null,false);
        getActivity().setTitle(getResources().getString(R.string.personal_name));
        ((MainActivity) getActivity()).lockAppBar(false, getResources().getString(R.string.personal_name));
      //  mNavigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
        Lg.send_log(Lg.Loglvl.Info, this.getClass().getSimpleName(), "FRAGMENT - onCreateView");
        ButterKnife.bind(this, getActivity());
        return convertView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        params.setAnchorId(R.id.appbar_layout);
        params.anchorGravity = Gravity.BOTTOM | Gravity.END;
        mFloatingActionButton.setLayoutParams(params);
        mFloatingActionButton.setImageResource(R.drawable.ic_edit_24dp);
    }

    @Override
    public void onDestroyView() {
        Lg.send_log(Lg.Loglvl.Info, this.getClass().getSimpleName(), "FRAGMENT - onDestroyView()");
        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(false);
        super.onDestroyView();
    }
}
