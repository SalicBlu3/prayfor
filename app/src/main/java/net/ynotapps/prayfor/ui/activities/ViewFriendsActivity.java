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
import net.ynotapps.prayfor.model.controllers.FriendRetriever;
import net.ynotapps.prayfor.model.dto.Friend;
import net.ynotapps.prayfor.model.dto.FriendGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

// Display all friends in friend groups in a Viewpager
public class ViewFriendsActivity extends ActionBarActivity {

    @InjectView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);
        pager.setAdapter(new FriendGroupPagerAdapter(getSupportFragmentManager()));
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
        if (id == R.id.action_delete) {
            new AlertDialog.Builder(this)
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteFriendGroup();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        }

        return true;
    }

    private void deleteFriendGroup() {
        
    }

    public static class FriendGroupPagerAdapter extends FragmentStatePagerAdapter{

        public FriendGroupPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            List<FriendGroup> friendGroups = FriendGroup.find(FriendGroup.class, "", new String[]{});
            FriendGroupFragment friendGroupFragment = new FriendGroupFragment();
            friendGroupFragment.setFriendGroup(friendGroups.get(position));
            return friendGroupFragment;
        }

        @Override
        public int getCount() {
            return (int) FriendGroup.count(FriendGroup.class, "", new String[]{});
        }

        @Override
        public CharSequence getPageTitle(int position) {
            FriendGroupFragment fragment = (FriendGroupFragment) getItem(position);
            return fragment.getFriendGroup().getName();
        }
    }

    public static class FriendGroupFragment extends Fragment {

        private FriendGroup friendGroup;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            ListView listView = new ListView(getActivity());

            // Add data to List View
            List<Friend> friends = FriendRetriever.retrieveAllFriendsFromGroup(friendGroup);
            ArrayAdapter<Friend> adapter = new ArrayAdapter<Friend>(getActivity(), android.R.layout.simple_list_item_1, friends);
            listView.setAdapter(adapter);

            return listView;
        }

        public FriendGroup getFriendGroup() {
            return friendGroup;
        }

        public void setFriendGroup(FriendGroup friendGroup) {
            this.friendGroup = friendGroup;
        }
    }
}
