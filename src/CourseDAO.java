import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseDAO {

    //Add a new Course

    public void addCourse(Course course){
        String sql = "Insert into Courses(course_name, total_seats, cutoff_marks) Values(?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

                ps.setString(1,course.getCourseName());
                ps.setInt(2,course.getTotalSeats());
                ps.setDouble(3,course.getCutOffMarks());
               
                int rows = ps.executeUpdate();

                if(rows>0){
                    System.out.println("Course Added Successfully!");
                }

            }catch(SQLException e){
                System.out.println("Error adding courses: " + e.getMessage());
            } catch(Exception e){
                System.out.println("Database connection failed: " + e.getMessage());
            }
    }

    //Display all courses

    public void displayCourses(){
        String sql = "Select * from Courses";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

                System.out.println("\n===Available Courses====");

                while (rs.next()) {
                   System.out.println("Course ID: "+rs.getInt("course_id")+", Course Name: "+rs.getString("course_name")+", Total Seats: "+rs.getInt("total_seats")+", CutOffMarks: "+rs.getDouble("cutoff_marks"));                   
                }
            }catch(Exception e){
                System.out.println("Error Displaying Courses: "+ e.getMessage());
            }
    }
    
}
