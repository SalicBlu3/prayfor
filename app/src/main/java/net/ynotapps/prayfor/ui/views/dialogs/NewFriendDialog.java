package net.ynotapps.prayfor.ui.views.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import net.ynotapps.prayfor.R;
import net.ynotapps.prayfor.model.dto.FriendGroup;

import java.util.List;

import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static android.view.View.inflate;

/**
 * Dialog to Save friend
 */
public abstract class NewFriendDialog extends AlertDialog {

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

    public abstract void processNewData(String friendName, String friendGroupName);

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
            FriendGroupAdapter adapter = new FriendGroupAdapter(getContext(), FriendGroup.listAll(FriendGroup.class));
            spinner.setAdapter(adapter);
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
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Create Friend
                String friendName = editFriendName.getText().toString();

                // Ignore if friend name is empty
                if (friendName.trim().isEmpty()) {
                    String errorMessage = getContext().getResources().getString(R.string.new_friend_dialog_error_message_empty_name);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create Friend Group if required
                String friendGroupName = "";
                if (editFriendGroup.getVisibility() == VISIBLE) {
                    friendGroupName = editFriendGroup.getText().toString();
                } else {
                    friendGroupName = ((FriendGroup) spinner.getSelectedItem()).getName();
                }

                processNewData(friendName, friendGroupName);
            }
        };
        setButton(BUTTON_POSITIVE, "Save", listener);
    }

    // Cancel button that just closes the dialog
    private void setupNegativeButton() {
        setButton(BUTTON_NEGATIVE, "Cancel", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }

    static class FriendGroupAdapter extends BaseAdapter {

        private Context context;
        private List<FriendGroup> friendgroupList;

        FriendGroupAdapter(Context context, List<FriendGroup> friendgroupList) {
            this.context = context;
            this.friendgroupList = friendgroupList;
        }

        @Override
        public int getCount() {
            return friendgroupList.size();
        }

        @Override
        public FriendGroup getItem(int position) {
            return friendgroupList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView;

            if (convertView == null) {
                textView = (TextView) View.inflate(context, android.R.layout.simple_list_item_1, null);
            } else {
                textView = (TextView) convertView;
            }

            FriendGroup bean = getItem(position);
            textView.setText(bean.getName());
            textView.setTag(bean);

            return textView;
        }
    }

}
