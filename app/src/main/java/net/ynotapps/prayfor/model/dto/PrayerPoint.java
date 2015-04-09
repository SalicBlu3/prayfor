package net.ynotapps.prayfor.model.dto;

import com.orm.SugarRecord;

/**
 * Prayer point object
 */
public class PrayerPoint extends SugarRecord<PrayerPoint>{

    private String title;
    private String subtitle;
    private long eventDate;
    private boolean archived;
    private boolean answered;
    private PrayerPointGroup prayerPointGroup;
    private Friend friend;

    public PrayerPoint() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public PrayerPointGroup getPrayerPointGroup() {
        return prayerPointGroup;
    }

    public void setPrayerPointGroup(PrayerPointGroup prayerPointGroup) {
        this.prayerPointGroup = prayerPointGroup;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
