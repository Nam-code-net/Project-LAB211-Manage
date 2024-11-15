/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hd
 */
public class Utils {

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
    public static int getAnInteger(String inputMsg, String errorMsg, int min, int max) {
        int n;
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static String regexString(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toLowerCase();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMsg);
            } else {
                return id.toLowerCase();
            }
        }
    }
    public static String regexString(String inputMsg, String errorMsg, String format, String oldData) {
        String id;
        boolean match;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toLowerCase();
            match = id.matches(format);
            if(id.length() == 0 || id.isEmpty()){
                return oldData;
            }else if ( match == false)
            {
                System.out.println(errorMsg);
                
            } else {
                return id.toLowerCase();
            }
        }
    }
    public static boolean validateTopic(String TopicId) {
       
        List<String> TopicList = FileUtils.readFile("Topic.txt");
        for (String Topic : TopicList) {
            String[] topicData = Topic.split(",");
            if ((topicData[0]).equalsIgnoreCase(TopicId) ) {
                return true;
            }
        }
        return false;
    }
    public static boolean validateCourse(String CourseId) {
       
        List<String> CourseList = FileUtils.readFile("Course.txt");
        for (String Course : CourseList) {
            String[] courseData = Course.split(",");
            if ((courseData[0]).equalsIgnoreCase(CourseId) ) {
                return true;
            }
        }
        return false;
    }
    public static double getDouble(String prompt, double min, double max) {  // Check double , dùng cho nhập tiền $
        double number = 0;
        boolean isValid = false;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println(prompt);
                number = Double.parseDouble(sc.nextLine());

                if (number >= min && number <= max) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (!isValid);

        return number;
    }
    
}
