import java.util.Scanner;

public class StudentGradeCalculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        // Validate number of subjects
        if (numSubjects <= 0) {
            System.out.println("Error: Number of subjects must be greater than 0.");
            scanner.close();
            return;
        }
        
        // Array to store marks
        int[] marks = new int[numSubjects];
        
        // Input marks for each subject
        System.out.println("\nEnter marks obtained (out of 100) for each subject:");
        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Subject " + (i + 1) + ": ");
                int mark = scanner.nextInt();
                
                // Validate marks are between 0 and 100
                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    break;
                } else {
                    System.out.println("Error: Marks must be between 0 and 100. Please try again.");
                }
            }
        }
        
        // Calculate total marks
        int totalMarks = calculateTotalMarks(marks);
        
        // Calculate average percentage
        double averagePercentage = calculateAveragePercentage(totalMarks, numSubjects);
        
        // Determine grade
        String grade = calculateGrade(averagePercentage);
        
        // Display results
        displayResults(totalMarks, averagePercentage, grade, numSubjects);
        
        scanner.close();
    }
    
    /**
     * Calculates the total marks obtained in all subjects
     * @param marks Array of marks obtained in each subject
     * @return Total marks
     */
    public static int calculateTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }
    
    /**
     * Calculates the average percentage based on total marks and number of subjects
     * @param totalMarks Total marks obtained
     * @param numSubjects Number of subjects
     * @return Average percentage
     */
    public static double calculateAveragePercentage(int totalMarks, int numSubjects) {
        return (double) totalMarks / numSubjects;
    }
    
    /**
     * Assigns a grade based on the average percentage
     * @param averagePercentage Average percentage achieved
     * @return Grade as a string
     */
    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "Fail";
        }
    }
    
    /**
     * Displays the calculated results to the user
     * @param totalMarks Total marks obtained
     * @param averagePercentage Average percentage
     * @param grade Grade assigned
     * @param numSubjects Number of subjects
     */
    public static void displayResults(int totalMarks, double averagePercentage, String grade, int numSubjects) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           GRADE CALCULATION RESULTS");
        System.out.println("=".repeat(50));
        System.out.printf("Total Marks Obtained:     %d / %d%n", totalMarks, numSubjects * 100);
        System.out.printf("Average Percentage:       %.2f%%%n", averagePercentage);
        System.out.printf("Grade:                    %s%n", grade);
        System.out.println("=".repeat(50));
    }
}

