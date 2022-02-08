package dzcorp.entity;

import java.time.Instant;

public class Photo {

    private String photo;
    private String location;
    private Instant time;
    private int sequence;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "JhonPhoto{" +
                "photo='" + photo + '\'' +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", sequence=" + sequence +
                '}';
    }
}
