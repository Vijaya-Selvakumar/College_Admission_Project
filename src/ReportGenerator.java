import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ReportGenerator {

    // Generate the admission list

    public void generateAdmissionList(){
      String sql = "Select a.application_id, s.student_id, s.student_name,s.email, c.course_name, a.merit_score, a.status from Applications a join Students s on a.student_id = s.student_id join Courses c on a.course_id = c.course_id where a.status = 'Approved' order by c.course_name, a.merit_score desc";

      try(Connection con = DBConnection.getConnection();
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            PrintWriter writer = new PrintWriter(new FileWriter("admission_list.csv"))){

    // Write column Headings
              writer.println("Appliation ID, Student ID,Student Name, Email, Course Name, Merit Score, Status");

    // Read each approved applications
               while (rs.next()) {
                int applicationId = rs.getInt("application_id");
                int studentId = rs.getInt("student_id");
                String name = csv(rs.getString("student_name"));
                String email = csv(rs.getString("email"));
                String courseName = csv(rs.getString("course_name"));
                double meritScore = rs.getDouble("merit_score");
                String status = rs.getString("status");

    // Write Applicaton details to the CSV file

                writer.println(applicationId + "," + studentId + "," + name + "," + email + "," + courseName + "," + meritScore + "," + status);
                
               }
               
               System.out.println("Admission list gernerated successfully!");
               System.out.println("File location: admission_list.csv");

            }catch(Exception e){
                System.out.println("Error generating admission list: " + e.getMessage());
            }
    }

    // Handles  commas and quotation marks in csv values

    private String csv(String value){
        if (value == null){
            return "";
        }
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
    
}
