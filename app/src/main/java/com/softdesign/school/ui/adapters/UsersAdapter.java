package com.softdesign.school.ui.adapters;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends BaseAdapter{

    Context mContext;
    ArrayList<User> mUsers;
    LayoutInflater mInflater;

    public UsersAdapter(ArrayList<User> mUsers,Context mContext) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null){
            itemView=mInflater.inflate(R.layout.item_user_list,parent,false);
        }

        User user = (User) getItem(position);

        ImageView avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
        avatar.setImageDrawable(user.getmImage());
        TextView fullName = (TextView) itemView.findViewById(R.id.user_full_name);
        fullName.setText(user.getmFirstName() + " " + user.getmLastName());

        return null;

    }
}
