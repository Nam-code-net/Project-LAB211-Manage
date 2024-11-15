/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import sample.dto.Topic;
import sample.utils.FileUtils;
import sample.utils.Utils;


public class TopicList extends ArrayList<Topic> {
    private static final String TOPIC_FILE_PATH = "Topic.txt";
    
    
    private Topic findById(String id) {
        for (Topic product : this) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
    public void addTopic(){
         loadFromFile();
        int getchoice =9;
    do{
        String id;
        do{
         id = Utils.getString("Enter the Topic id: ");
        
        }while (findById(id) != null);
        
        String name = Utils.getString("Enter Topic Name: ");
        String type = Utils.regexString("Enter Topic Type (long term or short term): ","Invalid type. Please enter 'long term' or 'short term'" 
                , "^(long term|short term)");
        String title = Utils.getString("Enter Topic Title: ");
        int duration = Utils.getInt("Enter Duration: ", 1, Integer.MAX_VALUE);

        Topic newTopic = new Topic(id, name, type, title, duration);
        this.add(newTopic);
        System.out.println("Topic added successfully.");
        saveToFile();
        
        
         getchoice = Utils.getInt("Do you want to add more products? (yes: 1, no: 0)", 0, 1);
            if (getchoice == 0) {
                System.out.println("----------Back to main menu!!!-------------");
            }    
        
    }while(getchoice != 0);
}
    
   public void updateTopic() {
        loadFromFile();
       String inputId = Utils.getString("Enter Topic id: ");
       Topic topic = findById(inputId);
       
       if (topic == null){
           System.out.println("The topic does not exist");
       }else{
           System.out.println("Updating Topic with ID: " + inputId);
       }
       
       String newName = Utils.getString("Enter new Name (leave blank to keep unchanged): ", topic.getName());
       String newType = Utils.regexString("Enter Topic Type (long term or short term): ","Invalid type. Please enter 'long term' or 'short term'" 
                , "^(long term|short term)", topic.getType());
        String newTitle = Utils.getString("Enter the new Title (leave blank to keep unchanged): ", topic.getTitle());
        int newDuration = Utils.getInt("Enter the new Duration: ", 0, Integer.MAX_VALUE);
        
        topic.setName(newName);
        topic.setType(newType);
        topic.setTitle(newTitle);
        if (newDuration != 0){
            topic.setDuration(newDuration);
        }
        System.out.println("Topic updated successfully.");
        saveToFile();
               
}
   
    public void deleteTopic(){
         loadFromFile();
        String inputId = Utils.getString("Enter the Topic u want to delete: ");
        Topic topic = findById(inputId);
        
        if (topic == null){
            System.out.println("The Topic does not exist.");
            
        }else {
            boolean confirm = Utils.confirmYesNo("Do you want to delete this Topic? (Y/N) ");
            if (confirm){
                this.remove(topic);
                System.out.println("Topic deleted successfully");
                saveToFile();
            }else{
                System.out.println("Cancel the delete operation");
            }
        }
}
    
    public void displayAllTopics() {
        loadFromFile();
    this.sort(Comparator.comparing(Topic::getName));
    System.out.printf("+----------+----------------+----------------+----------------+----------+\n");
    System.out.printf("| ID       | Name           | Type           | Title          | Duration |\n");
    System.out.printf("+----------+----------------+----------------+----------------+----------+\n");

    for (Topic topic : this) {
        System.out.printf("| %-8s | %-14s | %-14s | %-14s | %-8d |\n",
                          topic.getId(), topic.getName(), topic.getType(), topic.getTitle(), topic.getDuration());
    }
    System.out.printf("+----------+----------------+----------------+----------------+----------+\n");
}
   
    public void searchTopicByName(){
        loadFromFile();
        String searchName = Utils.getString("Enter the name to search for: ");
        
        boolean found = false;
    System.out.printf("+----------+----------------+----------------+----------------+----------+\n");
    System.out.printf("| ID       | Name           | Type           | Title          | Duration |\n");
    System.out.printf("+----------+----------------+----------------+----------------+----------+\n");

    for (Topic topic : this) {
        if (topic.getName().toLowerCase().contains(searchName.toLowerCase())) {
            System.out.printf("| %-8s | %-14s | %-14s | %-14s | %-8d |\n",
                              topic.getId(), topic.getName(), topic.getType(), topic.getTitle(), topic.getDuration());
            found = true;
        }
    }

    if (!found) {
        System.out.println("No topics found matching the search criteria.");
    }
    System.out.printf("+----------+----------------+----------------+----------------+----------+\n");
}
   public void saveToFile() {
    List<String> lines = new ArrayList<>();
    
    for (Topic topic : this) {  // Assume topicsList is your collection of Topic objects
        String line = topic.getId() + "," + topic.getName() + "," + topic.getType() + "," 
                    + topic.getTitle() + "," + topic.getDuration();
        lines.add(line);
    }

    FileUtils.overwriteFile(TOPIC_FILE_PATH, lines);
    System.out.println("Topics saved to file successfully.");
}
   
   public void loadFromFile() {
    this.clear(); 
    List<String> lines = FileUtils.readFile(TOPIC_FILE_PATH);
    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length == 5) { 
            String id = parts[0];
            String name = parts[1];
            String type = parts[2];
            String title = parts[3];
            int duration = Integer.parseInt(parts[4]);
            
            Topic item = new Topic(id, name, type, title, duration);
            this.add(item);
        }
    }
    System.out.println("Topic items loaded from file successfully.");
}
   
}
