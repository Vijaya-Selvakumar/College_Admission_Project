import java.util.Scanner;

public class AdminPanel {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        AdmissionService admissionService = new AdmissionService();
        ReportGenerator reportGenerator = new ReportGenerator();

        int choice = 0;

        do{
            System.out.println("\n==================================");
            System.out.println("COLLEGE ADMISSION MANAGEMENT SYSTEM");
            
            System.out.println("\n==================================");
            System.out.println("1. Register Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Display All Courses");
            System.out.println("4. Apply for a Course");
            System.out.println("5. Display All Applications");
            System.out.println("6. Approve Application");
            System.out.println("7. Reject Application");
            System.out.println("8. Generate Admission List CSV");
            System.out.println("9. Exit");
            System.out.println("\n==================================");

            System.out.println("Enter Your Choice: ");
            if(!sc.hasNextInt()){
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n---STUDENT REGISTRATION---");
                    System.out.println("Enter student name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.println("Enter Phone number: ");
                    String phone = sc.nextLine();
                    System.out.println("Enter marks: ");
                    if(!sc.hasNextDouble()){
                        System.out.println("Invaild marks. Pleae enter a number.");
                        sc.nextLine();
                        break;
                    }
                    double marks = sc.nextDouble();
                    sc.nextLine();
                     Student student = new Student(name, email, phone, marks);
                     studentDAO.registerStudent(student);
                    
                    break;

                case 2:
                    System.out.println("\n----REGISTERED STUDENTS----");
                    studentDAO.displayStudents();
                    break;

                case 3:
                    System.out.println("\n--- AVAILABLE COUSES---");
                     courseDAO.displayCourses();
                     break;

                case 4:
                    System.out.println("\n---APPLY FOR A COURSE---");
                    System.out.println("Enter student Id: ");
                    if(!sc.hasNextInt()){
                        System.out.println("Invalid student Id.");
                        sc.nextLine();
                        break;
                    }
                    int studentId = sc.nextInt();
                    
                    System.out.println("Enter course Id: ");
                    if(!sc.hasNextInt()){
                        System.out.println("Invalide Course Id.");
                        sc.nextLine();
                        break;
                    }
                    int courseId = sc.nextInt();

                    System.out.println("Enter merit score: ");
                    if(!sc.hasNextDouble()){
                        System.out.println("Invalid merit score.");
                        sc.nextLine();
                        break;
                    }
                    double meritScore = sc.nextDouble();
                    sc.nextLine();

                    applicationDAO.applyForCourse(studentId, courseId, meritScore);
                    break;

                case 5:
                    System.out.println("\n----ALL APPLICATIONS----");
                     
                    applicationDAO.displayApplications();
                    break;

                case 6:
                    System.out.println("\n-----APPROVE APPLICATION---");

                    System.out.println("Enter application Id to approve: ");
                    if(!sc.hasNextInt()){
                        System.out.println("Invalid application Id.");
                        sc.nextLine();
                        break;                    
                    }
                    int approveId = sc.nextInt();
                    sc.nextLine();
                    admissionService.processApplication(approveId, "Approve");
                    break;

                case 7:
                    System.out.println("\n---REJECT APPLICATION---");

                    System.out.println("Enter application Id to reject: ");
                    if(!sc.hasNextInt()){
                        System.out.println("Invalid application Id.");
                        sc.nextLine();
                        break;                    
                    }
                    int rejectId = sc.nextInt();
                    sc.nextLine();
                    admissionService.processApplication(rejectId, "Reject");
                    break;

                case 8:
                    System.out.println("\n---GENERATING AADMISSION REPORT---");

                    reportGenerator.generateAdmissionList();
                    break;

                case 9:
                    System.out.println("\nThank you for using the system!");
                    break;
            
                default:
                    System.out.println("Invalid choice. Please enter a number 1 to 9.");
                    break;
            }
        }while (choice!=9);

        sc.close();
                   
    }
        
}
