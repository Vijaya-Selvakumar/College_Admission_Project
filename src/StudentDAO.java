import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    //Add students
    public void registerStudent(Student student){
        String sql = "Insert into Students (student_name, email,phone,marks) Values(?,?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1,student.getName());
                ps.setString(2,student.getEmail());
                ps.setString(3,student.getPhone());
                ps.setDouble(4,student.getMarks());

                int rows = ps.executeUpdate();

                if(rows>0){
                    System.out.println("Student Registered Successfully!");
                }
            } catch(SQLException e){
                System.out.println("Registration failed: " + e.getMessage());
            } catch(Exception e){
                System.out.println("Database connection failed: " + e.getMessage());
            }
    }

    //displaying all students
    public void displayStudents(){
        String sql = "Select * from Students";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

                System.out.println("\n===Registered Students===");

                while (rs.next()) {
                    System.out.println("StudentID: "+ rs.getInt("student_id")+", Name: "+ rs.getString("student_name")+", Email: "+ rs.getString("email")+", Phone: "+rs.getString("phone")+", Marks: "+ rs.getDouble("marks"));                 
                }
            }catch(Exception e){
                System.out.println("Error Displaying students: "+ e.getMessage());
            }

    }
    
}
