public class Course {
    private int courseId;
    private String courseName;
    private int totalSeats;
    private double cutoffmarks;

    //Default constructor
    public Course(){

    }

    //parameterized constructor
    public Course(String courseName, int totalSeats, double cutoffmarks){
        this.courseName = courseName;
        this.totalSeats = totalSeats;
        this.cutoffmarks = cutoffmarks;
    }

    //getter
    public int getCourseId(){
        return courseId;
    }
    public  String getCourseName(){
        return courseName;
    }
    public int getTotalSeats(){
        return totalSeats;
    }
    public double getCutOffMarks(){
        return cutoffmarks;
    }

    //setter
    public void setCourseId(int courseId){
        this.courseId = courseId;
    }
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }
    public void setTotalSeats(int totalSeats){
        this.totalSeats = totalSeats;
    }
    public void setCutOffMarks(double cutoffmarks){
        this.cutoffmarks = cutoffmarks;
    }
    
}
