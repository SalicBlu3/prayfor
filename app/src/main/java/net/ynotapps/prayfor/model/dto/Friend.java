package net.ynotapps.prayfor.model.dto;

import com.orm.SugarRecord;

/**
 * Friends are grouped into friend contexts
 */
public class Friend extends SugarRecord<Friend> {
    private String name;

    public Friend() {}

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
