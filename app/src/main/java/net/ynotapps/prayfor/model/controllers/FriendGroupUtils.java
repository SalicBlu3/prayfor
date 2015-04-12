package net.ynotapps.prayfor.model.controllers;

import net.ynotapps.prayfor.model.dto.FriendGroup;

import java.util.List;

/**
 * Created by dougylee on 12/04/15.
 */
public class FriendGroupUtils {

    // Checks if Friend group exists
    public static boolean containsFriendGroup(String name) {
        List<FriendGroup> friendGroups = getFriendGroup(name);
        return friendGroups.size() > 0;
    }

    public static List<FriendGroup> getFriendGroup(String name) {
        return FriendGroup.find(FriendGroup.class, "name = ?", name);
    }

}
