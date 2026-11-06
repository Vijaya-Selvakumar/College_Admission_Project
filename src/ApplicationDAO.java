import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApplicationDAO {

    //apply for a course
    public void applyForCourse(int student_id, int course_id, double merit_score ){
        String sql= "insert into Applications(student_id, course_id, merit_score, status) values(?,?,?,'pending')";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)){
                ps.setInt(1,student_id);
                ps.setInt(2,course_id);
                ps.setDouble(3, merit_score);

                ps.executeUpdate();

                System.out.println("Application submitted successflly!");               
            }catch(Exception e){
                System.out.println("Error submitting application!" + e.getMessage());
            }
    }
    // Display all Applications

    public void displayApplications(){
        String sql="Select a.application_id, s.student_name,c.course_name, a.merit_score, a.status from Applications a join Students s ON a.student_id = s.student_id join Courses c on a.course_id = c.course_id";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                System.out.println("\n=====Student Applications======");
                while (rs.next()) {
                  System.out.println("Application_Id: "+ rs.getInt("application_id")+", Student: "+ rs.getString("student_name")+", Course: "+rs.getString("course_name")+", Merit_score: "+ rs.getDouble("merit_score")+", Status: "+ rs.getString("status"));
                    
                }
            }catch(Exception e){
                System.out.println("Error displaying applications: "+ e.getMessage());
            }
    }
}
