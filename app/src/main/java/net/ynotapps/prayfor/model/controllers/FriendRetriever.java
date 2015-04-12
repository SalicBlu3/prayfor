package net.ynotapps.prayfor.model.controllers;

import net.ynotapps.prayfor.model.dto.Friend;
import net.ynotapps.prayfor.model.dto.FriendGroup;
import net.ynotapps.prayfor.model.dto.FriendGroupMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class that controls the retrieval of friend data
 */
public class FriendRetriever {

    public static List<Friend> retrieveAllFriendsFromGroup(FriendGroup group) {
        List<FriendGroupMap> friendGroupMaps =
                FriendGroupMap.find(FriendGroupMap.class, "friend_group = ?", String.valueOf(group.getId()));

        ArrayList<Friend> friends = new ArrayList<>();

        for (FriendGroupMap map : friendGroupMaps) {
            friends.add(map.getFriend());
        }

        Collections.sort(friends, new Comparator<Friend>() {
            @Override
            public int compare(Friend lhs, Friend rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });

        return friends;
    }

    public static List<FriendGroup> retrieveAllGroupsThatContainFriend(Friend friend) {
        List<FriendGroupMap> friendGroupMaps =
                FriendGroupMap.find(FriendGroupMap.class, "friend = ?", String.valueOf(friend.getId()));

        ArrayList<FriendGroup> friendGroups = new ArrayList<>();
        for (FriendGroupMap map : friendGroupMaps) {
            friendGroups.add(map.getFriendGroup());
        }

        Collections.sort(friendGroups, new Comparator<FriendGroup>() {

            @Override
            public int compare(FriendGroup lhs, FriendGroup rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        return friendGroups;
    }

}
