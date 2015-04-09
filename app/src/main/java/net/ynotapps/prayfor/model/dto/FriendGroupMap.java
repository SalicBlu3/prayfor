package net.ynotapps.prayfor.model.dto;

import com.orm.SugarRecord;

/**
 * Maps Friends to a Group in a many to many relationship
 */
public class FriendGroupMap extends SugarRecord<FriendGroupMap> {
    private Friend friend;
    private FriendGroup friendGroup;

    public FriendGroupMap() {
    }

    public FriendGroupMap(Friend friend, FriendGroup friendGroup) {
        this.friend = friend;
        this.friendGroup = friendGroup;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public FriendGroup getFriendGroup() {
        return friendGroup;
    }

    public void setFriendGroup(FriendGroup friendGroup) {
        this.friendGroup = friendGroup;
    }
}
