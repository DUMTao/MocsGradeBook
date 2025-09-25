//Linked List of Students (Linked List)

public class FSCcourseRoster {
    public Student getHead() {
        return head;
    }

    private Student head; // Pointer to the head of the linked list
    private String courseNumber;

    // CONSTRUCTORS
    public FSCcourseRoster() {
        this.head = null; // Initialize head to null
        this.courseNumber = "";
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
    public String getCourseNumber() {
        return courseNumber;
    }

    public boolean searchID(int studentID) {
        return searchID(head, studentID);
    }

    private boolean searchID(Student head, int studentID) {
        //Create the helpPtr
        Student helpPtr = head;

        //Check if the "next" of helpPtr isn't null and that if student ID matches, return it
        while (helpPtr != null) {
            if (helpPtr.getStudentID() == studentID) {
                return true;
            }
            helpPtr = helpPtr.getNext(); // Save the address of that node
        }

        return false; //If this gets printed, studentID wasn't found.
    }

    public boolean searchName(String firstName, String lastName) {
        return searchName(head, firstName, lastName);
    }

    private boolean searchName(Student head, String firstName, String lastName) {
        Student helpPtr = head;

        //Check if the "next" of helpPtr isn't null and that if student ID matches, return it
        while (helpPtr != null) {
            String studentF = helpPtr.getFirstName();
            String studentL = helpPtr.getLastName();

            if (studentF.equals(firstName.trim()) && studentL.equals(lastName.trim())) {
                return true;
            }
            helpPtr = helpPtr.getNext(); // Save the address of that node
        }

        return false; //If this gets printed, studentID wasn't found.
    }

    public void insert(Student tempStudent) {
        head = insert(head, tempStudent);
    }

    private Student insert(Student head, Student tempStudent) {
        //Check if the list is not empty
        if (head == null || head.getStudentID() > tempStudent.getStudentID()) {
            tempStudent.setNext(head);
            return tempStudent;
        }
        //If the list isn't empty, traverse it
        Student helpPtr = head;

        while (helpPtr.getNext() != null) {
            if (helpPtr.getNext().getStudentID() > tempStudent.getStudentID()) {
                tempStudent.setNext(helpPtr.getNext());
                helpPtr.setNext(tempStudent);
                return head;
            }
            helpPtr = helpPtr.getNext();
        }
        //Create the new node and set it to the successor of the node
        helpPtr.setNext(tempStudent);
        return head;
    }

    //To find the ID
    public Student findNode(int studentID) {
        return findNode(head, studentID);
    }

    private Student findNode(Student head, int studentID) {
        Student helpPtr = head;

        //Check that helpPtr isn't null and that the studentID doesn't match
        while (helpPtr != null && helpPtr.getStudentID() != studentID) {
            helpPtr = helpPtr.getNext(); //Save the address of that node
        }

        //Return the address of the node or null if not found
        return helpPtr;

    }

    //To find the Name
    public Student findNodeName(String firstName, String lastName) {
        return findNodeName(head, firstName, lastName);
    }

    private Student findNodeName(Student head, String firstName, String lastName) {
        //helper!
        Student helpPtr = head;

        //Traverse the list until null and check if both the names match
        while (helpPtr != null){
            if (helpPtr.getFirstName().equals(firstName) && helpPtr.getLastName().equals(lastName)){
                //Return the address of that node
                return helpPtr;
            }
            //Increase!!
            helpPtr = helpPtr.getNext();
        }

        //If the name wasn't found, null it is
        return null;
    }

    public void delete(int studentID) {
        head = delete(head, studentID);
    }

    private Student delete(Student head, int studentID) {
        //Check if the list isn't empty, since only then can we delete
        if (!isEmpty()) {
            if (head.getStudentID() == studentID) {
                head = head.getNext();
            } else {
                Student helpPtr = head;
                //Traverse the list until student ID is found or until null
                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getStudentID() == studentID) {
                        helpPtr.setNext(helpPtr.getNext().getNext());
                        break; //Leave the loop once node is deleted
                    }
                    helpPtr = helpPtr.getNext(); //Move to the next node
                }
            }
            //Return the possibly updated head of the list
            return head;
        }
        return head;
    }


    //Printing methods here
    public void printStats() {
        printStats(head);
    }

    private void printStats(Student head) {
        int totalA = 0;
        int totalB = 0;
        int totalC = 0;
        int totalD = 0;
        int totalF = 0;
        double totalGrade = 0.0;
        double highestGrade = Double.MIN_VALUE;
        double lowestGrade = Double.MAX_VALUE;

        int numStudents = 0;

        Student helpPtr = head;

        while (helpPtr != null) {
            double finalGrade = helpPtr.getFinalGrade();
            totalGrade += finalGrade;

            char letterGrade = helpPtr.getLetterGrade();
            //Check the letter grade and increment the counter
            switch (letterGrade) {
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

            //Check for highest and lowest score
            if (finalGrade > highestGrade) {
                highestGrade = finalGrade;
            }
            if (finalGrade < lowestGrade) {
                lowestGrade = finalGrade;
            }
            //Don't forget to increment :))
            helpPtr = helpPtr.getNext();
            numStudents++;
        }

        if (numStudents == 0) {
            System.out.printf("\tTotal number of student records: %d\n", numStudents);
            System.out.printf("\tAverage Score: %.2f\n", 0.0);
            System.out.printf("\tHighest Score: %.2f\n", 0.0);
            System.out.printf("\tLowest Score:  %.2f\n", 0.0);
            //Just combine the percentage stuff into the line itself (don't feel like making a new variable for it :pp)
            System.out.printf("\tTotal 'A' Grades: %d (%.2f%% of class)\n", 0, 0.00);
            System.out.printf("\tTotal 'B' Grades: %d (%.2f%% of class)\n", 0, 0.00);
            System.out.printf("\tTotal 'C' Grades: %d (%.2f%% of class)\n", 0, 0.00);
            System.out.printf("\tTotal 'D' Grades: %d (%.2f%% of class)\n", 0, 0.00);
            System.out.printf("\tTotal 'F' Grades: %d (%.2f%% of class)\n\n", 0, 0.00);
            return;
        }

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


    public void printRoster() {
        printRoster(head);
    }

    private void printRoster(Student head) {
        Student helpPtr = head;
        System.out.printf("Course Roster for %s:\n", getCourseNumber());

        while (helpPtr != null) {
            System.out.printf("\t%s %s (ID# %d):\n",
                    helpPtr.getFirstName(),
                    helpPtr.getLastName(),
                    helpPtr.getStudentID());
            System.out.printf("\t\tExam 1:       %d\n", helpPtr.getExamGrades()[0]);
            System.out.printf("\t\tExam 2:       %d\n", helpPtr.getExamGrades()[1]);
            System.out.printf("\t\tFinal Exam:   %d\n", helpPtr.getExamGrades()[2]);
            System.out.printf("\t\tFinal Grade:  %.2f\n", helpPtr.getFinalGrade());
            System.out.printf("\t\tLetter Grade: %c\n", helpPtr.getLetterGrade());

            //Don't forget to increase :))
            helpPtr = helpPtr.getNext();
        }
    }
}