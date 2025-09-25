//Create the actual nodes (LLnode)

public class Student {
    private String courseNum;
    private int studentID;

    private String lastName;
    private String firstName;
    private int[] examGrades = new int[3];
    private double finalGrade;
    private char letterGrade;
    private static double averageGrade;
    private static double highestGrade;
    private static double lowestGrade;

    private static int numStudents = 0;

    // Pointer to the next student in the linked list
    private Student next;

    // Constructors
    public Student(String courseNum, int studentID, String firstName, String lastName, int[] examGrades) {
        this.courseNum = courseNum;
        this.studentID = studentID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.examGrades = examGrades;
        FinalAndLetterGrades();
        numStudents++;

        this.next = null; // Initialize next to null
    }




    //Accessors
    public Student getNext() {
        return next;
    }

    //Mutators
    public void setNext(Student next) {
        this.next = next;
    }


    public void FinalAndLetterGrades() {
        // Exam 1 is worth 30%, Exam 2 is worth 30%, and the Final Exam is worth 40%.
        //Traverse through the examGrades array and calculate the final grade
        double sum = examGrades[0] * 0.3 + examGrades[1] * 0.3 + examGrades[2] * 0.4;
        finalGrade = sum;

        //Use sum to find the letter grade
        if (finalGrade >= 90) {
            letterGrade = 'A';
        }
        else if (finalGrade >= 80) {
            letterGrade = 'B';
        }
        else if (finalGrade >= 70) {
            letterGrade = 'C';
        }
        else if (finalGrade >= 60) {
            letterGrade = 'D';
        }
        else {
            letterGrade = 'F';
        }
    }



//    Course
    public int getCourseNum() {
        return studentID;
    }
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

//    Student ID
    public int getStudentID() {
        return studentID;
    }
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

//    Last and First Names
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    Exam Grades
    public int[] getExamGrades() {
        return examGrades;
    }
    public void setExamGrades(int[] examGrades) {
        this.examGrades = examGrades;
    }

//    Final Grade
    public double getFinalGrade() {
        return finalGrade;
    }
    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

//   Letter Grade
    public char getLetterGrade() {
        return letterGrade;
    }
    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }

//   Get Num Students
    public static int getNumStudents() {
        return numStudents;
    }

//  Average Grade
    public static double getAverageGrade() {
        return averageGrade;
    }

    public static void setAverageGrade(double averageGrade) {
        Student.averageGrade = averageGrade;
    }

    public static double getHighestGrade() {
        return highestGrade;
    }

    public static void setHighestGrade(double highestGrade) {
        Student.highestGrade = highestGrade;
    }

    public static double getLowestGrade() {
        return lowestGrade;
    }

    public static void setLowestGrade(double lowestGrade) {
        Student.lowestGrade = lowestGrade;
    }
}
