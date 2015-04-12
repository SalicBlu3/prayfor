package net.ynotapps.prayfor.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.rengwuxian.materialedittext.MaterialEditText;

import net.ynotapps.prayfor.R;
import net.ynotapps.prayfor.model.controllers.FriendGroupUtils;
import net.ynotapps.prayfor.model.dto.Friend;
import net.ynotapps.prayfor.model.dto.FriendGroup;
import net.ynotapps.prayfor.model.dto.FriendGroupMap;
import net.ynotapps.prayfor.ui.adapters.FriendGroupPagerAdapter;
import net.ynotapps.prayfor.ui.fragments.FriendGroupFragment;
import net.ynotapps.prayfor.ui.views.dialogs.EditTextDialog;
import net.ynotapps.prayfor.ui.views.dialogs.NewFriendDialog;

import java.util.Arrays;
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

        final FriendGroup activeFriendGroup = getActiveFriendGroup();
        switch (id) {
            case R.id.action_delete:
                new AlertDialog.Builder(this)
                        .setTitle("Delete Group")
                        .setMessage(String.format("Would you like to delete %s", activeFriendGroup.getName()))
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
            case R.id.action_rename:

                final EditTextDialog editTextDialog = new EditTextDialog(this,
                        Arrays.asList("New Name"),
                        Arrays.asList(String.format("New name for %s...", activeFriendGroup.getName()))) {
                    @Override
                    public void processFields(List<MaterialEditText> fieldList) {
                        String updatedName = fieldList.get(0).getText().toString();
                        activeFriendGroup.setName(updatedName);
                        activeFriendGroup.save();
                        adapter.notifyDataSetChanged();
                    }
                };
                editTextDialog.setTitle("Rename Group");
                editTextDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextDialog.processFields(editTextDialog.getFieldList());
                    }
                });
                editTextDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                editTextDialog.show();
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
        getActiveFriendGroup().delete();
        adapter.notifyDataSetChanged();
    }

    private FriendGroup getActiveFriendGroup() {
        return getActiveFriendGroupFragment().getFriendGroup();
    }

    private FriendGroupFragment getActiveFriendGroupFragment() {
        return (FriendGroupFragment) adapter.getItem(pager.getCurrentItem());
    }
}
