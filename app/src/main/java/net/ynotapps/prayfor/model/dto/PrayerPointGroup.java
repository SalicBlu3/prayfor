package net.ynotapps.prayfor.model.dto;

/**
 * Context to group prayers
 */
public class PrayerPointGroup {
    private String name;

    public PrayerPointGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
