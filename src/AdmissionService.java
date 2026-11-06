import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdmissionService {

    //Approve or reject a student Application

    public void processApplication(int application_id, String decision){
           String sql = "SELECT a.status, a.merit_score, c.cutoff_marks, c.total_seats, c.course_id From Applications a JOIN Courses c ON a.course_id = c.course_id WHERE a.application_id= ?";

           try(Connection con = DBConnection.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)){
                ps.setInt(1, application_id);

                try(ResultSet rs = ps.executeQuery()){
                    if (!rs.next()){
                        System.out.println("Application not found!");
                        return ;
                    }
                     String status = rs.getString("status");
                     double merit_score = rs.getDouble("merit_score");
                     double cutoff_marks = rs.getDouble("cutoff_marks");
                     int total_seats = rs.getInt("total_seats");
                     int course_id = rs.getInt("course_id");

    //Check application status

                     if(!status.equalsIgnoreCase("pending")){
                        System.out.println("Application already processed");
                        return ;
                     }

    //check cutoff marks before approval
                      if(decision.equalsIgnoreCase("Approve")){

                        if(merit_score<cutoff_marks){
                            System.out.println("Cannot approve: student does not meet cutoff marks");
                            return;

                        }

    //check available seats
                
               String seatsql = "Select count(*) from Applications where course_id = ? and status = 'Approved'";
               try(PreparedStatement seatps = con.prepareStatement(seatsql)){
                seatps.setInt(1,course_id);

                try(ResultSet seatrs = seatps.executeQuery()){
                    seatrs.next();
                    if(seatrs.getInt(1)>=total_seats){
                        System.out.println("No seats available!");
                        return ;
                    }
                 }
               }
                        
            } else if (!decision.equalsIgnoreCase("Reject")){
                System.out.println("Invalid decision!");
                return;
            }

        }
        //Update application status
        String updatesql = "update Applications set status = ?" + "where application_id = ? and status='pending'";

        try(PreparedStatement updateps= con.prepareStatement(updatesql)){
            String newStatus = decision.equalsIgnoreCase("Approve")?"Approved" : "Rejected";

            updateps.setString(1, newStatus);
            updateps.setInt(2, application_id);

            int rows = updateps.executeUpdate();
            if(rows>0){
                System.out.println("Application "+ newStatus.toLowerCase()+" successfully!");
            }else{
                System.out.println("Application was not updated.");
            }
        }
    }catch(Exception e){
        System.out.println("Error Processing applicaton: "+e.getMessage());
    }
  }   
}
