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
import java.util.List;
import sample.dto.Learner;
import sample.utils.FileUtils;
import sample.utils.Utils;

/**
 *
 * @author VICTUS
 */
public class LearnerList extends ArrayList<Learner> {
    private static final String LEARNER_FILE_PATH = "Learner.txt";
    private static final int MAX_LEARNERS = 30;
    
    private Learner findById(String id) {
        for (Learner product : this) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
     public int countLearnersInCourse(String courseId) {
        int count = 0;
        for (Learner learner : this) {
            if (learner.getCourse().equalsIgnoreCase(courseId)) {
                count++; 
            }
        }
        return count; 
    }
   public void addLearner() {
    loadFromFile();
    boolean continueAdding = true;
    String inputCourse;

    // Prompt for a valid course ID
    do {
        inputCourse = Utils.getString("Enter the course ID you want to add learner to: ");
        if (!Utils.validateCourse(inputCourse)) {
            System.out.println("Course does not exist. Please enter another course.");
        }
    } while (!Utils.validateCourse(inputCourse));

    while (continueAdding) {
        int currentCount = countLearnersInCourse(inputCourse);
        if (currentCount >= MAX_LEARNERS) {
            System.out.println("Cannot add more learners. Maximum limit reached.");
            break;
        }
        String learnerId;
        do{
        learnerId = Utils.getString("Enter the learner ID: ");
        }while(findById(learnerId) != null);
        String name = Utils.getString("Enter the name: ");
        LocalDate dateOfBirth;
        
        // Prompt for a valid date of birth
        while (true) {
            String input = Utils.getString("Enter the date of birth with format (dd/MM/yyyy): ");
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dateOfBirth = LocalDate.parse(input, formatter);
                if (dateOfBirth.isBefore(LocalDate.now())) {
                    break;
                } else {
                    System.out.println("Please enter a date in the past.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Please enter the correct format (dd/MM/yyyy).");
            }
        }
        Double score = null;
        Learner newLearner = new Learner(learnerId, name, dateOfBirth, score, inputCourse);
        this.add(newLearner); // Assuming learners is the list holding Learner objects
        savetoFile(); // Save the learner to file

        System.out.println("Learner added successfully.");

        // Ask if the user wants to add another learner
        int choice = Utils.getInt("Do you want to add another learner? (yes: 1, no: 0)", 0, 1);
        if (choice == 0) {
            continueAdding = false;
            System.out.println("----------Back to main menu!----------");
        }
    }
}

    
    public void enterScore(){
        loadFromFile();
        String leanerId = Utils.getString("Enter the leanerID u want to enter score: ");
        Learner learner = findById(leanerId);
        
        if(learner == null){
            System.out.println("The learner does not exist");
        }else {       
            Double score = Utils.getDouble("Enter the score for learner", 0, 10);
            learner.setScore(score);
            savetoFile();
            System.out.println("Enter score to file sucessfully");
            
        }
        
    }
    public void displayAllLearners() {
    loadFromFile();
    if (this.isEmpty()) {
        System.out.println("No learners to display.");
        return;
    }

    System.out.println("ID        | Name                | Date of Birth | GPA    | Course            | Status");
    System.out.println("--------------------------------------------------------------------------------------");
    for (Learner learner : this) {
        String status = learner.getStatus();
        System.out.printf("%-10s | %-20s | %-13s | %-6s | %-18s | %s%n",
                learner.getId(),
                learner.getName(),
                learner.getDateOfBirth(),
                learner.getScore() != null ? String.format("%.2f", learner.getScore()) : "N/A", // Handle null score
                learner.getCourse(),
                status);
    }
}
    public void savetoFile() {
    List<String> lines = new ArrayList<>();
    
    for (Learner learner : this) {  // Giả sử collection của bạn là danh sách các đối tượng Learner
        String dateofbirthStr = learner.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String line = learner.getId() + "," + learner.getName() + "," 
                    + dateofbirthStr + "," 
                    + learner.getScore() + "," + learner.getCourse();
        lines.add(line);
    }

    FileUtils.overwriteFile(LEARNER_FILE_PATH, lines);
    System.out.println("Learners saved to file successfully.");
}
    
   public void loadFromFile() {
    this.clear(); 
    List<String> lines = FileUtils.readFile(LEARNER_FILE_PATH);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Appropriate date format
    
    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length == 5) {  // Assuming each line has 5 parts
            String id = parts[0];
            String name = parts[1];
            LocalDate dateOfBirth = LocalDate.parse(parts[2], formatter);
            Double score = null; // Initialize score as null
            String courseId = parts[4]; 
            
            // Check if the score part is "null" or empty
            if (parts[3].trim().isEmpty() || parts[3].trim().equalsIgnoreCase("null")) {
                score = null; // Keep it as null
            } else {
                score = Double.parseDouble(parts[3]);
            }
            
            if (Utils.validateCourse(courseId)) {
                Learner learner = new Learner(id, name, dateOfBirth, score, courseId);
                this.add(learner);
            }  
        }
    }
    System.out.println("Learners loaded from file successfully.");
}



}

