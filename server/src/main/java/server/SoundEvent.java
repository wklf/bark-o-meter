package server;

import java.sql.Timestamp;

public class SoundEvent {

    private int id;
    private Timestamp time;
    private String event;
    private int level;

    public SoundEvent(int id, Timestamp time, String event, int level){
        this.id = id;
        this.time = time;
        this.event = event;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString(){
        return "id: " + id + ", time: " + time + ", type: " + event + ", level: " + level;
    }
}
