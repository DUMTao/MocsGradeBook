import java.util.*;

public class Main {
    private static FSCcourseRoster[] courses;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //Create the current # of courses and reverence array for the roster
        int numCourses = Integer.parseInt(scanner.nextLine());
        courses = new FSCcourseRoster[numCourses];


        //Loop to fill the reference arr and create a new obj per index
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new FSCcourseRoster();
            courses[i].setCourseNumber(scanner.nextLine());
        }

        System.out.println("\nWelcome to the FSC Grade Book.\n");

        //Print the courses that were added to the system
        System.out.println("The following course(s) have been added to the database:");
        for (FSCcourseRoster cours : courses) {
            String temp = cours.getCourseNumber().trim();

            // Only print if it looks like a valid course code
            if (temp.matches("CSC-\\d{4}")) {
                System.out.printf("\t%s\n", temp);
            }
        }
        System.out.println();

        //Make a while loop to iterate through the methods
        while (true){
            String[] userCmd = scanner.nextLine().trim().split(" ");

            if (userCmd[0].equals("ADDRECORD")){
                System.out.println("Command: AddRecord");
                AddRecord(userCmd, courses);
            }

            else if (userCmd[0].equals("DELETERECORD")){
                System.out.println("Command: DeleteRecord");
                DeleteRecord(userCmd, courses);
            }

            else if (userCmd[0].equals("SEARCHBYID")){
                System.out.println("Command: SearchByID");
                SearchByID(userCmd, courses);
            }

            else if (userCmd[0].equals("SEARCHBYNAME")){
                System.out.println("Command: SearchByName");
                SearchByName(userCmd, courses);
            }


            else if (userCmd[0].equals("DISPLAYSTATS")){
                System.out.printf("Command: DisplayStats (%s)\n", userCmd[1]);
                displayStats(userCmd);
            }

            else if (userCmd[0].equals("DISPLAYSTUDENTS")){
                System.out.printf("Command: DisplayStudents (%s)\n", userCmd[1]);
                displayStudents(userCmd);
            }

            else if (userCmd[0].equals("QUIT")){
                System.out.println("Command: QUIT");
                System.out.println("Thank you for using the FSC Grade Book.\n");
                System.out.println("Goodbye.");
            }

        }

    }

    //Receives: String, stdID, firstN, lastN, intgrd1, intgrd2, intgrd3
    public static void AddRecord(String[] userCmd, FSCcourseRoster[] courses){
        //Create a student obj and assign the parameters to the obj
        Student tempStudent = new Student(userCmd[1], //Thats a long student parse :((
                Integer.parseInt(userCmd[2]),
                userCmd[3], userCmd[4],
                new int[]{Integer.parseInt(userCmd[5]),
                        Integer.parseInt(userCmd[6]),
                        Integer.parseInt(userCmd[7])});

        //Final and Letter grade should be calculated and saved as well
        //Exam 1 is worth 30%, Exam 2 is worth 30%, and the Final Exam is worth 40%.
        tempStudent.FinalAndLetterGrades();

        boolean foundCourse = false;
        //Insert the obj into the appropriate courseID linked-list
        for (int i = 0; i < courses.length; i++){
            //Check if the course number exists
            if (courses[i].getCourseNumber().equals(userCmd[1].trim())){
                foundCourse = true;
                //Check if the student doesn't already exist
                if (!courses[i].searchID(tempStudent.getStudentID()) && !courses[i].searchName(tempStudent.getFirstName(), tempStudent.getLastName())){
                    //Insert the student obj into the linked list
                    courses[i].insert(tempStudent);
                    System.out.printf("\t%s %s (ID# %d) has been added to %s.\n",
                            tempStudent.getFirstName(),
                            tempStudent.getLastName(),
                            tempStudent.getStudentID(),
                            courses[i].getCourseNumber());
                    System.out.printf("\tFinal Grade: %.2f (%c)\n\n",
                            tempStudent.getFinalGrade(),
                            tempStudent.getLetterGrade());
                }
            }
        }
        //Else, if the course doesn't exist = error message
        if (!foundCourse) {
            System.out.printf("ERROR: cannot add student. Course \"%s\" does not exist.\n\n", userCmd[1]);
        }
    }


    //Receives: student ID, x > 0
    public static void DeleteRecord(String[] userCmd, FSCcourseRoster[] courses){
        int studentID = Integer.parseInt(userCmd[1]);
        //Find the student ID by traversing the linked list



        //Delete the student obj from ALL the courses they're registered in
        //If ID not found, print error message

    }


    //Receives: Student ID
    public static void SearchByID(String[] userCmd, FSCcourseRoster[] courses){
        int studentID = Integer.parseInt(userCmd[1]);
        boolean foundStudent = false;
        //Loop courses by the number of courses not the array length
        for (int i = 0; i < courses.length; i++){
            //Check if that student ID exists in that course
            if (courses[i].searchID(studentID)){
                foundStudent = true;
                Student tempStudent = courses[i].findNode(studentID);

                //print thingies
                System.out.printf("Student Record for %s %s (ID# %d):\n",
                        tempStudent.getFirstName(),
                        tempStudent.getLastName(),
                        studentID);
                System.out.printf("\tCourse: %s\n", courses[i].getCourseNumber());
                System.out.printf("\t\tExam 1:       %d\n", tempStudent.getExamGrades()[0]);
                System.out.printf("\t\tExam 2:       %d\n", tempStudent.getExamGrades()[1]);
                System.out.printf("\t\tFinal Exam:   %d\n", tempStudent.getExamGrades()[2]);
                System.out.printf("\t\tFinal Grade:  %.2f\n", tempStudent.getFinalGrade());
                System.out.printf("\t\tLetter Grade: %c\n\n", tempStudent.getLetterGrade());
            }
        }

        //If the studentID doesn't match in any course, error!
        if (!foundStudent){
            System.out.printf("ERROR: there is no record for student ID# %d.\n\n", studentID);
        }

    }


    //Receives: Student first and last name
    public static void SearchByName(String[] userCmd, FSCcourseRoster[] courses){
        String fName = userCmd[2];
        String lName = userCmd[1];
        boolean foundStudent = false;

        //Loop courses by the number of courses
        for (int i = 0; i < courses.length; i++){
            //Check if that student name exists in that course
            if (courses[i].searchName(fName, lName)){
                foundStudent = true;
                Student tempStudent = courses[i].findNodeName(fName, lName);

                //print thingies
                System.out.printf("Student Record for %s %s (ID# %d):\n",
                        tempStudent.getFirstName(),
                        tempStudent.getLastName(),
                        tempStudent.getStudentID());
                System.out.printf("\tCourse: %s\n", courses[i].getCourseNumber());
                System.out.printf("\t\tExam 1:       %d\n", tempStudent.getExamGrades()[0]);
                System.out.printf("\t\tExam 2:       %d\n", tempStudent.getExamGrades()[1]);
                System.out.printf("\t\tFinal Exam:   %d\n", tempStudent.getExamGrades()[2]);
                System.out.printf("\t\tFinal Grade:  %.2f\n", tempStudent.getFinalGrade());
                System.out.printf("\t\tLetter Grade: %c\n\n", tempStudent.getLetterGrade());
            }
        }

        if (!foundStudent) {
            System.out.printf("ERROR: there is no record for student \"%s %s\".\n\n", fName, lName);
        }
    }


    //Receives: Course Number or "ALL"
    public static void displayStats(String[] userCmd) {
        boolean hasStudent = false;
        int totalA = 0;
        int totalB = 0;
        int totalC = 0;
        int totalD = 0;
        int totalF = 0;
        int numStudents = 0;
        double totalGrade = 0.0;

        double highestGrade = Double.MIN_VALUE;
        double lowestGrade = Double.MAX_VALUE;

        //Check if the courses are empty
        if (courses == null || courses.length == 0) {
            System.out.printf("ERROR: there are no student records for %s.\n, courseNum");
            return;
        }

        if (userCmd[1].equals("ALL")) {
            System.out.printf("Statistical Results for %s Courses:\n", userCmd[1]);

            //Loop through all the courses and add the stats
            for (int i = 0; i < courses.length; i++){
                if (!courses[i].isEmpty()){
                    //Get the pointer
                    Student helpPtr = courses[i].getHead();

                    //Traverse the linked list
                    while (helpPtr != null){
                        totalGrade += helpPtr.getFinalGrade();
                        numStudents++;

                        //Update the letter counts
                        switch (helpPtr.getLetterGrade()){
                            case 'A':
                                totalA++;
                                break;
                            case 'B':
                                totalB++;
                                break;
                            case 'C':
                                totalC++;
                                break;
                            case 'D':
                                totalD++;
                                break;
                            case 'F':
                                totalF++;
                                break;
                        }

                        //Check for the high and lows
                        if (helpPtr.getFinalGrade() > highestGrade){
                            highestGrade = helpPtr.getFinalGrade();
                        }
                        if (helpPtr.getFinalGrade() < lowestGrade){
                            lowestGrade = helpPtr.getFinalGrade();
                        }

                        //Don't forget to increment again! :))
                        helpPtr = helpPtr.getNext();
                    }
                    hasStudent = true;
                }
            }

            if (!hasStudent) {
                System.out.printf("\tTotal number of student records: %d\n", 0);
                System.out.printf("\tAverage Score: %.2f\n", 0.0);
                System.out.printf("\tHighest Score: %.2f\n", 0.0);
                System.out.printf("\tLowest Score:  %.2f\n", 0.0);
                System.out.printf("\tTotal 'A' Grades: %d (%.2f%% of class)\n", 0, 0.00);
                System.out.printf("\tTotal 'B' Grades: %d (%.2f%% of class)\n", 0, 0.00);
                System.out.printf("\tTotal 'C' Grades: %d (%.2f%% of class)\n", 0, 0.00);
                System.out.printf("\tTotal 'D' Grades: %d (%.2f%% of class)\n", 0, 0.00);
                System.out.printf("\tTotal 'F' Grades: %d (%.2f%% of class)\n", 0, 0.00);
                System.out.println();
            }
            else {
                double avg = totalGrade / numStudents;
                System.out.printf("\tTotal number of student records: %d\n", numStudents);
                System.out.printf("\tAverage Score: %.2f\n", avg);
                System.out.printf("\tHighest Score: %.2f\n", highestGrade);
                System.out.printf("\tLowest Score:  %.2f\n", lowestGrade);
                //Just combine the percentage stuff into the line itself (don't feel like making a new variable for it :pp)
                System.out.printf("\tTotal 'A' Grades: %d (%.2f%% of class)\n", totalA, (totalA * 100.0) / numStudents);
                System.out.printf("\tTotal 'B' Grades: %d (%.2f%% of class)\n", totalB, (totalB * 100.0) / numStudents);
                System.out.printf("\tTotal 'C' Grades: %d (%.2f%% of class)\n", totalC, (totalC * 100.0) / numStudents);
                System.out.printf("\tTotal 'D' Grades: %d (%.2f%% of class)\n", totalD, (totalD * 100.0) / numStudents);
                System.out.printf("\tTotal 'F' Grades: %d (%.2f%% of class)\n\n", totalF, (totalF * 100.0) / numStudents);
            }
        }
        else {
            System.out.printf("Statistical Results for %s Courses:\n", userCmd[1]);

            //Only Print that course
            for (int i = 0; i < courses.length; i++) {
                if (courses[i].getCourseNumber().equals(userCmd[1])) {
                    courses[i].printStats();
                }
            }
        }
    }

    //Receives: Course Number or "ALL"
    public static void displayStudents(String[] userCmd){
        boolean hasStudent = false;

        if (userCmd[1].equals("ALL")) {
            //Print the stats for each course
            for (int i = 0; i < courses.length; i++) {
                if (!courses[i].isEmpty()) {
                    courses[i].printRoster();
                    hasStudent = true;
                }
            }
            if (!hasStudent) {
                System.out.println("\tERROR: there are no students currently is the system.\n");
            }
        }
        else {
            //Only Print that course
            for (int i = 0; i < courses.length; i++) {
                if (courses[i].getCourseNumber().equals(userCmd[1])) {
                    courses[i].printRoster();
                }
            }
        }
    }
}



//Modify the insert code and send the address of the new student
//Set the tempStudent to where head is pointing
//make head equal to