package sample.dto;


public class Topic {

     private String id;
     private String name;
     private String type;
     private String title;
     private int duration;

    public Topic(String id, String name, String type, String title, int duration) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.duration = duration;
    }

    public Topic() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Topic{" + "id=" + id + ", name=" + name + ", type=" + type + ", title=" + title + ", duration=" + duration + '}';
    }
    
    
     
    

}
