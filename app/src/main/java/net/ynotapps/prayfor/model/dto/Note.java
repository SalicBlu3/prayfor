package net.ynotapps.prayfor.model.dto;

/**
 * Note that describes what happens to a prayer point
 */
public class Note {

    private static final String DEFAULT_FIRST_NOTE_STRING = "Prayer point created on %s";

    private long date;
    private String note;
    private PrayerPoint prayerPoint;

    public Note() {}

    public Note(long date, String note, PrayerPoint prayerPoint) {
        this.date = date;
        this.note = note;
        this.prayerPoint = prayerPoint;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PrayerPoint getPrayerPoint() {
        return prayerPoint;
    }

    public void setPrayerPoint(PrayerPoint prayerPoint) {
        this.prayerPoint = prayerPoint;
    }
}
