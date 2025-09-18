import java.util.*;

public class Main {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //Create the current # of courses and reverence array for the roster
        int numCourses = Integer.parseInt(scanner.nextLine());
        FSCcourseRoster[] courses = new FSCcourseRoster[numCourses];

        //Loop to fill the reference arr and create a new obj per index
        for (int i = 0; i < numCourses; i++) {
//            courses[i] = new FSCcoursesRoster();
//            courses[i].setCourseNumber(input.nextLine());
        }

        //Make a while loop to iterate through the methods
    }

    //Receives: String, stdID, firstN, lastN, intgrd1, intgrd2, intgrd3
    public static void AddRecord(){
        //Create a student obj and assign the parameters to the obj

        //Final and Letter grade should be calculated and saved as well
        //Exam 1 is worth 30%, Exam 2 is worth 30%, and the Final Exam is worth 40%.

        //Insert the obj into the appropriate courseID linked-list

    }

    //Receives: student ID, x > 0
    public static void DeleteRecord(){
        //Find the student ID by traversing the linked list

        //Delete the student obj from ALL the courses they're registered in
        //If ID not found, print error message

    }

    //Receives: Student ID
    public static void SearchByID(){
        //Loop courses by the number of courses not the array length

    }


    //Receives: Student first and last name
    public static void SearchByName(){
        //
    }

}


//Modify the insert code and send the address of the new student
//Set the tempStudent to where head is pointing
//make head equal to