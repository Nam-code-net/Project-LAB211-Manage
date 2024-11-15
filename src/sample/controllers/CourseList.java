/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import sample.dto.Course;
import sample.dto.Learner;
import sample.utils.FileUtils;
import sample.utils.Utils;

/**
 *
 * @author VICTUS
 */
public class CourseList extends ArrayList<Course> {
     private static final String COURSE_FILE_PATH = "Course.txt";
     private static final String LEARNER_FILE_PATH = "Learner.txt";
      private static List<Learner> learners = new ArrayList<>();

    private Course findById(String id) {
         loadFromFile();
        for (Course product : this) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
    public void addCourse(){
         loadFromFile();
        int getchoice =9;
    do{
        String topicId;
        do {
            topicId = Utils.getString("Enter Topic ID you want to add course: ");
            if (!Utils.validateTopic(topicId)) {
                System.out.println("Topic ID does not exist. Please try again.");
            }
        } while (!Utils.validateTopic(topicId));
        String courseId;
        do{
         courseId = Utils.getString("Enter the courseId: ");
        
        }while (findById(courseId) != null);
        String name = Utils.getString("Enter Course Name: ");
        String type = Utils.regexString("Enter Course Type (online/offline): ","Invalid type. Please enter 'online' or 'offline'" 
                , "^(online|offline)");
        String title = Utils.getString("Enter Course Title: ");
        LocalDate beginDate ;
         while(true){
        String input = Utils.getString("Enter Begin Date (dd/MM/yyyy): ");
            try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            beginDate = LocalDate.parse(input, formatter); 
                  break; 
            } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter in dd/MM/yyyy format.");
        }
        }
        LocalDate endDate; 
        while(true){
        String input = Utils.getString("Enter End Date (dd/MM/yyyy): ");
            try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            endDate = LocalDate.parse(input, formatter); 
            
                    if (endDate.isAfter(beginDate)) {
                        break; 
                    } else {
                     System.out.println("EndDate must be after begin date!!!.");
                    }
            } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter in dd/MM/yyyy format.");
        }
        }
        double tuitionFee = Utils.getDouble("Enter tuitionFee: ", 1, Double.MAX_VALUE);
        
        Course newCourse = new Course(courseId, name, type, title, beginDate, endDate, tuitionFee, topicId);
        this.add(newCourse);
        saveToFile();
        System.out.println("Add course successfully");
         getchoice = Utils.getInt("Do you want to add more Course? (yes: 1, no: 0)", 0, 1);
            if (getchoice == 0) {
                System.out.println("----------Back to main menu!!!-------------");
            }    
        
    }while(getchoice != 0);
}
    public void updateCourse(){
         loadFromFile();
    System.out.println("----- Update Course -----");
    String courseId = Utils.getString("Enter the Course ID: ");
    Course course = findById(courseId);
    
     if (course == null){
           System.out.println("The course does not exist");
       }else{
           System.out.println("Updating course with ID: " + courseId);
       }
        String newName = Utils.getString("Enter new Course Name (leave blank to keep unchanged): ", course.getName());
        String newType = Utils.regexString("Enter Course Type (online or offline): ", 
                                       "Invalid type. Please enter 'online' or 'offline'", 
                                       "^(online|offline)$", course.getType());
        String newTitle = Utils.getString("Enter new Course Title (leave blank to keep unchanged): ", course.getTitle());

         DateTimeFormatter dateFormatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         // Prompt for a new Begin Date with option to leave it blank
        String beginDateInput = Utils.getString("Enter new Begin Date (dd/MM/yyyy) (leave blank to keep unchanged): ", 
                                        course.getBeginDate().format(dateFormatter));
        LocalDate newBeginDate = course.getBeginDate();
        if (!beginDateInput.trim().isEmpty()) {
    try {
        LocalDate tempBeginDate = LocalDate.parse(beginDateInput, dateFormatter);
        newBeginDate = tempBeginDate;  // Only update if parsing is successful
    } catch (DateTimeParseException e) {
        System.out.println("Invalid date format. Please enter in dd/MM/yyyy format.");
    }
}

// Prompt for a new End Date with validation that it is after the Begin Date
    String endDateInput = Utils.getString("Enter new End Date (dd/MM/yyyy) (leave blank to keep unchanged): ", 
                                      course.getEndDate().format(dateFormatter));
    LocalDate newEndDate = course.getEndDate();
    if (!endDateInput.trim().isEmpty()) {
    try {
        LocalDate tempEndDate = LocalDate.parse(endDateInput, dateFormatter);
        if (tempEndDate.isAfter(newBeginDate)) {  // Ensure end date is after begin date
            newEndDate = tempEndDate;
        } else {
            System.out.println("End Date must be after Begin Date. Keeping the current end date.");
        }
    } catch (DateTimeParseException e) {
        System.out.println("Invalid date format. Please enter in dd/MM/yyyy format.");
    }
}
     double newTuitionFee  = Utils.getDouble("Enter new List Price (or 0 to keep unchanged): ", 0.0, Double.MAX_VALUE);
    course.setName(newName);
    course.setType(newType);
    course.setTitle(newTitle);
    course.setBeginDate(newBeginDate);
    course.setEndDate(newEndDate);
    if (newTuitionFee > 0){
        course.setTuitionFee(newTuitionFee);
    }
    saveToFile();
    System.out.println("Course item updated successfully.");
}
    
    public void deleteCourse() {
        loadFromFile();
    System.out.println("----- Delete Course -----");
    String topicId = Utils.getString("Enter the Topic u want to delete course: ");
     if (!Utils.validateTopic(topicId)) {
        System.out.println("The topic ID does not exist.");
        return; 
    }
    
    String courseId = Utils.getString("Enter the Course ID to delete: ");
    Course course = findById(courseId);

     if (course == null ){
            System.out.println("Course does not exist.");
        }else {
            boolean confirm = Utils.confirmYesNo("Do you want to delete this Course? (Y/N) ");
            if (confirm){
                this.remove(course);
                System.out.println("Course Item deleted successfully");
                saveToFile();
            }else {
                System.out.println("Deletion cancelled.");
            }
        }
}
    public void searchCourseByTopic() {
    loadFromFile();
    String topicCode = Utils.getString("Enter the Topic Code to search: ");
    if (!Utils.validateTopic(topicCode)) {
        System.out.println("Topic code does not exist.");
        return;
    }

    System.out.println("----- Courses with Topic Code: " + topicCode + " -----");
    boolean found = false;
    for (Course course : this) {
        if (course.getTopic().equalsIgnoreCase(topicCode)) {
            displayCourseInfo(course);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No courses found for the specified topic code.");
    }
}

public void searchCourseByName() {
    loadFromFile();
    String courseName = Utils.getString("Enter part of the Course Name to search: ");

    System.out.println("----- Courses containing name: " + courseName + " -----");
    boolean found = false;
    for (Course course : this) {
        if (course.getName().toLowerCase().contains(courseName.toLowerCase())) {
            displayCourseInfo(course);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No courses found with the specified name.");
    }
}

private void displayCourseInfo(Course course) {
    String status = (course.getEndDate().isAfter(LocalDate.now())) ? "Ongoing" : "Completed";
    int learnersPassed = getLearnersPassed(course); 
    int learnersFailed = getLearnersFailed(course); 
    double income = course.getTuitionFee() * (learnersPassed + learnersFailed);

    System.out.printf("ID: %s | Name: %s | Status: %s | Learners Passed: %d | Learners Failed: %d | Income: %.2f\n",
            course.getId(), course.getName(), status, learnersPassed, learnersFailed, income);
}

    public void displayCourses() {
        loadFromFile();
    System.out.println("----- Display Course Information -----");
    
    this.sort(Comparator.comparing(Course::getBeginDate));

    for (Course course : this) {
        String status = (course.getEndDate().isAfter(LocalDate.now())) ? "Ongoing" : "Completed";
        int learnersPassed = getLearnersPassed(course); 
        int learnersFailed = getLearnersFailed(course); 
        double income = course.getTuitionFee() * (learnersPassed + learnersFailed);

        System.out.printf("ID: %s | Name: %s | Status: %s | Learners Passed: %d | Learners Failed: %d | Income: %.2f\n",
                course.getId(), course.getName(), status, learnersPassed, learnersFailed, income);
    }
    
}
   public int getLearnersPassed(Course course) {
    loadLeanerFromFile();
    int result = 0;
    for (Learner learner : learners) {
        if (learner.getCourse().equals(course.getId()) && learner.getScore() != null && learner.getScore() >= 5.0) {
            result++;
        }
    }
    return result;
}

public int getLearnersFailed(Course course) {
    loadLeanerFromFile();
    int result = 0;
    for (Learner learner : learners) {
        if (learner.getCourse().equals(course.getId()) && learner.getScore() != null && learner.getScore() < 5.0) {
            result++;
        }
    }
    return result;
}


    public void loadLeanerFromFile() {
        
    learners.clear(); 
    List<String> lines = FileUtils.readFile(LEARNER_FILE_PATH);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng ngày phù hợp
    
    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length == 5) {  // Giả sử mỗi dòng có 5 phần tử
            String id = parts[0];
            String name = parts[1];
            LocalDate dateOfBirth = LocalDate.parse(parts[2], formatter);
            Double score ;
            String courseId = parts[4]; 
            if (parts[3].trim().isEmpty() || parts[3].trim().equalsIgnoreCase("null")) {
                score = null; // Keep it as null
            } else {
                score = Double.parseDouble(parts[3]);
            }
            if (Utils.validateCourse(courseId)){
            Learner learner = new Learner(id, name, dateOfBirth, score, courseId);
            learners.add(learner);
            }  
        }
    }
    System.out.println("Learners loaded from file successfully.");
}


    public void saveToFile() {
    List<String> lines = new ArrayList<>();
    
    for (Course course : this) {
        String begindateStr = course.getBeginDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String enddateStr = course.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String line = course.getId() + "," + course.getName() + "," + course.getType() + "," 
                    + course.getTitle() + "," + begindateStr + "," 
                    + enddateStr + "," + course.getTuitionFee() + "," 
                    + course.getTopic();
        lines.add(line);
    }

    FileUtils.overwriteFile(COURSE_FILE_PATH, lines);
    System.out.println("Courses saved to file successfully.");
}
    
    public void loadFromFile() {
    this.clear(); 
    List<String> lines = FileUtils.readFile(COURSE_FILE_PATH);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng ngày phù hợp
    
    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length == 8) { 
            String id = parts[0];
            String name = parts[1];
            String type = parts[2];
            String title = parts[3];
            LocalDate beginDate = LocalDate.parse(parts[4], formatter);
            LocalDate endDate = LocalDate.parse(parts[5], formatter);
            double tuitionFee = Double.parseDouble(parts[6]);
            String topic = parts[7];
            if (Utils.validateTopic(topic)){
            Course course = new Course(id, name, type, title, beginDate, endDate, tuitionFee, topic);
            this.add(course);
            }
        }
    }
    System.out.println("Courses loaded from file successfully.");
}

}
