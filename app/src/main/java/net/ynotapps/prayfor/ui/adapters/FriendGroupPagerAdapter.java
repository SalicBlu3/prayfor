package net.ynotapps.prayfor.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.ynotapps.prayfor.model.dto.FriendGroup;
import net.ynotapps.prayfor.ui.fragments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dougylee on 12/04/15.
 */
public class FriendGroupPagerAdapter extends FragmentStatePagerAdapter {

    List<fragments.FriendGroupFragment> fragmentList = new ArrayList<>();

    public FriendGroupPagerAdapter(FragmentManager fm) {
        super(fm);
        refreshFragments();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    private void refreshFragments() {
        List<FriendGroup> friendGroups = FriendGroup.find(FriendGroup.class, "", new String[]{});
        ArrayList<fragments.FriendGroupFragment> updatedFriendGroupList = new ArrayList<>();
        for (FriendGroup friendGroup : friendGroups) {
            fragments.FriendGroupFragment friendGroupFragment = new fragments.FriendGroupFragment();
            friendGroupFragment.setFriendGroup(friendGroup);
            updatedFriendGroupList.add(friendGroupFragment);
        }
        fragmentList = updatedFriendGroupList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

    @Override
    public void notifyDataSetChanged() {
        refreshFragments();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
