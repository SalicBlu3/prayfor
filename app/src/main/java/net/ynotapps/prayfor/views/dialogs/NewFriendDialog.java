package net.ynotapps.prayfor.views.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import net.ynotapps.prayfor.R;
import net.ynotapps.prayfor.model.dto.Friend;
import net.ynotapps.prayfor.model.dto.FriendGroup;
import net.ynotapps.prayfor.model.dto.FriendGroupMap;

import java.util.List;

import butterknife.ButterKnife;

import static android.view.View.*;
import static android.view.View.VISIBLE;

/**
 * Dialog to
 */
public class NewFriendDialog extends AlertDialog {

    private TextView editFriendGroup;
    private TextView editFriendName;
    private ToggleButton toggle;
    private Spinner spinner;

    public NewFriendDialog(Context context) {
        super(context);
        setupCustomView();
        setupPositiveButton();
        setupNegativeButton();
    }

    private void setupCustomView() {
        View customView = inflate(getContext(), R.layout.dialog_new_friend, null);
        editFriendName = ButterKnife.findById(customView, R.id.editFriendName);
        editFriendGroup = ButterKnife.findById(customView, R.id.editFriendGroup);
        toggle = ButterKnife.findById(customView, R.id.toggle);
        spinner = ButterKnife.findById(customView, R.id.spinner);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    displayGroupNew();
                } else {
                    displayGroupDropdown();
                }
            }
        });

        // Populate Spinner with existing friend groups.
        // If user has no groups, just display a new group
        List<FriendGroup> friendGroups = FriendGroup.listAll(FriendGroup.class);
        if (friendGroups.size() == 0) {
            toggle.setVisibility(GONE);
            displayGroupNew();
        } else {
            ArrayAdapter<FriendGroup> friendGroupArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, friendGroups);
            spinner.setAdapter(friendGroupArrayAdapter);
        }
        setView(customView);

    }

    private void displayGroupDropdown() {
        spinner.setVisibility(VISIBLE);
        editFriendGroup.setVisibility(INVISIBLE);
    }

    private void displayGroupNew() {
        spinner.setVisibility(INVISIBLE);
        editFriendGroup.setVisibility(VISIBLE);
    }

    // Save button
    private void setupPositiveButton() {
        setButton(BUTTON_POSITIVE, "Save", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Create Friend
                String friendName = editFriendName.getText().toString();
                Friend friend = new Friend(friendName);
                friend.save();

                FriendGroup friendGroup;

                // Create Friend Group if required
                if (editFriendGroup.getVisibility() == VISIBLE) {
                    String friendGroupName = editFriendGroup.getText().toString();
                    friendGroup = new FriendGroup(friendGroupName);
                    friendGroup.save();
                } else {
                    friendGroup = new FriendGroup();
                }

                FriendGroupMap friendGroupMap = new FriendGroupMap(friend, friendGroup);
                friendGroupMap.save();
            }
        });
    }

    // Cancel button that just closes the dialog
    private void setupNegativeButton() {
        setButton(BUTTON_NEGATIVE, "Cancel", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
    }

}
