package net.ynotapps.prayfor.model.dto;

import com.orm.SugarRecord;

/**
 * Contexts where you can group your friends
 */
public class FriendGroup extends SugarRecord<FriendGroup> {

    private String name;

    public FriendGroup() {}

    public FriendGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
