package net.ynotapps.prayfor.model.controllers;

import net.ynotapps.prayfor.model.dto.FriendGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dougylee on 12/04/15.
 */
public class FriendGroupUtils {

    public static List<FriendGroup> getSortedFriendGroupList() {
        List<FriendGroup> friendGroups = FriendGroup.find(FriendGroup.class, "", new String[]{});
        Collections.sort(friendGroups, new Comparator<FriendGroup>() {
            @Override
            public int compare(FriendGroup lhs, FriendGroup rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        return friendGroups;
    }

    // Checks if Friend group exists
    public static boolean containsFriendGroup(String name) {
        List<FriendGroup> friendGroups = getFriendGroup(name);
        return friendGroups.size() > 0;
    }

    public static List<FriendGroup> getFriendGroup(String name) {
        return FriendGroup.find(FriendGroup.class, "name = ?", name);
    }

}
