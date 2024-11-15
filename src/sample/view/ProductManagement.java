package sample.view;

import sample.dto.I_List;
import sample.dto.I_Menu;
import sample.controllers.Menu;
import sample.controllers.ProductList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
public class ProductManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("1. Manager the Topics");
        menu.addItem("2. Manager the Course");
        menu.addItem("3. Manager the Learner");
        menu.addItem("4. Search information");
        menu.addItem("5. Load and Save information");
        menu.addItem("6. Quit");
        int choice;
        boolean cont = false;
        I_List list = new ProductList();
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.manageTopic();
                    break;
                case 2:
                    list.manageCourse();
                    break;
                case 3:
                    list.manageLearner();
                    break;
                case 4:
                    list.search();
                     break;
                case 5:
                    list.save();
                    break;
                case 6:
                    cont = menu.confirmYesNo("Do you want to quit?(Y/N)");
                    break;
            }
        } while (!cont);
    }
}
