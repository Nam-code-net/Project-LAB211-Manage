/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.time.LocalDate;

/**
 *
 * @author VICTUS
 */
public class Course {
    private String id;
    private String name;
    private String type;
    private String title;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double tuitionFee;
    private String topic;

    public Course() {
    }

    public Course(String id, String name, String type, String title, LocalDate beginDate, LocalDate endDate, double tuitionFee, String topic) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tuitionFee = tuitionFee;
        this.topic = topic;
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

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public void getleanerPass(){
        
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", type=" + type + ", title=" + title + ", beginDate=" + beginDate + ", endDate=" + endDate + ", tuitionFee=" + tuitionFee + ", topic=" + topic + '}';
    }

    
    
}
