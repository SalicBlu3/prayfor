package net.ynotapps.prayfor.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.ynotapps.prayfor.R;
import net.ynotapps.prayfor.model.controllers.FriendGroupUtils;
import net.ynotapps.prayfor.model.controllers.FriendRetriever;
import net.ynotapps.prayfor.model.dto.Friend;
import net.ynotapps.prayfor.model.dto.FriendGroup;
import net.ynotapps.prayfor.model.dto.FriendGroupMap;
import net.ynotapps.prayfor.ui.views.dialogs.NewFriendDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

// Display all friends in friend groups in a Viewpager
public class ViewFriendsActivity extends ActionBarActivity {

    @InjectView(R.id.pager)
    ViewPager pager;
    private FriendGroupPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);
        adapter = new FriendGroupPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_delete:
                new AlertDialog.Builder(this)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFriendGroup();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                break;
            case R.id.action_add_friend:
                new NewFriendDialog(this) {
                    @Override
                    public void processNewData(String friendName, String friendGroupName) {
                        addNewFriend(friendName, friendGroupName);
                    }
                }.show();
                break;
        }

        return true;
    }

    private void addNewFriend(String friendName, String friendGroupName) {

        Friend friend = new Friend(friendName);
        friend.save();

        FriendGroup friendGroup;
        if (FriendGroupUtils.containsFriendGroup(friendGroupName)) {
            friendGroup = FriendGroupUtils.getFriendGroup(friendGroupName).get(0);
        } else {
            friendGroup = new FriendGroup(friendGroupName);
            friendGroup.save();
        }

        FriendGroupMap map = new FriendGroupMap(friend, friendGroup);
        map.save();
        adapter.notifyDataSetChanged();
    }

    private void deleteFriendGroup() {

    }

    public static class FriendGroupPagerAdapter extends FragmentStatePagerAdapter {

        List<FriendGroupFragment> fragmentList = new ArrayList<>();

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
            ArrayList<FriendGroupFragment> updatedFriendGroupList = new ArrayList<>();
            for (FriendGroup friendGroup : friendGroups) {
                FriendGroupFragment friendGroupFragment = new FriendGroupFragment();
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

    public static class FriendGroupFragment extends Fragment {

        private FriendGroup friendGroup;
        private ArrayAdapter<Friend> adapter;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            ListView listView = new ListView(getActivity());

            // Add data to List View
            List<Friend> friends = FriendRetriever.retrieveAllFriendsFromGroup(friendGroup);
            adapter = new ArrayAdapter<Friend>(getActivity(), android.R.layout.simple_list_item_1, friends);
            listView.setAdapter(adapter);

            return listView;
        }

        public FriendGroup getFriendGroup() {
            return friendGroup;
        }

        public void setFriendGroup(FriendGroup friendGroup) {
            this.friendGroup = friendGroup;
        }

        public String getTitle() {
            return friendGroup.getName();
        }
    }
}
