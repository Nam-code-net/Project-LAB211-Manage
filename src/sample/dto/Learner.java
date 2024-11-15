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
public class Learner {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private Double score;
    private String course;

    public Learner() {
    }

    public Learner(String id, String name, LocalDate dateOfBirth, Double score, String course) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.score = score;
        this.course = course;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
     public String getStatus() {
        if (score == null){
            return "N/A";
        }
        return score >= 5.0 ? "Pass" : "Fail"; // Đậu nếu điểm >= 5, rớt nếu dưới 5
    }

   
    
    @Override
    public String toString() {
        return "Learner{" + "id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", score=" + score + ", course=" + course + '}';
    }
    
    
}
