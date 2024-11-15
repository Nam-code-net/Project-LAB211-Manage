/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.util.Scanner;
import sample.dto.I_List;
import sample.utils.Utils;

/**
 *
 * @author VICTUS
 */
public class ProductList implements I_List {
    private final TopicList topics = new TopicList();
    private final CourseList courses = new CourseList();
    private final LearnerList learners = new LearnerList();
    @Override
    public void manageTopic() {
        boolean cont = false;
        Scanner sc = new Scanner(System.in);
        while(!cont){
              System.out.println("------ manageTopic-------"); 
            System.out.println("1. Add Topics to catalog");
            System.out.println("2. Update Topic");
            System.out.println("3. Delete Topic");
            System.out.println("4. Display all Topics");
            System.out.println("5. Quit");
            
            int choice = sc.nextInt();
            
            switch(choice){
                case 1: 
                    topics.addTopic();
                    break;
                case 2:
                    topics.updateTopic();
                    break;
                case 3:
                    topics.deleteTopic();
                    break;
                case 4:
                    topics.displayAllTopics();
                    break;
                case 5:
                    cont = Utils.confirmYesNo("Do you want to quit: (Y/N) ");
                    break;
                default:
                    System.out.println("Invalid input!!! Please enter again ");
                    
            }
        }
    }

    @Override
    public void manageCourse() {
        boolean cont = false;
        Scanner sc = new Scanner(System.in);
        while(!cont){
            System.out.println("------ manageCourse-------"); 
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Display Course information");
            
            System.out.println("5. Quit");
            
            int choice = sc.nextInt();
            
            switch(choice){
                case 1: 
                    courses.addCourse();
                    break;
                case 2:
                    courses.updateCourse();
                    break;
                case 3:
                    courses.deleteCourse();
                    break;
                case 4:
                    courses.displayCourses();
                    break;
                case 5:
                    cont = Utils.confirmYesNo("Do you want to quit: (Y/N) ");
                    break;
                default:
                    System.out.println("Invalid input!!! Please enter again ");
                    
            }
        }
    }

    @Override
    public void manageLearner() {
       boolean cont = false;
        Scanner sc = new Scanner(System.in);
        while(!cont){
            System.out.println("------ manageLearner-------"); 
            System.out.println("1. Add Learner to Course");
            System.out.println("2. Enter scores for learners");
            System.out.println("3. Display Learner information");
            System.out.println("4. Quit");
            
            int choice = sc.nextInt();
            
            switch(choice){
                case 1: 
                    learners.addLearner();
                    break;
                case 2:
                    learners.enterScore();
                    break;
                case 3:
                    learners.displayAllLearners();
                    break;
                
                case 4:
                    cont = Utils.confirmYesNo("Do you want to quit: (Y/N) ");
                    break;
                default:
                    System.out.println("Invalid input!!! Please enter again ");
                    
            }
        }
    }

    @Override
    public void search() {
       boolean cont = false;
        Scanner sc = new Scanner(System.in);
        while(!cont){
            System.out.println("------ Search-------"); 
            System.out.println("1. .Search Topic");
            System.out.println("2. searchCourseByTopic");
            System.out.println("3. searchCourseByName");
            System.out.println("4. Quit");
            
            int choice = sc.nextInt();
            
            switch(choice){
                case 1: 
                    topics.searchTopicByName();
                    break;
                case 2:
                    courses.searchCourseByTopic();
                    break;
                case 3:
                    courses.searchCourseByName();
                    break;
                
                case 4:
                    cont = Utils.confirmYesNo("Do you want to quit: (Y/N) ");
                    break;
                default:
                    System.out.println("Invalid input!!! Please enter again ");
                    
            }
        }
    }
    @Override
    public void save(){
        topics.loadFromFile();
        topics.saveToFile();
        courses.loadFromFile();
        courses.saveToFile();
        learners.loadFromFile();
        learners.savetoFile();
                
    }

   
    
    
    
    
}
