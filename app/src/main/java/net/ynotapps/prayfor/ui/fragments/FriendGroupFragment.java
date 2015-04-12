package net.ynotapps.prayfor.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.ynotapps.prayfor.model.controllers.FriendRetriever;
import net.ynotapps.prayfor.model.dto.Friend;
import net.ynotapps.prayfor.model.dto.FriendGroup;

import java.util.List;

/**
 * Created by dougylee on 12/04/15.
 */
public class FriendGroupFragment extends Fragment {

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
